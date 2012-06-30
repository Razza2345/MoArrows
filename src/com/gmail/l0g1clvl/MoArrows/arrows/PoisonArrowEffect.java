package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.*;
import org.bukkit.Effect;
import org.bukkit.event.Event;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class PoisonArrowEffect implements ArrowEffect {
	private MoArrows moArrows = MoArrows.moArrows;
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		LivingEntity e = (LivingEntity) target;
		e.addPotionEffect(new PotionEffect(PotionEffectType.POISON, moArrows.poisonSec, 1));
	}

	public void onGroundHitEvent(Arrow arrow) {
	}
		
}
