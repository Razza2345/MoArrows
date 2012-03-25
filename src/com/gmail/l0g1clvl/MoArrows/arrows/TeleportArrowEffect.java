package com.gmail.l0g1clvl.MoArrows.arrows;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TeleportArrowEffect implements ArrowEffect {
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		
	}

	public void onGroundHitEvent(Arrow arrow) {
		Player player = (Player) arrow.getShooter();
		
		//Random r = new Random();
		Location newLoc = arrow.getLocation();//.add(r.nextInt(5) - 2, r.nextInt(5) - 2, r.nextInt(5) - 2);
		Location tempLoc = newLoc;
		while (!newLoc.getBlock().isEmpty() && newLoc.getY() < 127) {
			newLoc.add(0, 1, 0);
		}
		
		if ((newLoc.getY() - tempLoc.getY()) < 3) {
			newLoc.setPitch(0F);
			arrow.getShooter().teleport(newLoc);
			arrow.remove();
		} else {
			player.sendMessage(ChatColor.RED + "It's not safe to teleport into such a small area. Teleport canceled.");
		}
		
	}
}
