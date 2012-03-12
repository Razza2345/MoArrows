package com.gmail.l0g1clvl.MoArrows;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Arrow;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gmail.l0g1clvl.MoArrows.arrows.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Changes arrow types, fires arrows
 * @author MrAverage with code from ayan4m1
 */
public class MoArrowsPlayerListener implements Listener {
	private MoArrows plugin;

	public void onPlayerQuit(PlayerQuitEvent event) {
		if (plugin.activeArrowType.containsKey(event.getPlayer())) {
			plugin.activeArrowType.remove(event.getPlayer());
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		String message = " ";
		Player player = event.getPlayer();
		if (player.getItemInHand().getType() == Material.BOW) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR	|| event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				//event.setCancelled(true);

				if (!plugin.activeArrowType.containsKey(player)) {
					plugin.activeArrowType.put(player, ArrowType.Normal);
				}

				ArrowType arrowType = plugin.activeArrowType.get(player);
//				MaterialData arrowMaterial = plugin.config.getReqdMaterialData(arrowType);

//				PlayerInventory inventory = player.getInventory();
//				if (!player.hasPermission("multiarrow.free-materials") && arrowMaterial.getItemType() != Material.AIR) {
//					String arrowMaterialName = arrowMaterial.getItemType().toString().toLowerCase().replace('_', ' ');
//					if (arrowMaterial.getData() > 0) {
//						arrowMaterialName += " (" + ((Byte)arrowMaterial.getData()).toString() + ")";
//					}
//					if (inventory.contains(arrowMaterial.getItemType())) {
//						ItemStack reqdStack = inventory.getItem(inventory.first(arrowMaterial.getItemType()));
//						if (reqdStack.getAmount() > 1) {
//							reqdStack.setAmount(reqdStack.getAmount() - 1);
//						} else {
//							inventory.clear(inventory.first(arrowMaterial.getItemType()));
//						}
//					} else {
//						player.sendMessage("You do not have any " + arrowMaterialName);
//						return;
//					}
//				}
//
//				if (!player.hasPermission("multiarrow.infinite")) {
//					if (inventory.contains(Material.ARROW)) {
//						ItemStack arrowStack = inventory.getItem(inventory.first(Material.ARROW));
//						if (arrowStack.getAmount() > 1) {
//							arrowStack.setAmount(arrowStack.getAmount() - 1);
//						} else {
//							inventory.remove(arrowStack);
//						}
//					} else {
//						player.sendMessage("Out of arrows!");
//						return;
//					}
//				}
//
//				//HACK: Without this the arrow count does not update correctly
//				player.updateInventory();

				
			} else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (plugin.activeArrowType.containsKey(player)) {
					int arrowTypeIndex = plugin.activeArrowType.get(player).ordinal();
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
					plugin.activeArrowType.put(player, ArrowType.values()[arrowTypeIndex]);
					if (player.hasPermission("multiarrow.use.all")) {
						
						if (!player.isSneaking()) {
							if (!plugin.removedArrows.contains(plugin.activeArrowType.get(player).toString().toLowerCase())) {
								arrowTypeIndex = plugin.activeArrowType.get(player).ordinal();
							} else {
								arrowTypeIndex = plugin.activeArrowType.get(player).ordinal() + 1;
							}
						} else {
							if (!plugin.removedArrows.contains(plugin.activeArrowType.get(player).toString().toLowerCase())) {
								arrowTypeIndex = plugin.activeArrowType.get(player).ordinal();
							} else {
								arrowTypeIndex = plugin.activeArrowType.get(player).ordinal() - 1;
							}
						}
						
						
						//arrowTypeIndex = this.nextArrowIndex(arrowTypeIndex, player.isSneaking());
					} //else {
//						int initialIndex = arrowTypeIndex;
//						arrowTypeIndex = this.nextArrowIndex(arrowTypeIndex, player.isSneaking());
//						while (arrowTypeIndex != initialIndex) {
//							String permissionNode = "multiarrow.use." + ArrowType.values()[arrowTypeIndex].toString().toLowerCase();
//							if (player.hasPermission(permissionNode)) {
//								break;
//							}
//
//							if (player.isSneaking()) {
//								if (arrowTypeIndex == 0) {
//									arrowTypeIndex = ArrowType.values().length - 1;
//								} else {
//									arrowTypeIndex--;
//								}
//							} else {
//								if (arrowTypeIndex == ArrowType.values().length - 1) {
//									arrowTypeIndex = 0;
//									break;
//								} else {
//									arrowTypeIndex++;
//								}
//							}
//						}
//					}

					plugin.activeArrowType.put(player, ArrowType.values()[arrowTypeIndex]);
				} else {
					plugin.activeArrowType.put(player, ArrowType.Normal);
				}

				ArrowType arrowType = plugin.activeArrowType.get(player);
//				Double arrowFee = plugin.config.getArrowFee(arrowType);
				
				message = ChatColor.BLUE + "You select " + plugin.activeArrowType.get(player).toString() + " arrows.";
//				if (plugin.iconomy != null && arrowFee > 0D) {
//					message += " (" + iConomy.format(arrowFee) + ")";
//				}
		//		if (!plugin.removedArrows.contains(arrowType.toString().toLowerCase())) {
				player.sendMessage(message);
				}
			}
		}
	

//	@EventHandler
//	private int nextArrowIndex(int startIndex, boolean isSneaking) {
//		int currentIndex = startIndex;
//		if (isSneaking) {
//			if (currentIndex == 0) {
//				currentIndex = ArrowType.values().length - 1;
//			} else {
//				currentIndex--;
//			}
//		} else {
//			if (currentIndex == ArrowType.values().length - 1) {
//				currentIndex = 0;
//			} else {
//				currentIndex++;
//			}
//		}
//		return currentIndex;
//	}
	
	@EventHandler
    public void playerBowShoot(EntityShootBowEvent e) {
		
		MoArrowsEntityListener mael = new MoArrowsEntityListener();
        Entity entity = e.getEntity();
        Float speed = e.getForce();
        Entity arrow = e.getProjectile();
              
    }
}

