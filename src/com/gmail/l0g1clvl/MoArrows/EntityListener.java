package com.gmail.l0g1clvl.MoArrows;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.gmail.l0g1clvl.MoArrows.MoArrows.ArrowType;
import com.gmail.l0g1clvl.MoArrows.arrows.ArrowEffect;
import com.massivecraft.factions.Factions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.BukkitUtil.*;

public class EntityListener extends JavaPlugin implements Listener {
	private MoArrows moArrows = MoArrows.moArrows;
	private ArrowID arrowID;

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		arrowID = null;

		// Need this in BOTH onProjectileHit AND onEntityDamage
		ArrowType arrowType = ArrowType.Normal;

		if (!(event.getEntity() instanceof Arrow)) {
			return;
		}

		Arrow arrow = (Arrow) event.getEntity();
		// moArrows.log.info("arrow = " + event.getEntity().getEntityId());
		if (!(arrow.getShooter() instanceof Player)) {
			return;
		}

		// Checks arrowID list for fired arrows, then assigns type
		// based on the arrows unique ID.
		for (ArrowID aid : moArrows.arrowList) {
			if (aid.id == event.getEntity().getEntityId()) {
				arrowType = aid.type;
				arrowID = aid;
			}
		}

		// Ignore this event if there is a targetable entity within one block
		List<Entity> entities = arrow.getNearbyEntities(1D, 1D, 1D);
		int entCount = entities.size();
		for (Entity ent : entities) {
			if ((ent instanceof Arrow) || (ent instanceof Item)
					|| (ent == arrow.getShooter())) {
				entCount--;
			}
		}
		
		try {

			// Only raise the onGroundHitEvent if there are no valid entities nearby
			if (entCount == 0) {
				if (arrowType != ArrowType.Normal) {
					ArrowEffect arrowEffect = null;
					String className = "com.gmail.l0g1clvl.MoArrows.arrows."
							+ arrowType.toString() + "ArrowEffect";
					try {
						arrowEffect = (ArrowEffect) Class.forName(className)
								.newInstance();
					} catch (ClassNotFoundException e) {
						moArrows.log.warning("Failed to find class "
								+ className);
					} catch (InstantiationException e) {
						moArrows.log.warning("Could not instantiate class "
								+ className);
					} catch (IllegalAccessException e) {
						moArrows.log.warning("Could not access class "
								+ className);
					}
					
					Location location = arrow.getLocation();
	
					if (moArrows.hookHandler.canHit(location)) {
						arrowEffect.onGroundHitEvent(arrow);
						if (moArrows.arrowList.size() > 50) {
							while (moArrows.arrowList.size() > 50) {
								moArrows.arrowList.remove(0);
							} // remove old arrows
						}
					} else {
						Player player = (Player) arrow.getShooter();
						player.sendMessage(ChatColor.RED
								+ "You are firing into a protected area!");
					}
				}
			}
		} catch (Exception ex) {
			return;
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		ArrowID arrowID = null;
		// Need this in BOTH onProjectileHit AND onEntityDamage
		ArrowType arrowType = ArrowType.Normal;

		if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
			return;
		}

		if (!(event instanceof EntityDamageByEntityEvent)) {
			return;
		}
		
		try { // catch ebe passing exception if it happens
			EntityDamageByEntityEvent ebe = (EntityDamageByEntityEvent) event;
			if (!(ebe.getDamager() instanceof Arrow)) {
				return;
			}
			
			Arrow arrow = (Arrow) ebe.getDamager();
			if (!(arrow.getShooter() instanceof Player)) {
				return;
			}
		} catch (Exception ex) {
			return; // gtfo if it does
		}
		
		// ebe is good so proceed
		EntityDamageByEntityEvent ebe = (EntityDamageByEntityEvent) event;
		if (!(ebe.getDamager() instanceof Arrow)) {
			return;
		}
		
		Arrow arrow = (Arrow) ebe.getDamager();
		if (!(arrow.getShooter() instanceof Player)) {
			return;
		}
		
