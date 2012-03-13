package com.gmail.l0g1clvl.MoArrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.l0g1clvl.MoArrows.arrows.ArrowType;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * MoArrows for Bukkit (originally MultiArrow)
 * @author MrAverage with code from ayan4m1
 */
public class MoArrows extends JavaPlugin {

    public static Logger log;
    public static Map<Player, ArrowType> activeArrowType;
    public ConfigHandler config;
    public static String[] arrowID;
    
    
    //configuration variables
    public static List <String> removedArrows;
    public static double baseDamageMultiplier;
    public static double baseCritMultiplier;
    public static double baseMassiveMultiplier;
    public static double baseCrouchMultiplier;
    public static double baseArmorPenalyty[];
    public static String poisonMaterials, explosiveMaterials, lightningMaterials, teleportMaterials, torchMaterials, waterMaterials, drillMaterials, animalMaterials;
    public static boolean allowCrits, allowArmorPenalty, needMaterials;
   

	public MoArrows() {
		this.log = Logger.getLogger("minecraft");
		this.activeArrowType = new HashMap<Player, ArrowType>();
	}

	public String toProperCase(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

	public void onEnable() {
		 arrowID = new String[100];
		 for (int i=0;i<100;i++) {
			 arrowID[i] = "";
		 }
		
		this.config = new ConfigHandler(this);
		getServer().getPluginManager().registerEvents(new MoArrowsPlayerListener(), this);
		getServer().getPluginManager().registerEvents(new MoArrowsEntityListener(), this);
		getServer().getPluginManager().registerEvents(new MoArrowsServerListener(), this);
        
		//gather list of removed arrows
        removedArrows = getConfig().getStringList("remove-arrows");
        log.info("[MoArrows] Removed the following arrows: " + removedArrows);
		log.info("[MoArrows] MoArrows v0.1.2 enabled!");
		
		baseDamageMultiplier = getConfig().getDouble("base-damage");
		baseCrouchMultiplier = getConfig().getDouble("crouch-damage");
		allowCrits = getConfig().getBoolean("allow-crit");
		needMaterials = getConfig().getBoolean("need-materials");
		allowArmorPenalty = getConfig().getBoolean("allow-penalty");
		baseCritMultiplier = getConfig().getDouble("base-crit");
		baseMassiveMultiplier = getConfig().getDouble("base-massive");
//		poisonMaterials = getConfig().getString("poison-materials");
//		drillMaterials = getConfig().getString("poison-materials");
//		explosiveMaterials = getConfig().getString("poison-materials");
//		teleportMaterials = getConfig().getString("poison-materials");
//		lightningMaterials = getConfig().getString("poison-materials");
//		animalMaterials = getConfig().getString("poison-materials");
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " shutting down.");
	}

}

