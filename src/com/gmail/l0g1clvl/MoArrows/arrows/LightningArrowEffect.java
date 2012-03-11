package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public class LightningArrowEffect implements ArrowEffect {
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		arrow.getWorld().strikeLightning(arrow.getLocation());
	}

	public void onGroundHitEvent(Arrow arrow) {
		arrow.getWorld().strikeLightning(arrow.getLocation());
	}
}

