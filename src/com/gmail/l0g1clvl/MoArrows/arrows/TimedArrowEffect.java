package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.entity.Arrow;

public interface TimedArrowEffect extends ArrowEffect {
	public Runnable getDelayTriggerRunnable(Arrow arrow);
	public long getDelayTicks();
}
