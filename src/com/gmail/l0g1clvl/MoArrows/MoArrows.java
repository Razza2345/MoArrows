package com.gmail.l0g1clvl.MoArrows;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import com.gmail.l0g1clvl.MoArrows.arrows.ArrowType;
import com.sk89q.worldedit.Location;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

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
    public MaterialHandler materials;
    public static String[] arrowID;
    public static Plugin wgPlugin;
    
    //configuration variables
    public static List <String> removedArrows;
    public static double baseDamageMultiplier;
    public static double baseCritMultiplier;
    public static double baseCritChance;
    public static double baseMassiveChance;
    public static double baseMassiveMultiplier;
    public static double baseCrouchMultiplier;
    public static double baseArmorPenalyty[];
    public static boolean allowCrits, allowArmorPenalty;
    
	public MoArrows() {
		this.log = Logger.getLogger("minecraft");
		this.activeArrowType = new HashMap<Player, ArrowType>();
	}

	public void onEnable() {
		 arrowID = new String[100];
		 for (int i=0;i<100;i++) {
			 arrowID[i] = "";
		 }
		
		this.config = new ConfigHandler(this);
		this.materials = new MaterialHandler(this);
		getServer().getPluginManager().registerEvents(new MoArrowsPlayerListener(), this);
		getServer().getPluginManager().registerEvents(new MoArrowsEntityListener(), this);
		getServer().getPluginManager().registerEvents(new MoArrowsServerListener(), this);
        
		//gather list of removed arrows 
        removedArrows = getConfig().getStringList("remove-arrows");
        log.info("[MoArrows] Removed the following arrows: " + removedArrows);
		
		baseDamageMultiplier = getConfig().getDouble("base-damage");
		baseCrouchMultiplier = getConfig().getDouble("crouch-damage");
		allowCrits = getConfig().getBoolean("allow-crit");
		allowArmorPenalty = getConfig().getBoolean("allow-penalty");
		baseCritMultiplier = getConfig().getDouble("base-crit");
		baseCritChance = getConfig().getDouble("base-crit-chance");
		baseMassiveChance = getConfig().getDouble("base-massive-chance");
		
		//hook WorldGuard
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
		wgPlugin = getServer().getPluginManager().getPlugin("WorldGuard");
		
	}

	public void onDisable() {
		
	}
	
	public String toProperCase(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

}

