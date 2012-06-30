package com.gmail.l0g1clvl.MoArrows;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.P;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyUniverse;

public class HookHandler {
	private MoArrows moArrows = MoArrows.moArrows;
	private Plugin[] pluginList;
	
	private WorldGuardPlugin getWorldGuard() {
	    // WorldGuard may not be loaded
	    if (moArrows.wgPlugin == null || !(moArrows.wgPlugin instanceof WorldGuardPlugin)) {
	    	//plugin.log.warning("#debug WorldGuard plugin not found!");
	        //May disable Plugin
	        return null; // Maybe you want throw an exception instead
	    }
	    //plugin.log.warning("#debug WorldGuard plugin hooked!");
	    return (WorldGuardPlugin) moArrows.wgPlugin;
	}
	
	private P getFactions() {
	    // Factions may not be loaded
	    if (moArrows.fPlugin == null || !(moArrows.fPlugin instanceof P)) {
	        //May disable Plugin
	        return null; // Maybe you want throw an exception instead
	    }
	    return (P) moArrows.fPlugin;
	}
	
	private Towny getTowny() {
	    // Towny may not be loaded
	    if (moArrows.tPlugin == null || !(moArrows.tPlugin instanceof Towny)) {
	        //May disable Plugin
	        return null; // Maybe you want throw an exception instead
	    }
	    return (Towny) moArrows.tPlugin;
	}
	
	boolean isProtected(Player player, Location location, int radius) {
		if (getWorldGuard() != null) { //add radius variable!!!!!!!!!!!
			//plugin.log.warning("#debug canShoot active.");
			
			try {
				ApplicableRegionSet regionSet = getWorldGuard().getRegionManager(location.getWorld()).getApplicableRegions(location);
			
				if (!regionSet.allows(DefaultFlag.PVP) || !getWorldGuard().canBuild(player,
					location.getBlock().getRelative(0, 0, 0)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(radius, 0, 0)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(0, radius, 0)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(0, 0, radius)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(-radius, 0, 0)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(0, -radius, 0)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(0, 0, -radius)) || 
					!getWorldGuard().canBuild(player,
				    location.getBlock().getRelative(radius, radius, radius)) || 
				    !getWorldGuard().canBuild(player,
				    location.getBlock().getRelative(radius, -radius, radius)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(-radius, -radius, radius)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(-radius, radius, radius)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(radius, radius, -radius)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(-radius, radius, -radius)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(radius, -radius, -radius)) || 
					!getWorldGuard().canBuild(player,
					location.getBlock().getRelative(-radius, -radius, -radius))) {
					return false;
				} else {
					return true;
				}
			
			} catch(Exception ex) {
				return true;
			}	
		
		} else {
			return true;
		}
	}
	
	boolean isRegionPvp(Location location) {
		if (getWorldGuard() != null) { //add radius variable!!!!!!!!!!!
			//plugin.log.warning("#debug canShoot active.");
			
			try {
				ApplicableRegionSet regionSet = getWorldGuard().getRegionManager(location.getWorld()).getApplicableRegions(location);
				if (regionSet.allows(DefaultFlag.PVP)) {
					return true;
				} else return false;
			} catch (Exception ex) {
				return true;
			}
		} else return true;
	}
	
	boolean isRegionBuild(Location location) {
		if (getWorldGuard() != null) { //add radius variable!!!!!!!!!!!
			//plugin.log.warning("#debug canShoot active.");
			
			try {
				ApplicableRegionSet regionSet = getWorldGuard().getRegionManager(location.getWorld()).getApplicableRegions(location);
				if (regionSet.allows(DefaultFlag.BUILD)) {
					return true;
				} else return false;
			} catch (Exception ex) {
				return false;
			}
		} else return false;
	}
	
	boolean isFactionLand(Location location) {
		
		if (getFactions() != null) { 
			Faction faction = Board.getFactionAt(new FLocation(location));
			//plugin.log.warning("#debug No faction?=" + faction.isNone());
			if (faction.isNone()) return false;
			else return true;
		} else {
			//plugin.log.warning("#debug factions not hooked!");
			return false;
		}
	}
	
	boolean isFactionSafeZone(Location location) {
		
		if (getFactions() != null) { 
			Faction faction = Board.getFactionAt(new FLocation(location));
			//plugin.log.warning("#debug No faction?=" + faction.isNone());
			if (faction.isSafeZone() || faction.isPeaceful()) return true;
			else return false;
		} else {
			//plugin.log.warning("#debug factions not hooked!");
			return false;
		}
	}
	
	boolean isFactionWarZone(Location location) {
		
		if (getFactions() != null) { 
			Faction faction = Board.getFactionAt(new FLocation(location));
			//plugin.log.warning("#debug No faction?=" + faction.isNone());
			if (faction.isWarZone()) return true;
			else return false;
		} else {
			//plugin.log.warning("#debug factions not hooked!");
			return false;
		}
	}
	
	boolean isTownPvp(Location location) {
		
		if (getTowny() != null) { 
			TownyUniverse tu = new TownyUniverse((Towny) moArrows.tPlugin);
			TownBlock townBlock = tu.getTownBlock(location);

			if (townBlock != null) {
				if (townBlock.getPermissions().pvp) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
			
		} else {
			return true;
		}
	}
	
	public boolean canHit(Location location) {
		
	    if (isFactionWarZone(location)) {
	        return true;
	    } 
	         
		if (isFactionSafeZone(location)) {
		    return false;
		}
		        
		if (!isTownPvp(location)) {
		    return false;
		}
		        
		if (!isRegionPvp(location)) {
		    return false;
		}
	    return true;
	}
	
	public boolean canExplode(EntityExplodeEvent event) {
		Boolean allowExplosionDamage = moArrows.allowExplosionDamage;
		Boolean allowFactionDamage = moArrows.allowFactionDamage;
		Location location = event.getLocation();
		List<Block> blockList = event.blockList();
        		
		if (event.getEntity() == null) {
		
	        if(!allowExplosionDamage) {
	            return false;
	        }
	        
	        for (Block block : blockList) {
	        	location = block.getLocation();
	        	
		        if (!allowFactionDamage && isFactionLand(location)) {
		            return false;
		        }
		        
		        if (isFactionSafeZone(location)) {
		        	return false;
		        }
		        
		        if (!isTownPvp(location)) {
		        	return false;
		        }
		        
		        if (!isRegionPvp(location)) {
		        	return false;
		        }
	        	
	        }
	        return true;
		} else return true;
	}
	
}
