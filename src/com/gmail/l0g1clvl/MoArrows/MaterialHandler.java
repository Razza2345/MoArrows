package com.gmail.l0g1clvl.MoArrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.l0g1clvl.MoArrows.MoArrows.ArrowType;

public class MaterialHandler {
	private MoArrows moArrows = MoArrows.moArrows;
	private Boolean hasDurability = false;
	private Boolean hasMaterials = true;
	
	private ItemStack nullStack[];
	
public boolean removeMaterials(Player player) {
		
	Inventory inventory = player.getInventory();
    ItemStack[] stack = new ItemStack[10];
    
    if (player.hasPermission("moarrows.bypassmaterials")) return true;
    
    stack = null;
    stack = moArrows.removedItemStacks.get(moArrows.activeArrowType.get(player).toString().toLowerCase());
    
    if (stack[0].getTypeId() != 0) {
    
        //get stack size	
        int len = 0;
        for (int y = 0; y < stack.length; y++) {
        	if (stack[y] != null) {
        		len++;
        	} 
        }
        
        // Check for all the right materials..
        hasMaterials = true;
        hasDurability = false;
        for (int u = 0; u < len; u++) {
        	int itemType = stack[u].getTypeId();
        	int itemAmount = stack[u].getAmount();
        	short itemDurability = stack[u].getDurability();
        	
        	if (!inventory.contains(itemType, itemAmount)) {
        		hasMaterials = false;
        		return false;
        	} else {
        		ItemStack[] stack2 = inventory.getContents();
	        	for (ItemStack stak : stack2) {
	        		if (stak != null) {
		        		if (stak.getTypeId() == itemType && stak.getDurability() == itemDurability && stak.getAmount() >= itemAmount) {
		        			hasDurability = true;
	        				break;
		        		}
	        		}
	        	}
	        	if (!hasDurability) hasMaterials = false;
        	}
        }
        if (!hasMaterials && !player.hasPermission("moarrows.bypassmaterials")) {
        	player.sendMessage(ChatColor.RED + "You dont have enough materials for that type of arrow!");
        	return false;
        } else {
        	if (!player.hasPermission("moarrows.bypassmaterials")) {
        		for (int k = 0; k < len; k++) {
		        	inventory.removeItem(stack[k]);
		        }
        	} 
        	return true;
        }
    } else return true;
	}

	public boolean hasMaterials(Player player) {
		
		if (player.hasPermission("moarrows.bypassmaterials")) return true;
		
		Inventory inventory = player.getInventory();
	    ItemStack[] stack = new ItemStack[10];
	    
	    stack = null;
	    stack = moArrows.removedItemStacks.get(moArrows.activeArrowType.get(player).toString().toLowerCase());
	    
	    if (stack[0].getTypeId() != 0) {
	    
	        //get stack size	
	        int len = 0;
	        for (int y = 0; y < stack.length; y++) {
	        	if (stack[y] != null) {
	        		len++;
	        	} 
	        }
	        
	        // Check for all the right materials..
	        hasMaterials = true;
	        hasDurability = false;
	        for (int u = 0; u < len; u++) {
	        	int itemType = stack[u].getTypeId();
	        	int itemAmount = stack[u].getAmount();
	        	short itemDurability = stack[u].getDurability();
	        	
	        	if (!inventory.contains(itemType, itemAmount)) {
	        		hasMaterials = false;
	        		return false;
	        	} else {
	        		ItemStack[] stack2 = inventory.getContents();
		        	for (ItemStack stak : stack2) {
		        		if (stak != null) {
			        		if (stak.getTypeId() == itemType && stak.getDurability() == itemDurability && stak.getAmount() >= itemAmount) {
			        			hasDurability = true;
		        				break;
			        		}
		        		}
		        	}
		        	if (!hasDurability) hasMaterials = false;
	        	}
	        }
	        return hasMaterials;
	    } else return true;
	
	}

}
