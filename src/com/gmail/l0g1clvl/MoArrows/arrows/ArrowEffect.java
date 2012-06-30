package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityExplodeEvent;

public interface ArrowEffect {
	public abstract void onEntityHitEvent(Arrow arrow, Entity target);
	public abstract void onGroundHitEvent(Arrow arrow);
}
