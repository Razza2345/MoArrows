package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class DoombringerArrowEffect implements ArrowEffect {

	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Location loc = arrow.getLocation();
		arrow.getWorld().strikeLightning(arrow.getLocation());
		arrow.getWorld().strikeLightning(arrow.getLocation().add(10,0,0));
		arrow.getWorld().strikeLightning(arrow.getLocation().subtract(10,0,0));
		arrow.getWorld().strikeLightning(arrow.getLocation().add(0,0,10));
		arrow.getWorld().strikeLightning(arrow.getLocation().subtract(0,0,10));
		arrow.getWorld().createExplosion(loc, 10);
		arrow.remove();
		Player player = (Player) arrow.getShooter();
		player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5000, 1));
	}

	public void onGroundHitEvent(Arrow arrow) {
		Location loc = arrow.getLocation();
		arrow.getWorld().strikeLightning(arrow.getLocation());
		arrow.getWorld().strikeLightning(arrow.getLocation().add(10,0,0));
		arrow.getWorld().strikeLightning(arrow.getLocation().subtract(10,0,0));
		arrow.getWorld().strikeLightning(arrow.getLocation().add(0,0,10));
		arrow.getWorld().strikeLightning(arrow.getLocation().subtract(0,0,10));
		arrow.getWorld().createExplosion(loc, 10);
		arrow.remove();
		Player player = (Player) arrow.getShooter();
		player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5000, 1));
	}
	
}

