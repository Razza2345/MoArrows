package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class ExplosiveArrowEffect implements ArrowEffect {
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Location loc = target.getLocation();
		arrow.getWorld().createExplosion(loc, 2);
	}

	public void onGroundHitEvent(Arrow arrow) {
		Location loc = arrow.getLocation();
		arrow.getWorld().createExplosion(loc, 2);
		
//		Player player = (Player) arrow.getShooter();
//		if (player.getInventory().contains(1)) {
//			player.sendMessage("removed 1");
//			player.getInventory().removeItem(new ItemStack(1,1));
//		} else {
//			player.sendMessage("removed 0");
//		}
		
	}
		
}