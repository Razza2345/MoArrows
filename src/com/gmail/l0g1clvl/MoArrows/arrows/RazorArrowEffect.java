package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.Effect;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class RazorArrowEffect implements ArrowEffect {

private MoArrows plugin;
	
	public void onEntityHitEvent(Arrow arrow, Entity target) {
		LivingEntity e = (LivingEntity) target;
//		plugin.tempCritChance = plugin.baseCritChance*2;
//		plugin.tempMassiveChance = plugin.baseMassiveChance*2;
	}

	public void onGroundHitEvent(Arrow arrow) {
		Player player = (Player) arrow.getShooter();
	}
	
}