package com.gmail.l0g1clvl.MoArrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gmail.l0g1clvl.MoArrows.MoArrows.ArrowType;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.*;

public class ConfigHandler {
	private MoArrows moArrows = MoArrows.moArrows;
	
	private LinkedHashMap<String, LinkedHashMap> data;
	private String defaultConfigFile;
	private ItemStack tempStack[];
	private ItemStack nullStack[];
	private String stackArray[];
	private String stackDurability[];
		
	public boolean getConfig() {
		String eol = System.getProperty("line.separator");
		defaultConfigFile = "" +
				"# Uncomment arrow types below to completely remove them from your server" + eol +
				"remove-arrows:" + eol +
				"#    - teleport" + eol +
				"#    - poison" + eol +
				"#    - explosive" + eol +
				"#    - compression" + eol +
				"#    - lightning" + eol +
				"#    - razor" + eol +
				"#    - slow" + eol +
				"#    - fire" + eol +
				"#    - net" + eol +
				"#    - piercing" + eol +
				"#    - doombringer" + eol +
				"" + eol +
				"# Increase or decrease total bow damage by changing this multiplier" + eol +
				"base-damage: 1.0" + eol +
				"# Increase or decrease crouch damage by changing this multiplier" + eol +
				"crouch-damage: 1.2" + eol +
				"# Turn crits on or off and set their damage multipliers here" + eol +
				"# Note: keep crits on for best performance." + eol +
				"allow-crit: true" + eol +
				"base-crit: 2.0" + eol +
				"base-massive: 4.0" + eol +
				"# Change the percentage chance of a critical hit or massive" + eol +
				"# Note: role play servers may be more comfortable with 7% crit, 2% massive." + eol +
				"base-crit-chance: 15" + eol +
				"base-massive-chance: 5" + eol +
				"" + eol +
				"# Turn on or off the armor penalties" + eol +
				"# Note: keep penalties on for best performance." + eol +
				"allow-penalty: true" + eol +
				"" + eol +
				"# Turn on or off the global arrow explosion damage" + eol +
				"allow-explosion-damage: true" + eol +
				"" + eol +
				"# Turn on or off the block explosion damage in faction zones" + eol +
				"allow-faction-explosion-damage: false" + eol +
				"" + eol +
				"# Turn on or off the block fire damage in faction zones" + eol +
				"allow-faction-fire-damage: false" + eol +
				"" + eol +
				"# Change the special damage and durations for arrow types below" + eol +
				"poison-seconds: 10" + eol +
				"fire-seconds: 10" + eol +
				"slow-seconds: 10" + eol +
				"net-seconds: 10" + eol +
				"explosive-radius: 2" + eol +
				"compression-radius: 3" + eol +
				"doombringer-radius: 10" + eol +
				"doombringer-poison-seconds: 300" + eol +
				"razor-crit-chance-multiplier: 2.0" + eol +
				"piercing-damage-multiplier: 0.5" + eol +
				"" + eol +
				"# Set cooldowns in seconds below:" + eol +
				"explosive-cooldown: 2" + eol +
				"compression-cooldown: 2" + eol +
				"poison-cooldown: 2" + eol +
				"lightning-cooldown: 2" + eol +
				"teleport-cooldown: 2" + eol +
				"razor-cooldown: 2" + eol +
				"slow-cooldown: 2" + eol +
				"fire-cooldown: 2" + eol +
				"net-cooldown: 2" + eol +
				"piercing-cooldown: 2" + eol +
				"doombringer-cooldown: 100" + eol +
				"" + eol +
				"# Add material requirements to fired arrows below" + eol +
				"# Format: [itemtype<:subtype>]x[amount]" + eol +
				"# For example, if you want explosive arrows to take 1 TNT and 2 blaze rods from the players inventory..." + eol +
				"# explosive-materials: 46x1,369x2" + eol +
				"# Another example: if you want poison arrows to take a poison II potion..." + eol +
				"# poison-materials: 373:8228x1" + eol +
				"" + eol +
				"# Note: The '0:0,' is no longer required before materials." + eol +
				"#       Including 0:0 will cause item requirement to register incorrectly." + eol +
				"#       Also, make sure there are no spaces between material requirements." + eol +
				"" + eol +
				"explosive-materials: " + eol +
				"compression-materials: " + eol +
				"poison-materials: " + eol +
				"lightning-materials: " + eol +
				"teleport-materials: " + eol +
				"razor-materials: " + eol +
				"slow-materials: " + eol +
				"fire-materials: " + eol +
				"net-materials: " + eol +
				"piercing-materials: " + eol +
				"doombringer-materials: " + eol +
				"" + eol +
				"# --WARNING! Do not edit below this line if you don't know what you're doing!--" + eol +
				"" + eol +
				"# Change armor penalties below as follows:" + eol +
				"# Usage:" + eol +
				"#[armor type]-penalty: [total decimal penalty for set]" + eol +
				"# The number represents what the final damage will be divided" + eol +
				"# by with a full set of that armor on." + eol +
				"# For example, if you want the total bow damage dealt for a full diamond armor" + eol +
				"# set to be divided by 1.9 instead of 2.0, do the following:" + eol +
				"# Example: " + eol +
				"#diamond-penalty: 1.9" + eol +
				"" + eol +
				"# Note: each set is further divided in the Java code." + eol +
				"# For all values, the head represents 20 percent of the reduction," + eol +
				"# the chest is 40, the legs are 30, and the boots are 10." + eol +
				"# This allows players wearing multiple armor types to still have" + eol +
				"# the correct penalty applied to them." + eol +
				"" + eol +
				"diamond-penalty: 2.0 " + eol +
				"gold-penalty: 1.25 " + eol +
				"iron-penalty: 1.5 " + eol +
				"chain-penalty: 1.0 " + eol +
				"leather-penalty: 0.75 " + eol +
				"naked-penalty: 0.5 " + eol +
				"" + eol +
				"# END";
		
		if (this.createDataDirectory()) {
			Yaml yaml = new Yaml();
			File configFile = new File(moArrows.getDataFolder().getAbsolutePath() + File.separator + "config.yml");
			try {
				if (!configFile.exists()) {
					moArrows.log.info("[" + moArrows.getDescription().getName() + "]" + " Created new config.yml");
					configFile.createNewFile();
					if (configFile.canWrite()) {
						FileOutputStream fo = new FileOutputStream(configFile);
						fo.write(defaultConfigFile.getBytes());
						fo.flush();
						fo.close();
						return true;
					}
				}
				FileInputStream fs = new FileInputStream(configFile);
				this.data = (LinkedHashMap<String, LinkedHashMap>)yaml.load(fs);
				if (this.data == null) {
					moArrows.log.warning(moArrows.getDescription().getName() + " could not load " + moArrows.getDescription().getName() + "/config.yml");
					return false;
				} else return true;
			} catch (IOException e) {
				moArrows.log.warning("Error reading " + moArrows.getDescription().getName() + "/config.yml + (" + e.getMessage() + ")");
				return false;
			}
		} else {
			moArrows.log.warning(moArrows.getDescription().getName() + " could not find or create a configuration file!");
			return false;
		}
	}
	
