package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Effect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class SlowArrowEffect implements ArrowEffect {

private MoArrows plugin;
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		LivingEntity e = (LivingEntity) target;
		e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 4));
	}

	public void onGroundHitEvent(Arrow arrow) {
		Player player = (Player) arrow.getShooter();
		player.playEffect(arrow.getLocation(), Effect.POTION_BREAK, 4);
	}
	
}
