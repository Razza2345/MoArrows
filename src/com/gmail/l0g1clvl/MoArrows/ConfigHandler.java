package com.gmail.l0g1clvl.MoArrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.*;

/**
 * Reads and parses data from YAML configuration file
 * @author ayan4m1
 */

public class ConfigHandler {
	private MoArrows plugin;
	private LinkedHashMap<String, LinkedHashMap> data;
	private String defaultConfigFile;
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
		defaultConfigFile += "2.0\nbase-massive: 4.0\n# Change the percentage chance of a ";
		defaultConfigFile += "critical hit or massive..\nbase-crit-chance: 7\nbase-massive-chance: 2\n\n# Turn on or off the armor ";
		defaultConfigFile += "penalties\nallow-penalty: true\n\n# Add material requirements to fired ";
		defaultConfigFile += "arrows below\n# For example, if you want explosive arrows to ";
		defaultConfigFile += "take 1 TNT and 2 blaze rods from the players inventory...\n";
		defaultConfigFile += "# explosive-materials: 46:1,369:2\nexplosive-materials: \n";
		defaultConfigFile += "poison-materials: \nlightning-materials: \nwater-materials: ";
		defaultConfigFile += "\nteleport-materials: \nanimal-materials: \ntorch-materials: ";
		defaultConfigFile += "\ndrill-materials: \n\n# The following are not";
		defaultConfigFile += "implemented yet\n\n# Change armor penalties below as ";
		defaultConfigFile += "follows:\n# Usage:\n#[armor type]:\n#    - [decimal head]";
		defaultConfigFile += "\n#    - [decimal chest]\n#    - [decimal legs]\n#    - ";
		defaultConfigFile += "[decimal boots]\n# All values will add up to have a final ";
		defaultConfigFile += "damage reduction multiplier.\n# For example, if you want ";
		defaultConfigFile += "the total bow damage dealt for a full diamond armor\n# set ";
		defaultConfigFile += "to be divided by 1.9, do the following:# Example: \n#diamond:";
		defaultConfigFile += "\n#    - 0.4\n#    - 0.5\n#    - 0.6\n#    - 0.4\n\n";
		defaultConfigFile += "#fees:\n#options:\n#    send-balance-on-fee: ";
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
