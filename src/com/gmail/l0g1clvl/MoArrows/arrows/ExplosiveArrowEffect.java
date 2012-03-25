package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class ExplosiveArrowEffect implements ArrowEffect {
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Location loc = arrow.getLocation();
		arrow.getWorld().createExplosion(loc, 2);
		arrow.remove();
	}

	public void onGroundHitEvent(Arrow arrow) {
		Location loc = arrow.getLocation();
		arrow.getWorld().createExplosion(loc, 2);
		arrow.remove();
	}
		
}