package com.gmail.l0g1clvl.MoArrows;

import org.bukkit.plugin.Plugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldGuardHook extends JavaPlugin {
	private MoArrows plugin;
	
	private WorldGuardPlugin getWorldGuard() {
	    // WorldGuard may not be loaded
	    if (plugin.wgPlugin == null || !(plugin.wgPlugin instanceof WorldGuardPlugin)) {
	        //May disable Plugin
	        return null; // Maybe you want throw an exception instead
	    }
	    return (WorldGuardPlugin) plugin.wgPlugin;
	}
	
//	Custom method
	boolean canShoot(org.bukkit.entity.Player player, org.bukkit.Location location) {
		if (getWorldGuard() != null) {
			if (!getWorldGuard().canBuild(player,
				location.getBlock().getRelative(0, 0, 0)) || 
				!getWorldGuard().canBuild(player,
			    location.getBlock().getRelative(2, 2, 2)) || 
			    !getWorldGuard().canBuild(player,
			    location.getBlock().getRelative(2, -2, 2)) || 
				!getWorldGuard().canBuild(player,
				location.getBlock().getRelative(-2, -2, 2)) || 
				!getWorldGuard().canBuild(player,
				location.getBlock().getRelative(-2, 2, 2)) || 
				!getWorldGuard().canBuild(player,
				location.getBlock().getRelative(2, 2, -2)) || 
				!getWorldGuard().canBuild(player,
				location.getBlock().getRelative(-2, 2, -2)) || 
				!getWorldGuard().canBuild(player,
				location.getBlock().getRelative(2, -2, -2)) || 
				!getWorldGuard().canBuild(player,
				location.getBlock().getRelative(-2, -2, -2))) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}
	
//  Original method
//	boolean canBuild(org.bukkit.entity.Player player, org.bukkit.Location location) {
//		return getWorldGuard().canBuild(player,
//		        player.getLocation().getBlock().getRelative(0, -1, 0));
//	}
	
}
