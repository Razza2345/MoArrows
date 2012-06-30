package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class FireArrowEffect implements ArrowEffect {
	private MoArrows moArrows = MoArrows.moArrows;
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Entity e = (LivingEntity) target;
		e.setFireTicks(moArrows.fireSec);
	}

	public void onGroundHitEvent(Arrow arrow) {
		
	}
}
