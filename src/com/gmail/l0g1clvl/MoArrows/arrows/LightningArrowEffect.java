package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class LightningArrowEffect implements ArrowEffect {
	private MoArrows moArrows = MoArrows.moArrows;
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		arrow.getWorld().strikeLightning(arrow.getLocation());
		arrow.remove();
	}

	public void onGroundHitEvent(Arrow arrow) {
		arrow.getWorld().strikeLightning(arrow.getLocation());
		arrow.remove();
	}
}

