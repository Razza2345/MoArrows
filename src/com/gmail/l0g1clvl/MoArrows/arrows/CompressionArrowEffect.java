package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class CompressionArrowEffect implements ArrowEffect {
	private MoArrows moArrows = MoArrows.moArrows;

	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Location location = arrow.getLocation();
		moArrows.compressionList.add("" + location.getBlockX() + location.getBlockY() + location.getBlockZ());
		arrow.getWorld().createExplosion(location, moArrows.compressionRadius);
		arrow.remove();
	}

	public void onGroundHitEvent(Arrow arrow) {
		Location location = arrow.getLocation();
		moArrows.compressionList.add("" + location.getBlockX() + location.getBlockY() + location.getBlockZ());
		arrow.getWorld().createExplosion(location, moArrows.compressionRadius);
		arrow.remove();
	}

}
