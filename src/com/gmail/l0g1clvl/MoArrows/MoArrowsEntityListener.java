package com.gmail.l0g1clvl.MoArrows;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.gmail.l0g1clvl.MoArrows.arrows.ArrowType;
import com.gmail.l0g1clvl.MoArrows.arrows.ArrowEffect;
import com.gmail.l0g1clvl.MoArrows.arrows.TimedArrowEffect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
/**
 * Listens for entity events and raises arrow effect events
 * @author MrAverage with code from ayan4m1
 */

public class MoArrowsEntityListener extends JavaPlugin implements Listener {
	private MoArrows plugin;
	public static double damageMultiplier;

//	public boolean chargeFee(Player player, ArrowType type) {
//		Double arrowFee = plugin.config.getArrowFee(type);
//		if (plugin.iconomy != null && !player.hasPermission("multiarrow.free-fees") && arrowFee > 0D) {
//			try {
//				if (iConomy.hasAccount(player.getName())) {
//					Holdings balance = iConomy.getAccount(player.getName()).getHoldings();
//					if (balance.hasEnough(arrowFee)) {
//						balance.subtract(arrowFee);
//						if ((Boolean)plugin.config.getOptionValue("send-balance-on-fee") == true) {
//							player.sendMessage("Balance is now " + iConomy.format(balance.balance()) + "");
//						}
//					} else {
//						player.sendMessage("You need " + iConomy.format(arrowFee) + ", but only have " + iConomy.format(balance.balance()));
//						return false;
//					}
//				} else {
//					player.sendMessage("Couldn't find your iConomy holdings, cannot pay fee of " + iConomy.format(arrowFee));
//					return false;
//				}
//			} catch (Exception e) {
//				plugin.log.warning("Exception when trying to charge " + player.getName() + " " + iConomy.format(arrowFee));
//			}
//			return true;
//		} else return true;
//	}
	

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if (!(event.getEntity() instanceof Arrow)) {
			return;
		}

		Arrow arrow = (Arrow)event.getEntity();
		if (!(arrow.getShooter() instanceof Player)) {
			return;
		}

		ArrowType arrowType = plugin.activeArrowType.get(((Player)arrow.getShooter()));

		//We should ignore this event if there is a targetable entity within one block
		List<Entity> entities = arrow.getNearbyEntities(1D, 1D, 1D);
		int entCount = entities.size();
		for(Entity ent : entities) {
			if ((ent instanceof Arrow) || (ent instanceof Item) || (ent == arrow.getShooter())) {
				entCount--;
			}
		}

		//Only raise the onGroundHitEvent if there are no valid entities nearby
		if (entCount == 0) {
			if (arrowType != ArrowType.Normal) {
				if (true) {
					ArrowEffect arrowEffect = null;

					String className = "com.gmail.l0g1clvl.MoArrows.arrows." + arrowType.toString() + "ArrowEffect";
					try {
						arrowEffect = (ArrowEffect)Class.forName(className).newInstance();
					} catch (ClassNotFoundException e) {
						plugin.log.warning("Failed to find class " + className);
					} catch (InstantiationException e) {
						plugin.log.warning("Could not instantiate class " + className);
					} catch (IllegalAccessException e) {
						plugin.log.warning("Could not access class " + className);
					}

					arrowEffect.onGroundHitEvent(arrow);
					
//					if (plugin.config.getArrowRemove(arrowType)) {
//						arrow.remove();
//					}

//					if (arrowEffect instanceof TimedArrowEffect) {
//						TimedArrowEffect timedArrowEffect = (TimedArrowEffect)arrowEffect;
//						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, timedArrowEffect.getDelayTriggerRunnable(arrow), timedArrowEffect.getDelayTicks());
					//}
				}
			}
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
			return;
		}

		if (!(event instanceof EntityDamageByEntityEvent)) {
			return;
		}

		EntityDamageByEntityEvent ebe = (EntityDamageByEntityEvent)event;
		if (!(ebe.getDamager() instanceof Arrow)) {
			return;
		}

		Arrow arrow = (Arrow)ebe.getDamager();
		if (!(arrow.getShooter() instanceof Player)) {
			return;
		}

		ArrowType arrowType = plugin.activeArrowType.get(((Player)arrow.getShooter()));