	public void getValues() {
		moArrows.diamondPenalty = moArrows.getConfig().getDouble("diamond-penalty");
		moArrows.goldPenalty = moArrows.getConfig().getDouble("gold-penalty");
		moArrows.ironPenalty = moArrows.getConfig().getDouble("iron-penalty");
		moArrows.chainPenalty = moArrows.getConfig().getDouble("chain-penalty");
		moArrows.leatherPenalty = moArrows.getConfig().getDouble("leather-penalty");
		moArrows.nakedPenalty = moArrows.getConfig().getDouble("naked-penalty");
		moArrows.baseDamageMultiplier = moArrows.getConfig().getDouble("base-damage");
		moArrows.baseCrouchMultiplier = moArrows.getConfig().getDouble("crouch-damage");
		moArrows.allowCrits = moArrows.getConfig().getBoolean("allow-crit");
		moArrows.allowArmorPenalty = moArrows.getConfig().getBoolean("allow-penalty");
		moArrows.allowExplosionDamage = moArrows.getConfig().getBoolean("allow-explosion-damage");
		moArrows.allowFactionDamage = moArrows.getConfig().getBoolean("allow-faction-explosion-damage");
		moArrows.allowFireDamage = moArrows.getConfig().getBoolean("allow-faction-fire-damage");
		moArrows.baseCritMultiplier = moArrows.getConfig().getDouble("base-crit");
		moArrows.baseMassiveMultiplier = moArrows.getConfig().getDouble("base-massive");
		moArrows.baseCritChance = moArrows.getConfig().getInt("base-crit-chance");
		moArrows.baseMassiveChance = moArrows.getConfig().getInt("base-massive-chance");
		moArrows.poisonSec = (moArrows.getConfig().getInt("poison-seconds")*20);
		moArrows.doomPoisonSec = (moArrows.getConfig().getInt("doombringer-poison-seconds")*20);
		moArrows.fireSec = (moArrows.getConfig().getInt("fire-seconds")*20);
		moArrows.slowSec = (moArrows.getConfig().getInt("slow-seconds")*20);
		moArrows.netSec = (moArrows.getConfig().getInt("net-seconds")*20);
		moArrows.explosiveRadius = (moArrows.getConfig().getInt("explosive-radius"));
		moArrows.compressionRadius = (moArrows.getConfig().getInt("compression-radius"));
		moArrows.doombringerRadius = (moArrows.getConfig().getInt("doombringer-radius"));
		moArrows.razorCritMultiplier = moArrows.getConfig().getDouble("razor-crit-chance-multiplier");
		moArrows.piercingMultiplier = moArrows.getConfig().getDouble("piercing-damage-multiplier");
		
		//gather list of cooldowns
		moArrows.doombringerCooldown = moArrows.getConfig().getInt("doombringer-cooldown");
		moArrows.explosiveCooldown = moArrows.getConfig().getInt("explosive-cooldown");
		moArrows.compressionCooldown = moArrows.getConfig().getInt("compression-cooldown");
		moArrows.fireCooldown = moArrows.getConfig().getInt("fire-cooldown");
		moArrows.lightningCooldown = moArrows.getConfig().getInt("lightning-cooldown");
		moArrows.netCooldown = moArrows.getConfig().getInt("net-cooldown");
		moArrows.piercingCooldown = moArrows.getConfig().getInt("piercing-cooldown");
		moArrows.poisonCooldown = moArrows.getConfig().getInt("poison-cooldown");
		moArrows.razorCooldown = moArrows.getConfig().getInt("razor-cooldown");
		moArrows.slowCooldown = moArrows.getConfig().getInt("slow-cooldown");
		moArrows.teleportCooldown = moArrows.getConfig().getInt("teleport-cooldown");
		
		//gather list of removed arrows 
		moArrows.removedArrows = moArrows.getConfig().getStringList("remove-arrows");
		moArrows.log.info("[MoArrows] Removed the following arrows: " + moArrows.removedArrows);
		
		nullStack = new ItemStack[1];
		nullStack[0] = new ItemStack(0, 0);
		
		for (int i = 0; i < ArrowType.values().length; i++) {
			tempStack = new ItemStack[10];
			String s1 = moArrows.getConfig().getString(ArrowType.values()[i].toString().toLowerCase() + "-materials");
			if (s1 != null) {
				parseMaterial(s1, tempStack, ArrowType.values()[i]);
				moArrows.removedItemStacks.put(ArrowType.values()[i].toString().toLowerCase(), tempStack);
			} else {
				moArrows.removedItemStacks.put(ArrowType.values()[i].toString().toLowerCase(), nullStack);
			}
        }
		
	}
	
