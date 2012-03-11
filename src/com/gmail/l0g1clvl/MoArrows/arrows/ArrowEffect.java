package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

public interface ArrowEffect {
	public abstract void onEntityHitEvent(Arrow arrow, Entity target);
	public abstract void onGroundHitEvent(Arrow arrow);
}