		if (arrowType != ArrowType.Normal) {
			if (arrowType == ArrowType.Poison) {
				event.setCancelled(true);
			}
//			if (this.chargeFee((Player)arrow.getShooter(), arrowType)) {
				ArrowEffect arrowEffect = null;
				String className = "com.gmail.l0g1clvl.MoArrows.arrows." + arrowType.toString() + "ArrowEffect";
			
				try {
					arrowEffect = (ArrowEffect)Class.forName(className).newInstance();
				} catch (ClassNotFoundException e) {
					plugin.log.warning("Failed to find class " + className);
				} catch (InstantiationException e) {
					plugin.log.warning("Could not instantiate class " + className);
				} catch (IllegalAccessException e) {
					plugin.log.warning("Could not access class " + className);
				}

				arrowEffect.onEntityHitEvent(arrow, event.getEntity());
				
//				if (plugin.config.getArrowRemove(arrowType)) {
//					arrow.remove();
//				}
				
		}
		
//------------------------BEGIN "ARMOR PENALTY" CODE--------------------------
		
		Player player = (Player) arrow.getShooter();
		double damageReduction = 0;
		
		String helm = String.valueOf(player.getInventory().getHelmet());
		String chest = String.valueOf(player.getInventory().getChestplate());
		String pants = String.valueOf(player.getInventory().getLeggings());
		String boots = String.valueOf(player.getInventory().getBoots());

		if (helm.contains("DIAMOND_HELMET")) {
			damageReduction += 0.4;
		} else if (helm.contains("IRON_HELMET")) {
			damageReduction += 0.3;
		} else if (helm.contains("GOLD_HELMET")) {
			damageReduction += 0.2;
		} else if (helm.contains("LEATHER_HELMET")) {
			damageReduction += 0.1;
		} else {
			damageReduction += 0.1;
		}
		if (chest.contains("DIAMOND_CHESTPLATE")) {
			damageReduction += 0.6;
		} else if (chest.contains("IRON_CHESTPLATE")) {
			damageReduction += 0.5;
		} else if (chest.contains("GOLD_CHESTPLATE")) {
			damageReduction += 0.4;
		} else if (chest.contains("LEATHER_CHESTPLATE")) {
			damageReduction += 0.3;
		} else {
			damageReduction += 0.2;
		}
		if (pants.contains("DIAMOND_LEGGINGS")) {
			damageReduction += 0.5;
		} else if (pants.contains("IRON_LEGGINGS")) {
			damageReduction += 0.4;
		} else if (pants.contains("GOLD_LEGGINGS")) {
			damageReduction += 0.3;
		} else if (pants.contains("LEATHER_LEGGINGS")) {
			damageReduction += 0.2;
		} else {
			damageReduction += 0.1;
		}
		if (boots.contains("DIAMOND_BOOTS")) {
			damageReduction += 0.4;
		} else if (boots.contains("IRON_BOOTS")) {
			damageReduction += 0.3;
		} else if (boots.contains("GOLD_BOOTS")) {
			damageReduction += 0.2;
		} else if (boots.contains("LEATHER_BOOTS")) {
			damageReduction += 0.2;
		} else {
			damageReduction += 0.2;
		}
		

		
//------------------------BEGIN "MASSIVE CRITICALS" CODE--------------------------
		
		//plugin.log.info("Initial damage: " + String.valueOf(event.getDamage()));
		
		Random randomGenerator = new Random();
		int critNum = randomGenerator.nextInt(100);
		
		if (damageReduction > 1.5) {
			player.sendMessage(ChatColor.RED + "You are heavily encumbered.. your arrows do little damage!");
		}
		
		if (player.isSneaking()) {
			if (critNum >= 0 && critNum <=7) { 		//8% chance for regular crit
				damageMultiplier = (event.getDamage()*(2))/damageReduction;
				player.sendMessage(ChatColor.YELLOW + "Critical hit!");
			} else {
				damageMultiplier = (event.getDamage()*(1.2))/damageReduction;
			}
		} else {
			if (critNum == 7 || critNum == 77) {	//2% chance for massive crit
				damageMultiplier = (event.getDamage()*(4))/damageReduction;
				player.sendMessage(ChatColor.RED + "MASSIVE CRIT!");
			} else {
				damageMultiplier = (event.getDamage())/damageReduction;
			}
		}
		
		event.setDamage((int)Math.floor(damageMultiplier));
		
		List<String> arrows = getConfig().getStringList("remove-arrows");
        for (String s : arrows)
        	plugin.log.info("contains: " + s);
		//plugin.log.info("Final damage: " + damageMultiplier + " rounded to " + (int)Math.floor(damageMultiplier));
		
		
	}
	
	
}
