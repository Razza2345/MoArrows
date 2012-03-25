package com.gmail.l0g1clvl.MoArrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.l0g1clvl.MoArrows.arrows.ArrowType;

public class MaterialHandler {
	MoArrows moArrows = new MoArrows();
	public static Map<String, ItemStack[]> removedItemStacks;
	private ItemStack tempStack[];
	private ItemStack nullStack[];
	private String stackArray[];

	public MaterialHandler(MoArrows instance) {
		this.moArrows = instance;
		this.removedItemStacks = new HashMap<String, ItemStack[]>();
		nullStack = new ItemStack[1];
		nullStack[0] = new ItemStack(0, 0);
		
		tempStack = new ItemStack[10];
		String s1 = moArrows.getConfig().getString("explosive-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("explosive", tempStack);
		} else {
			removedItemStacks.put("explosive", nullStack);
		}
		tempStack = new ItemStack[10];
		s1 = moArrows.getConfig().getString("poison-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("poison", tempStack);
		} else {
			removedItemStacks.put("poison", nullStack);
		}
		tempStack = new ItemStack[10];
		s1 = moArrows.getConfig().getString("lightning-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("lightning", tempStack);
		} else {
			removedItemStacks.put("lightning", nullStack);
		}
		tempStack = new ItemStack[10];
		s1 = moArrows.getConfig().getString("teleport-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("teleport", tempStack);
		} else {
			removedItemStacks.put("teleport", nullStack);
		}
		tempStack = new ItemStack[10];
		s1 = moArrows.getConfig().getString("razor-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("razor", tempStack);
		} else {
			removedItemStacks.put("razor", nullStack);
		}
		tempStack = new ItemStack[10];
		s1 = moArrows.getConfig().getString("slow-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("slow", tempStack);
		} else {
			removedItemStacks.put("slow", nullStack);
		}
		tempStack = new ItemStack[10];
		s1 = moArrows.getConfig().getString("fire-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("fire", tempStack);
		} else {
			removedItemStacks.put("fire", nullStack);
		}
		tempStack = new ItemStack[10];
        s1 = moArrows.getConfig().getString("net-materials");
        if (s1 != null) {
            parseMaterial(s1, tempStack);
            removedItemStacks.put("net", tempStack);
        } else {
            removedItemStacks.put("net", nullStack);
        }
        tempStack = new ItemStack[10];
        s1 = moArrows.getConfig().getString("doombringer-materials");
        if (s1 != null) {
            parseMaterial(s1, tempStack);
            removedItemStacks.put("doombringer", tempStack);
        } else {
            removedItemStacks.put("doombringer", nullStack);
        }
	}
	
	private void parseMaterial(String input, ItemStack tempStack[]) {
		try {
			stackArray = new String[20];
			String delim = "[:,]+";
			stackArray = input.split(delim);
			int arrayIndex = 0;
			for (int z = 0; z < stackArray.length; z+=2) {
					tempStack[arrayIndex] = new ItemStack(Integer.parseInt(stackArray[z]), Integer.parseInt(stackArray[z+1]));
					arrayIndex++;
			}
		}
		catch (Exception e) {
			moArrows.log.warning("[MoArrows] Error parsing material requirements!");
			moArrows.log.warning("[MoArrows] Please review your config file.");
		}
	}
}
