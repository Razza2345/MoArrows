package com.gmail.l0g1clvl.MoArrows;

import org.bukkit.entity.Player;
import com.gmail.l0g1clvl.MoArrows.MoArrows.ArrowType;


public class CooldownID {
	private MoArrows moArrows = MoArrows.moArrows;
	
	long time = 0;
	ArrowType type = ArrowType.Normal;
	long cooldown = 0;
	
	public CooldownID(Player p, ArrowType t, long time) {
		this.time = time;
		this.type = t;

		switch (type) {
		case Normal : this.cooldown = 0;
		break;
		case Doombringer : this.cooldown = moArrows.doombringerCooldown*10;
		break;
		case Explosive : this.cooldown = moArrows.explosiveCooldown*10;
		break;
		case Compression : this.cooldown = moArrows.compressionCooldown*10;
		break;
		case Fire : this.cooldown = moArrows.fireCooldown*10;
		break;
		case Lightning : this.cooldown = moArrows.lightningCooldown*10;
		break;
		case Net : this.cooldown = moArrows.netCooldown*10;
		break;
		case Piercing : this.cooldown = moArrows.piercingCooldown*10;
		break;
		case Poison : this.cooldown = moArrows.poisonCooldown*10;
		break;
		case Razor : this.cooldown = moArrows.razorCooldown*10;
		break;
		case Slow : this.cooldown = moArrows.slowCooldown*10;
		break;
		case Teleport : this.cooldown = moArrows.teleportCooldown*10;
		break;
		}
	}
}
