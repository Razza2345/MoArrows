package com.gmail.l0g1clvl.MoArrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import java.util.List;
import org.bukkit.plugin.PluginDescriptionFile;
import com.gmail.l0g1clvl.MoArrows.CommandHandler;

public class MoArrows extends JavaPlugin {
    public static Logger log;
    
    public static Map<Player, ArrowType> activeArrowType;
    public static Map<Player, List<ArrowType>> removedTypes;
    public static List<ArrowID> arrowList;
    public static List<String> compressionList;
    public static Random randomGenerator;
    
    // group cooldowns variables
    public static ConfigurationSection groupSection;
    public static Set<String> groupList;
    public static Map<Player, List<CooldownID>> cooldownList;
    
    // set up storage of outside plugins
    public static Plugin wgPlugin;
    public static Plugin tPlugin;
    public static Plugin fPlugin;
    
    // set up class instances
    public static MoArrows moArrows;
    public static ConfigHandler configHandler;
    private static CommandHandler commandHandler;
    public static DamageHandler damageHandler;
    public static HookHandler hookHandler;
    public static MaterialHandler materialHandler;
    
    //configuration variables
    public static List <String> removedArrows;
    public static Double diamondPenalty, goldPenalty, ironPenalty, leatherPenalty, 
    	nakedPenalty, chainPenalty;
    public static int doombringerCooldown, compressionCooldown,
		explosiveCooldown, fireCooldown, 
		lightningCooldown, netCooldown, 
		piercingCooldown, poisonCooldown, 
		razorCooldown, slowCooldown, teleportCooldown;
    public static double baseDamageMultiplier;
    public static double baseCritMultiplier;
    public static double razorCritMultiplier;
    public static double piercingMultiplier;
    public static int baseCritChance, baseMassiveChance;
    public static int poisonSec, slowSec, fireSec, netSec, doomPoisonSec,
    	explosiveRadius, compressionRadius, doombringerRadius;
    public static double baseMassiveMultiplier;
    public static double baseCrouchMultiplier;
    public static double baseArmorPenalyty[];
    public static boolean allowCrits, allowArmorPenalty, allowExplosionDamage, 
    	allowFactionDamage, allowFireDamage;
    public static Map<String, ItemStack[]> removedItemStacks;
    
    public enum ArrowType {
    	Normal, Razor, Piercing, Lightning,
    	Fire, Explosive, Compression, Poison,
    	Slow, Net, Teleport, Doombringer
    }
    
	public MoArrows() {
		moArrows = this;
	}

	@Override
	public void onEnable() {
		log = Logger.getLogger("minecraft");
		activeArrowType = new HashMap<Player, ArrowType>();
		removedTypes = new HashMap<Player, List<ArrowType>>();
		removedItemStacks = new HashMap<String, ItemStack[]>();
		cooldownList = new HashMap<Player, List<CooldownID>>();
		groupList = new HashSet<String>();
		arrowList = new ArrayList<ArrowID>();
		compressionList = new ArrayList<String>();
		randomGenerator = new Random();
		
		// initialize handlers
		configHandler = new ConfigHandler();
		commandHandler = new CommandHandler();
		hookHandler = new HookHandler();
		damageHandler = new DamageHandler();
		materialHandler = new MaterialHandler();
		
		// register listeners
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new EntityListener(), this);
		
		// store outside plugins
		wgPlugin = getServer().getPluginManager().getPlugin("WorldGuard");
		tPlugin = getServer().getPluginManager().getPlugin("Towny");
		fPlugin = getServer().getPluginManager().getPlugin("Factions");
		
		// initialize command interface
		getCommand("moarrows").setExecutor(commandHandler);
	
		// check config file and retrieve values
		if (configHandler.getConfig()) {
			configHandler.getValues();
		}
		
		// this code enables the /reload command
		Player[] player = getServer().getOnlinePlayers();
		if (player.length > 0) {
			for (Player p : player) {
				moArrows.activeArrowType.put(p, ArrowType.Normal);
				// required for cooldown code
				List <CooldownID> tempList = new ArrayList<CooldownID>();
				tempList.add(new CooldownID(p, ArrowType.Normal, p.getWorld().getFullTime()));
				moArrows.cooldownList.put(p, tempList);
			}
		}
		
	}
	
	public String getType(Player player) {
		return moArrows.activeArrowType.get(player).toString();
	}
	
	@Override
	public void onDisable() {
		 log = null;
		 activeArrowType = null;
	     arrowList = null;
	     randomGenerator = null;
	     groupSection = null;
	     groupList = null;
	     compressionList = null;
	     cooldownList = null;
	     wgPlugin = null;
	     tPlugin = null;
	     fPlugin = null;
	     moArrows = null;
	     configHandler = null;
	     commandHandler = null;
	     damageHandler = null;
	     hookHandler = null;
	     materialHandler = null;
	     removedArrows = null;
	     diamondPenalty = null;
	     goldPenalty = null;
	     ironPenalty = null;
	     leatherPenalty = null;
	     nakedPenalty = null;
	     chainPenalty = null;
	     doombringerCooldown  = 0;
	     explosiveCooldown = 0;
	     fireCooldown = 0;
	     lightningCooldown = 0;
	     netCooldown = 0;
	     piercingCooldown = 0;
	     poisonCooldown = 0;
	     razorCooldown = 0;
	     slowCooldown = 0;
	     teleportCooldown = 0;
	     compressionCooldown = 0;
	     baseDamageMultiplier = 0;
	     baseCritMultiplier = 0;
	     razorCritMultiplier = 0;
	     piercingMultiplier = 0;
	     baseCritChance = 0;
	     baseMassiveChance = 0;
	     poisonSec = 0;
	     slowSec = 0;
	     fireSec = 0;
	     netSec = 0;
	     doomPoisonSec = 0;
	     explosiveRadius = 0;
	     compressionRadius = 0;
	     doombringerRadius = 0;
	     baseMassiveMultiplier = 0;;
	     baseCrouchMultiplier = 0;;
	     baseArmorPenalyty = null;
	     removedItemStacks = null;
	}
}