		// Checks arrowID list for fired arrows, then assigns type
		// based on the arrows unique ID.
		for (ArrowID aid : moArrows.arrowList) {
			if (aid.id == arrow.getEntityId()) {
				arrowID = aid;
			}
		}
		
		try {
				
			if (arrowID.type != ArrowType.Normal) {
				ArrowEffect arrowEffect = null;
				String className = "com.gmail.l0g1clvl.MoArrows.arrows."
						+ arrowID.type.toString() + "ArrowEffect";
	
				try {
					arrowEffect = (ArrowEffect) Class.forName(className)
							.newInstance();
				} catch (ClassNotFoundException e) {
					moArrows.log.warning("Failed to find class " + className);
				} catch (InstantiationException e) {
					moArrows.log
							.warning("Could not instantiate class " + className);
				} catch (IllegalAccessException e) {
					moArrows.log.warning("Could not access class " + className);
				}
	
				Player player = (Player) arrow.getShooter();
				Location location = arrow.getLocation();
	
				if (moArrows.hookHandler.canHit(location)) {
					arrowEffect.onEntityHitEvent(arrow, event.getEntity());
				} else {
					player.sendMessage(ChatColor.RED
							+ "You are firing into a protected area!");
					event.setCancelled(true);
				}
	
			}
	
			Player player = (Player) arrow.getShooter();
			double damageReduction = moArrows.damageHandler.getPenalty(player);
			
			event.setDamage(moArrows.damageHandler.setDamage(event, arrowID, damageReduction));
			
			if (moArrows.arrowList.size() > 50) {
				while (moArrows.arrowList.size() > 50) {
					moArrows.arrowList.remove(0);
				} // remove old arrows
			}
			
		} catch (Exception ex) {
			return;
		}
	}

	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		if (MoArrows.hookHandler.canExplode(event) == false) {
			event.blockList().clear();
			return;
		}

		for (String l : moArrows.compressionList) {
			String tempstring = "" + event.getLocation().getBlockX() + event.getLocation().getBlockY() + event.getLocation().getBlockZ();
			if (l.contains(tempstring)) {
				event.blockList().clear();
				moArrows.compressionList.remove(l);
				return;
			}
		}
	}

	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent event) {
		Boolean allowFireDamage = moArrows.allowFireDamage;
		Block block = event.getBlock();
		Location location = block.getLocation();

		if (MoArrows.hookHandler.isFactionWarZone(location)) {
			event.setCancelled(true);
			ExtinguishFire(event.getBlock().getRelative(BlockFace.NORTH));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.EAST));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.SOUTH));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.WEST));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.UP));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.DOWN));
		}

		if (!moArrows.allowFireDamage
				&& MoArrows.hookHandler.isFactionLand(location)) {
			event.setCancelled(true);
			ExtinguishFire(event.getBlock().getRelative(BlockFace.NORTH));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.EAST));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.SOUTH));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.WEST));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.UP));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.DOWN));
		}

	}

	public void ExtinguishFire(Block block) {
		if (block.getType() == Material.FIRE) {
			block.setType(Material.AIR);
		}
	}

	@EventHandler
	public void onBlockBurn(BlockBurnEvent event) {
		Boolean allowFireDamage = moArrows.allowFireDamage;
		Block block = event.getBlock();
		Location location = block.getLocation();

		if (MoArrows.hookHandler.isFactionWarZone(location)) {
			event.setCancelled(true);
			ExtinguishFire(event.getBlock().getRelative(BlockFace.NORTH));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.EAST));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.SOUTH));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.WEST));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.UP));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.DOWN));
		}

		if (!moArrows.allowFireDamage
				&& MoArrows.hookHandler.isFactionLand(location)) {
			event.setCancelled(true);
			ExtinguishFire(event.getBlock().getRelative(BlockFace.NORTH));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.EAST));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.SOUTH));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.WEST));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.UP));
			ExtinguishFire(event.getBlock().getRelative(BlockFace.DOWN));
		}

	}

}
