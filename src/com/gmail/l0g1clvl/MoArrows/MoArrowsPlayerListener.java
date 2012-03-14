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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;
import com.gmail.l0g1clvl.MoArrows.arrows.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Changes arrow types, fires arrows
 * @author MrAverage with code from ayan4m1
 */

public class MoArrowsPlayerListener implements Listener {
	
	public static int[] matsReqd;
	public static String[] parse1;
	public static ItemStack[] stack;
	public static List <String> matsList;
	
	private MoArrows plugin;
	private MaterialHandler materialHandler;

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
				
			} else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				
				if (plugin.activeArrowType.containsKey(player)) {
					int arrowTypeIndex = plugin.activeArrowType.get(player).ordinal();
					
					do {
						
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
					
					} while (plugin.removedArrows.contains(plugin.activeArrowType.get(player).toString().toLowerCase()) || !player.hasPermission("moarrows.use." + plugin.activeArrowType.get(player).toString().toLowerCase()));
					

				} else {
					plugin.activeArrowType.put(player, ArrowType.Normal);
				}

				// Figure out the perission node name
				String specificPerm = "" + plugin.activeArrowType.get(player).toString().toLowerCase();
				
				if (player.hasPermission("moarrows.use.all")) {
					ArrowType arrowType = plugin.activeArrowType.get(player);
					message = ChatColor.BLUE + "You select " + plugin.activeArrowType.get(player).toString() + " arrows.";
					player.sendMessage(message);
				} else if (player.hasPermission("moarrows.use." + specificPerm)) {
					ArrowType arrowType = plugin.activeArrowType.get(player);
					message = ChatColor.BLUE + "You select " + plugin.activeArrowType.get(player).toString() + " arrows.";
					player.sendMessage(message);
				} else {
					plugin.activeArrowType.put(player, ArrowType.Normal);
				}
			}
		}
	}
	
	
	@EventHandler
    public void playerBowShoot(EntityShootBowEvent e) {
		
		MoArrowsEntityListener mael = new MoArrowsEntityListener();
        Entity entity = e.getEntity();
        Float speed = e.getForce();
        Entity arrow = e.getProjectile();
        
        Player player = (Player) entity;
        int arrowNum = e.getProjectile().getEntityId(); //IMPORTANT!!!
        //      ^--- this is the custom ID for that arrow!
        
//--------------------------MATERIALS NEEDED CODE----------------------------------
        
        Inventory inventory = player.getInventory();
        ItemStack[] stack = new ItemStack[10];
        
        String l = "" + plugin.activeArrowType.get(player);
        switch (l) {
        case "Explosive" : stack = materialHandler.removedItemStacks.get("explosive");
        	break;
        case "Poison" : stack = materialHandler.removedItemStacks.get("poison");
    		break;
        case "Water" : stack = materialHandler.removedItemStacks.get("water");
    		break;
        case "Drill" : stack = materialHandler.removedItemStacks.get("drill");
    		break;
        case "Lightning" : stack = materialHandler.removedItemStacks.get("lightning");
    		break;
        case "Torch" : stack = materialHandler.removedItemStacks.get("torch");
    		break;
        case "Teleport" : stack = materialHandler.removedItemStacks.get("teleport");
    		break;
        case "Animal" : stack = materialHandler.removedItemStacks.get("animal");
    		break;
    	default : stack = null;
        }
        
        if (stack != null) {
        
	        //get stack size	
	        int len = 0;
	        for (int y = 0; y < stack.length; y++) {
	        	if (stack[y] != null) {
	        		len++;
	        	} 
	        }
	        
	        // Check for all the right materials..
	        Boolean hasMaterials = true;
	        for (int u = 0; u < len; u++) {
	        	int temp1 = stack[u].getTypeId();
	        	int temp2 = stack[u].getAmount();
	        	if (!inventory.contains(temp1, temp2)) {
	        		hasMaterials = false;
	        		break;
	        	}
	        }
	        
	        if (!hasMaterials) {
	        	player.sendMessage(ChatColor.RED + "You dont have enough materials for that type of arrow!");
	        } else {
	        	for (int k = 0; k < len; k++) {
		        	inventory.removeItem(stack[k]);
		        }
	        	
	        	//player is good to fire a special arrow by this point..
	        	//associate a custom ID to an arrow entity with a specific arrow type
	            for (int j = 0; j < 100; j ++) {
	            	if (plugin.arrowID[j] == "") {
	            		plugin.arrowID[j] = "" + arrowNum + "." + plugin.activeArrowType.get(player);
	        	        //plugin.log.info(plugin.arrowID[j]);
	        	        break;
	            	} 
	            }
	        }
        }
    }
}

