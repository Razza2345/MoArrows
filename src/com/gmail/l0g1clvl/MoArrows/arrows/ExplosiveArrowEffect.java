package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class ExplosiveArrowEffect implements ArrowEffect {
	private MoArrows moArrows = MoArrows.moArrows;
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Location location = arrow.getLocation();
		arrow.getWorld().createExplosion(location, moArrows.explosiveRadius);
		arrow.remove();
	}

	public void onGroundHitEvent(Arrow arrow) {
		Location location = arrow.getLocation();
		arrow.getWorld().createExplosion(location, moArrows.explosiveRadius);
		arrow.remove();
	}
		
}