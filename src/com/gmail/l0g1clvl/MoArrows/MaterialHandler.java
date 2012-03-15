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
		s1 = moArrows.getConfig().getString("torch-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("torch", tempStack);
		} else {
			removedItemStacks.put("torch", nullStack);
		}
		tempStack = new ItemStack[10];
		s1 = moArrows.getConfig().getString("water-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("water", tempStack);
		} else {
			removedItemStacks.put("water", nullStack);
		}
		tempStack = new ItemStack[10];
		s1 = moArrows.getConfig().getString("drill-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("drill", tempStack);
		} else {
			removedItemStacks.put("drill", nullStack);
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
		s1 = moArrows.getConfig().getString("animal-materials");
		if (s1 != null) {
			parseMaterial(s1, tempStack);
			removedItemStacks.put("animal", tempStack);
		} else {
			removedItemStacks.put("animal", nullStack);
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
