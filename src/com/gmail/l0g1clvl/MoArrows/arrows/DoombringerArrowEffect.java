package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class DoombringerArrowEffect implements ArrowEffect {
	private MoArrows moArrows = MoArrows.moArrows;

	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Location location = arrow.getLocation();
		if (moArrows.hookHandler.canHit(location)) {
			arrow.getWorld().strikeLightning(arrow.getLocation());
		}
		if (moArrows.hookHandler.canHit(location.add(moArrows.doombringerRadius,0,0))) {
			arrow.getWorld().strikeLightning(arrow.getLocation().add(moArrows.doombringerRadius,0,0));
		}
		if (moArrows.hookHandler.canHit(location.subtract(moArrows.doombringerRadius,0,0))) {
			arrow.getWorld().strikeLightning(arrow.getLocation().subtract(moArrows.doombringerRadius,0,0));
		}
		if (moArrows.hookHandler.canHit(location.add(0,0,moArrows.doombringerRadius))) {
			arrow.getWorld().strikeLightning(arrow.getLocation().add(0,0,moArrows.doombringerRadius));
		}
		if (moArrows.hookHandler.canHit(location.subtract(0,0,moArrows.doombringerRadius))) {
			arrow.getWorld().strikeLightning(arrow.getLocation().subtract(0,0,moArrows.doombringerRadius));
		}
		arrow.getWorld().createExplosion(location, moArrows.doombringerRadius);
		arrow.remove();
		Player player = (Player) arrow.getShooter();
		player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, moArrows.doomPoisonSec, 1));
	}

	public void onGroundHitEvent(Arrow arrow) {
		Location location = arrow.getLocation();
		
		if (moArrows.hookHandler.canHit(location)) {
			arrow.getWorld().strikeLightning(arrow.getLocation());
		}
		if (moArrows.hookHandler.canHit(location)) {
			arrow.getWorld().strikeLightning(arrow.getLocation().add(moArrows.doombringerRadius,0,0));
		}
		if (moArrows.hookHandler.canHit(location)) {
			arrow.getWorld().strikeLightning(arrow.getLocation().subtract(moArrows.doombringerRadius,0,0));
		}
		if (moArrows.hookHandler.canHit(location)) {
			arrow.getWorld().strikeLightning(arrow.getLocation().add(0,0,moArrows.doombringerRadius));
		}
		if (moArrows.hookHandler.canHit(location)) {
			arrow.getWorld().strikeLightning(arrow.getLocation().subtract(0,0,moArrows.doombringerRadius));
		}
		arrow.getWorld().createExplosion(location, moArrows.doombringerRadius);
		arrow.remove();
		Player player = (Player) arrow.getShooter();
		player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, moArrows.doomPoisonSec, 1));
	}
	
}

