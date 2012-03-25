package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class NetArrowEffect implements ArrowEffect {
	private MoArrows plugin;

	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Location blockLoc = target.getLocation();
		Block b = (Block) blockLoc.getBlock();
		
		//Let's make sure the location is on the ground //
		while (b.isEmpty()) {							//
			blockLoc.subtract(0,1,0);					//
			b = (Block) blockLoc.getBlock();			//
		}												//
		blockLoc.add(0,1,0);							//
		b = (Block) blockLoc.getBlock();				//
		
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.add(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.subtract(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.subtract(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.subtract(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.subtract(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		
	}

	public void onGroundHitEvent(Arrow arrow) {
		Location blockLoc = arrow.getLocation();
		Block b = (Block) blockLoc.getBlock();
		
		//Let's make sure the location is on the ground //
		while (b.isEmpty()) {							//
			blockLoc.subtract(0,1,0);					//
			b = (Block) blockLoc.getBlock();			//
		}												//
		blockLoc.add(0,1,0);							//
		b = (Block) blockLoc.getBlock();				//
		
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.add(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.subtract(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.subtract(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.subtract(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.subtract(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.isEmpty())
			b.setTypeId(30, true);

	}
	
}

