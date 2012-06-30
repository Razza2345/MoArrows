package com.gmail.l0g1clvl.MoArrows;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;

import com.gmail.l0g1clvl.MoArrows.MoArrows.ArrowType;

public class DamageHandler {
	private MoArrows moArrows = MoArrows.moArrows;
	
	private double damageMultiplier;
	
	//temporary damage variables
    private int tempCritChance;
    private int tempMassiveChance;
    int initialDamage;
    Player player;
	
	public int setDamage (EntityDamageEvent event, ArrowID arrowID, double damageReduction) {
		tempCritChance = 0;
		tempMassiveChance = 0;
		initialDamage = event.getDamage();
		player = arrowID.shooter;
		int critNum = moArrows.randomGenerator.nextInt(100);

		if (arrowID.type == ArrowType.Razor) {
			tempCritChance = (int) (moArrows.baseCritChance * moArrows.razorCritMultiplier);
			tempMassiveChance = (int) (moArrows.baseMassiveChance * moArrows.razorCritMultiplier);
		} else {
			tempCritChance = moArrows.baseCritChance;
			tempMassiveChance = moArrows.baseMassiveChance;
		}

		if (arrowID.isCrouching) {
			if (moArrows.allowCrits
					&& (critNum >= 0 && critNum < tempCritChance) 
					&& arrowID.shooter.hasPermission("moarrows.allowcrit")) {
				damageMultiplier = ((initialDamage
						* moArrows.baseCritMultiplier * moArrows.baseCrouchMultiplier) / damageReduction)
						* moArrows.baseDamageMultiplier;
				if (event.getEntity() instanceof LivingEntity)
					player.sendMessage(ChatColor.YELLOW + "Critical hit!");
			} else if (moArrows.allowCrits
					&& (critNum >= tempCritChance && critNum < (moArrows.baseCritChance + tempMassiveChance) 
					&& arrowID.shooter.hasPermission("moarrows.allowcrit"))) {
				damageMultiplier = ((initialDamage
						* moArrows.baseMassiveMultiplier * moArrows.baseCrouchMultiplier) / damageReduction)
						* moArrows.baseDamageMultiplier;
				if (event.getEntity() instanceof LivingEntity)
					player.sendMessage(ChatColor.RED + "MASSIVE CRIT!");
			} else {
				damageMultiplier = ((initialDamage * moArrows.baseCrouchMultiplier) / damageReduction)
						* moArrows.baseDamageMultiplier;
			}
		} else {
			if (moArrows.allowCrits
					&& (critNum >= 0 && critNum < tempCritChance)
					&& arrowID.shooter.hasPermission("moarrows.allowcrit")) {
				damageMultiplier = ((initialDamage * moArrows.baseCritMultiplier) / damageReduction)
						* moArrows.baseDamageMultiplier;
				if (event.getEntity() instanceof LivingEntity)
					player.sendMessage(ChatColor.YELLOW + "Critical hit!");
			} else if (moArrows.allowCrits
					&& (critNum >= tempCritChance && critNum < (tempCritChance + tempMassiveChance))
					&& arrowID.shooter.hasPermission("moarrows.allowcrit")) {
				damageMultiplier = ((initialDamage * moArrows.baseMassiveMultiplier) / damageReduction)
						* moArrows.baseDamageMultiplier;
				if (event.getEntity() instanceof LivingEntity)
					player.sendMessage(ChatColor.RED + "MASSIVE CRIT!");
			} else {
				damageMultiplier = ((initialDamage) / damageReduction)
						* moArrows.baseDamageMultiplier;
			}
		}
		Entity damaged = event.getEntity();
		if (arrowID.type == ArrowType.Piercing) {
			if (damaged instanceof Player) {
				Player foobar = (Player) damaged;
				if (foobar.getHealth() - (int) (Math.floor(damageMultiplier) * moArrows.piercingMultiplier) > 0) {
					foobar.setHealth(foobar.getHealth()
							- (int) (Math.floor(damageMultiplier) * moArrows.piercingMultiplier));
				} else {
					foobar.setHealth(0);
				}
			}
			return 0;
		} else {
			return ((int) Math.floor(damageMultiplier));
		}
	}
	
	public double getPenalty (Player player) {
		double damageReduction = 0;

		String helm = String.valueOf(player.getInventory().getHelmet());
		String chest = String.valueOf(player.getInventory().getChestplate());
		String pants = String.valueOf(player.getInventory().getLeggings());
		String boots = String.valueOf(player.getInventory().getBoots());

		if (moArrows.allowArmorPenalty) {
			if (helm.contains("DIAMOND_HELMET")) {
				damageReduction += (moArrows.diamondPenalty * 0.20);
			} else if (helm.contains("IRON_HELMET")) {
				damageReduction += (moArrows.ironPenalty * 0.20);
			} else if (helm.contains("GOLD_HELMET")) {
				damageReduction += (moArrows.goldPenalty * 0.20);
			} else if (helm.contains("LEATHER_HELMET")) {
				damageReduction += (moArrows.leatherPenalty * 0.20);
			} else if (helm.contains("CHAINMAIL_HELMET")) {
				damageReduction += (moArrows.leatherPenalty * 0.20);
			} else {
				damageReduction += (moArrows.nakedPenalty * 0.20);
			}
			if (chest.contains("DIAMOND_CHESTPLATE")) {
				damageReduction += (moArrows.diamondPenalty * 0.40);
			} else if (chest.contains("IRON_CHESTPLATE")) {
				damageReduction += (moArrows.ironPenalty * 0.40);
			} else if (chest.contains("GOLD_CHESTPLATE")) {
				damageReduction += (moArrows.goldPenalty * 0.40);
			} else if (chest.contains("LEATHER_CHESTPLATE")) {
				damageReduction += (moArrows.leatherPenalty * 0.40);
			} else if (chest.contains("CHAINMAIL_CHESTPLATE")) {
				damageReduction += (moArrows.leatherPenalty * 0.40);
			} else {
				damageReduction += (moArrows.nakedPenalty * 0.40);
			}
			if (pants.contains("DIAMOND_LEGGINGS")) {
				damageReduction += (moArrows.diamondPenalty * 0.30);
			} else if (pants.contains("IRON_LEGGINGS")) {
				damageReduction += (moArrows.ironPenalty * 0.30);
			} else if (pants.contains("GOLD_LEGGINGS")) {
				damageReduction += (moArrows.goldPenalty * 0.30);
			} else if (pants.contains("LEATHER_LEGGINGS")) {
				damageReduction += (moArrows.leatherPenalty * 0.30);
			} else if (pants.contains("CHAINMAIL_LEGGINGS")) {
				damageReduction += (moArrows.leatherPenalty * 0.30);
			} else {
				damageReduction += (moArrows.nakedPenalty * 0.30);
			}
			if (boots.contains("DIAMOND_BOOTS")) {
				damageReduction += (moArrows.diamondPenalty * 0.10);
			} else if (boots.contains("IRON_BOOTS")) {
				damageReduction += (moArrows.ironPenalty * 0.10);
			} else if (boots.contains("GOLD_BOOTS")) {
				damageReduction += (moArrows.goldPenalty * 0.10);
			} else if (boots.contains("LEATHER_BOOTS")) {
				damageReduction += (moArrows.leatherPenalty * 0.10);
			} else if (boots.contains("CHAINMAIL_BOOTS")) {
				damageReduction += (moArrows.leatherPenalty * 0.10);
			} else {
				damageReduction += (moArrows.nakedPenalty * 0.10);
			}
			return damageReduction;
		} else {
			return 1;
		}
	}
	
}