	private boolean createDataDirectory() {
	    File file = moArrows.getDataFolder();
	    if (!file.isDirectory()){
	        if (!file.mkdirs()) {
	            return false;
	        }
	    }
	    return true;
	}
	
	private void parseMaterial(String input, ItemStack tempStack[], ArrowType arrowType) {
try {
			
			stackArray = new String[20];
			stackDurability = new String[2];
			String delim = "[x,]+";
			stackArray = input.split(delim);
			int arrayIndex = 0;
			for (int z = 0; z < stackArray.length; z+=2) {
				if (stackArray[z].contains(":")) {
					stackDurability = stackArray[z].split(":");
					tempStack[arrayIndex] = new ItemStack(Integer.parseInt(stackDurability[0]), Integer.parseInt(stackArray[z+1]));
					tempStack[arrayIndex].setDurability(Short.parseShort(stackDurability[1]));
					arrayIndex++;
				} else {
					tempStack[arrayIndex] = new ItemStack(Integer.parseInt(stackArray[z]), Integer.parseInt(stackArray[z+1]));
					arrayIndex++;
				}
			}
		}
		catch (Exception e) {
			moArrows.log.warning("[MoArrows] Error parsing " + arrowType.toString() + " material requirements!");
			moArrows.log.warning("[MoArrows] Please review your config file.");
		}
	}
	
}
