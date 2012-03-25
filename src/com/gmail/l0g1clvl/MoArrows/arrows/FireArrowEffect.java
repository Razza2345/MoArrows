package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class FireArrowEffect implements ArrowEffect {
	private MoArrows plugin;
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Entity e = (Entity) target;
		e.setFireTicks(400);
		arrow.remove();
	}

	public void onGroundHitEvent(Arrow arrow) {
		Location blockLoc = arrow.getLocation();
		Block b = (Block) blockLoc.getBlock();
		b.setTypeId(51, true);
	}
}
