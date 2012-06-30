package com.gmail.l0g1clvl.MoArrows;

import org.bukkit.entity.Player;
import com.gmail.l0g1clvl.MoArrows.MoArrows.ArrowType;

public class ArrowID {
	private MoArrows moArrows = MoArrows.moArrows;
	
	int id = 0;
	ArrowType type = ArrowType.Normal;
	Player shooter = null;
	boolean isCrouching = false;
	
	public ArrowID(int id, ArrowType type, double reduction,
			Player shooter, boolean isCrouching) {
		this.id = id;
		this.type = type;
		this.shooter = shooter;
		this.isCrouching = isCrouching;
	}
	
}
