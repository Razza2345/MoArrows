package com.gmail.l0g1clvl.MoArrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import com.gmail.l0g1clvl.MoArrows.arrows.ArrowType;


/**
 * Reads and parses data from YAML configuration file
 * @author ayan4m1
 */
public class ConfigHandler {
	private MoArrows plugin;
	private LinkedHashMap<String, LinkedHashMap> data;
	private String defaultConfigFile;

//	public Object getOptionValue(String key) {
//		if (data.containsKey("options")) {
//			LinkedHashMap<String, Object> options = data.get("options");
//			if (options.containsKey(key)) {
//				return options.get(key);
//			} else return false;
//		} else return false;
//	}

//	public MaterialData getReqdMaterialData(ArrowType type) {
//		if (data.containsKey("materials")) {
//			LinkedHashMap<String, Object> materials = data.get("materials");
//			String typeName = type.toString().toLowerCase();
//			if (materials != null && materials.containsKey(typeName)) {
//				try {
//					String value = materials.get(typeName).toString();
//					MaterialData r = new MaterialData(Material.AIR);
//					if (value.indexOf(':') > 0) {
//						int blockId = Integer.parseInt(value.substring(0, value.indexOf(':')));
//						byte dataVal = Byte.parseByte(value.substring(value.indexOf(':') + 1));
//						if (blockId == Material.INK_SACK.getId()) {
//							r = new MaterialData(Material.INK_SACK, dataVal);
//						} else if (blockId == Material.WOOL.getId()) {
//							r = new MaterialData(Material.WOOL, dataVal);
//						} else {
//							r = new MaterialData(blockId);
//						}
//					} else {
//						r = new MaterialData(Material.getMaterial(Integer.parseInt(value)));
//					}
//					return r;
//				} catch (Exception e) {
//					plugin.log.warning("Exception parsing requirement for " + typeName + " arrow");
//					return new MaterialData(Material.AIR);
//				}
//			} else return new MaterialData(Material.AIR);
//		} else return new MaterialData(Material.AIR);
//	}

//	public boolean getArrowRemove(ArrowType type) {
//		if (data.containsKey("remove-arrows")) {
//			LinkedHashMap<String, Boolean> removals = data.get("remove-arrow");
//			String typeName = type.toString().toLowerCase();
//			plugin.log.info("removed = " + removals);
//			if (removals != null && removals.containsKey(typeName)) {
//				try {
//					return removals.get(typeName);
//				} catch (Exception e) {
//					plugin.log.warning("Removal setting for " + typeName + " arrow must be true or false");
//					return true;
//				}
//			} else return true;
//		} else return true;
//	}

//	public Double getArrowFee(ArrowType type) {
//		if (data.containsKey("fees")) {
//			LinkedHashMap<String, Double> fees = data.get("fees");
//			String typeName = type.toString().toLowerCase();
//			if (fees != null && fees.containsKey(typeName)) {
//				try {
//					return fees.get(typeName);
//				} catch (Exception e) {
//					plugin.log.warning("Fee for " + typeName + " arrow must end in a decimal (e.g. 100.0)");
//					return 0D;
//				}
//			} else return 0D;
//		} else return 0D;
//	}

	private boolean createDataDirectory() {
	    File file = plugin.getDataFolder();
	    if (!file.isDirectory()){
	        if (!file.mkdirs()) {
	            return false;
	        }
	    }
	    return true;
	}

	public ConfigHandler(MoArrows instance) {
		this.plugin = instance;
		
		defaultConfigFile = "# Uncomment arrow types below to remove them from your ";
		defaultConfigFile += "server\nremove-arrows:\n#    - water\n#    - teleport\n#";
		defaultConfigFile += "    - poison\n#    - explosive\n#    - drill\n#    - animal\n#";
		defaultConfigFile += "    - torch\n\n# Increase or decrease total bow damage by ";
		defaultConfigFile += "changing this multiplier\nbase-damage: 1.0\n# Increase or ";
		defaultConfigFile += "decrease crouch damage by changing this multiplier\n";
		defaultConfigFile += "crouch-damage: 1.2\n# Turn crits on or off and set their ";
		defaultConfigFile += "damage multipliers here\nallow-crit: true\nbase-crit: ";
		defaultConfigFile += "2.0\nbase-massive: 4.0\n# Turn on or off the armor ";
		defaultConfigFile += "penalties\nallow-penalty: true\n\n# The following are not";
		defaultConfigFile += "implemented yet\n\n# Change armor penalties below as ";
		defaultConfigFile += "follows:\n# Usage:\n#[armor type]:\n#    - [decimal head]";
		defaultConfigFile += "\n#    - [decimal chest]\n#    - [decimal legs]\n#    - ";
		defaultConfigFile += "[decimal boots]\n# All values will add up to have a final ";
		defaultConfigFile += "damage reduction multiplier.\n# For example, if you want ";
		defaultConfigFile += "the total bow damage dealt for a full diamond armor\n# set ";
		defaultConfigFile += "to be divided by 1.9, do the following:# Example: \n#diamond:";
		defaultConfigFile += "\n#    - 0.4\n#    - 0.5\n#    - 0.6\n#    - 0.4\n\n";
		defaultConfigFile += "#materials:\n#fees:\n#options:\n#    send-balance-on-fee: ";
		defaultConfigFile += "true";
		
		if (this.createDataDirectory()) {
			Yaml yaml = new Yaml();
			File configFile = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "config.yml");
			try {
				if (!configFile.exists()) {
					plugin.log.info(plugin.getDescription().getName() + " created new config.yml");
					configFile.createNewFile();
					if (configFile.canWrite()) {
						FileOutputStream fo = new FileOutputStream(configFile);
						fo.write(defaultConfigFile.getBytes());
						fo.flush();
						fo.close();
					}
				}
				FileInputStream fs = new FileInputStream(configFile);
				this.data = (LinkedHashMap<String, LinkedHashMap>)yaml.load(fs);
				if (this.data == null) {
					plugin.log.warning(plugin.getDescription().getName() + " could not load " + plugin.getDescription().getName() + "/config.yml");
				}
			} catch (IOException e) {
				plugin.log.warning("Error reading " + plugin.getDescription().getName() + "/config.yml + (" + e.getMessage() + ")");
			}
		} else {
			plugin.log.warning(plugin.getDescription().getName() + " could not find or create a configuration file!");
		}
	}
}
