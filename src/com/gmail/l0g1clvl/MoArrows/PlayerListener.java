package com.gmail.l0g1clvl.MoArrows;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Arrow;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

import com.gmail.l0g1clvl.MoArrows.MoArrows.ArrowType;
import com.gmail.l0g1clvl.MoArrows.arrows.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class PlayerListener implements Listener {
	private MoArrows moArrows = MoArrows.moArrows;

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		String message = "";
		Player player = event.getPlayer();
		if (player.getItemInHand().getType() == Material.BOW) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR	|| event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				
				if (!moArrows.activeArrowType.containsKey(player)) {
					moArrows.activeArrowType.put(player, ArrowType.Normal);
				}

				ArrowType arrowType = moArrows.activeArrowType.get(player);
				
			} else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				
				if (moArrows.activeArrowType.containsKey(player)) {
					int arrowTypeIndex = moArrows.activeArrowType.get(player).ordinal();
					
					Boolean isSkipped = false;
					Boolean doNotNotify = false;
					do {
						doNotNotify = false;
						if (!player.isSneaking()) {
							if (arrowTypeIndex == ArrowType.values().length - 1) {
								arrowTypeIndex = 0;
							} else {
								arrowTypeIndex++;
							}
						} else {
							if (arrowTypeIndex == 0) {
								arrowTypeIndex = ArrowType.values().length - 1;
							} else {
								arrowTypeIndex--;
							}
						} 
						
						moArrows.activeArrowType.put(player, ArrowType.values()[arrowTypeIndex]);
						
						isSkipped = false;
						
						if ( !moArrows.removedArrows.contains(moArrows.activeArrowType.get(player).toString().toLowerCase())
								&& !moArrows.materialHandler.hasMaterials(player)
								&& (player.hasPermission("moarrows.use." + moArrows.activeArrowType.get(player).toString().toLowerCase())
								|| player.hasPermission("moarrows.use.all")) 
								&& !player.isOp()) {
							isSkipped = true;
						}
						
						if (moArrows.activeArrowType.get(player) == ArrowType.Normal) {
							isSkipped = false;
						}
						
						if (moArrows.removedTypes.get(player) != null && !moArrows.removedTypes.get(player).isEmpty()) {
							for (ArrowType at : moArrows.removedTypes.get(player)) {
								if (moArrows.activeArrowType.get(player) == at) {
									doNotNotify = true;
								}
							}
						}
						
						if (isSkipped && !doNotNotify) {
							player.sendMessage(ChatColor.GRAY + "" + moArrows.activeArrowType.get(player).toString() + " arrows skipped. Not enough materials.");
						}
					
					} while (moArrows.removedArrows.contains(moArrows.activeArrowType.get(player).toString().toLowerCase())
							|| isSkipped
							|| doNotNotify
							|| ((moArrows.activeArrowType.get(player) != ArrowType.Normal) && !player.hasPermission("moarrows.use." + moArrows.activeArrowType.get(player).toString().toLowerCase()))
							&& !player.hasPermission("moarrows.use.all"));
					

				} else {
					moArrows.activeArrowType.put(player, ArrowType.Normal);
				}
				
				ArrowType arrowType = moArrows.activeArrowType.get(player);
				message = ChatColor.BLUE + "You select " + moArrows.activeArrowType.get(player).toString() + " arrows.";
				player.sendMessage(message);
			}
		}
	}
	
	@EventHandler
    public void playerBowShoot(EntityShootBowEvent e) {
        Entity entity = e.getEntity();
        Float speed = e.getForce();
        Entity arrow = e.getProjectile();
        Player player = (Player) entity;
        World world = player.getWorld();
        SmokeEffect se = new SmokeEffect(arrow, world);
        
        // begin cooldown code
        List <CooldownID> cdList = moArrows.cooldownList.get(player);
        ArrowType arrowType = moArrows.activeArrowType.get(player);
        long curTime = player.getWorld().getFullTime();
        Boolean exists = false;
        Boolean cooldownGood = false;
        int index = 0;
        
        for (CooldownID id : cdList) {
        	if (id.type != ArrowType.Normal && id.type == arrowType) {
        		exists = true;
        		index = cdList.indexOf(id);
        	}
        }
        if (!exists) {
        	cdList.add(new CooldownID(player, arrowType, curTime));
        	cooldownGood = true;
        } else {
        	if (curTime < (cdList.get(index).time+cdList.get(index).cooldown)) {
        		cooldownGood = false;
        		if (!player.hasPermission("moarrows.bypasscooldowns")) {
        			player.sendMessage(ChatColor.AQUA + "" + (((cdList.get(index).time+cdList.get(index).cooldown)-curTime)/10) 
        				+ " seconds remaining until you may use " + arrowType.toString() + " arrows again.");
        		}
    		} else {
    			cdList.remove(index);
    			cdList.add(new CooldownID(player, arrowType, curTime));
    			cooldownGood = true;
    		}
        }
        moArrows.cooldownList.put(player,  cdList);
        
        if (player.hasPermission("moarrows.bypasscooldowns")) cooldownGood=true;
        
        if (cooldownGood) {
		    // Check for all the right materials..
	        if (moArrows.materialHandler.removeMaterials(player)) {
	        	
	        	// add special visual effects
	        	if (moArrows.activeArrowType.get(player) == ArrowType.Fire) {
	            	arrow.setFireTicks(2000);
	                se.setId(Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(moArrows, se, 0, 1));
	            }
	            if (moArrows.activeArrowType.get(player) == ArrowType.Explosive) {
	                se.setId(Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(moArrows, se, 0, 1));
	            }
	            
	        	//player is good to fire a special arrow by this point..
	        	//associate a custom ID to an arrow entity with a specific arrow type
	            ArrowID newArrow = new ArrowID(arrow.getEntityId(), 
	            		moArrows.activeArrowType.get(player), moArrows.damageHandler.getPenalty(player), player, 
	            		player.isSneaking());
	            moArrows.arrowList.add(newArrow);
	            
	        } else { 
	        	player.sendMessage(ChatColor.RED + "You ran out of " + moArrows.activeArrowType.get(player).toString() + " arrow materials!");
	        	moArrows.activeArrowType.put(player, ArrowType.Normal);
	        }
        }
    }
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (moArrows.activeArrowType.containsKey(event.getPlayer())) {
			moArrows.activeArrowType.remove(event.getPlayer());
			moArrows.removedTypes.remove(event.getPlayer());
		}
	}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) { 
		moArrows.activeArrowType.put(event.getPlayer(), ArrowType.Normal);
		List tempList2 = new ArrayList<ArrowType>();
		moArrows.removedTypes.put(event.getPlayer(), tempList2);
		// required for cooldown code
		List <CooldownID> tempList = new ArrayList<CooldownID>();
		tempList.add(new CooldownID(event.getPlayer(), ArrowType.Normal, event.getPlayer().getWorld().getFullTime()));
		moArrows.cooldownList.put(event.getPlayer(), tempList);
	}
	
}

