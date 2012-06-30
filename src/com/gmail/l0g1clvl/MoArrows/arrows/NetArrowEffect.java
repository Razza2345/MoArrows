package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.gmail.l0g1clvl.MoArrows.MoArrows;
import com.gmail.l0g1clvl.MoArrows.SmokeEffect;

public class NetArrowEffect implements ArrowEffect {
	private MoArrows moArrows = MoArrows.moArrows;
	private Block[] changedBlocks;
	private int blockIndex;

	public void onEntityHitEvent(Arrow arrow, Entity target) {
		Location blockLoc = target.getLocation();
		Block b = (Block) blockLoc.getBlock();
		changedBlocks = new Block[20];
		blockIndex = 0;
		
		//Let's make sure the location is on the ground //
		while (b.getType() == Material.AIR) {			//
			blockLoc.subtract(0,1,0);					//
			b = (Block) blockLoc.getBlock();			//
		}												//
		blockLoc.add(0,1,0);							//
		b = (Block) blockLoc.getBlock();				//
		
		if (b.getType() == Material.AIR) {
			b.setTypeId(30, true); 
			changedBlocks[blockIndex] = b; 
			blockIndex++;
		}
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.add(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.subtract(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.subtract(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.subtract(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.subtract(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
			
		BlockRemove br = new BlockRemove(changedBlocks);
		br.setId(Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(moArrows, br, moArrows.netSec));
		
	}

	public void onGroundHitEvent(Arrow arrow) {
		Location blockLoc = arrow.getLocation();
		Block b = (Block) blockLoc.getBlock();
		changedBlocks = new Block[20];
		blockIndex = 0;
		
		//Let's make sure the location is on the ground //
		while (b.getType() == Material.AIR) {			//
			blockLoc.subtract(0,1,0);					//
			b = (Block) blockLoc.getBlock();			//
		}												//
		blockLoc.add(0,1,0);							//
		b = (Block) blockLoc.getBlock();				//
		
		if (b.getType() == Material.AIR) {
			b.setTypeId(30, true); 
			changedBlocks[blockIndex] = b; 
			blockIndex++;
		}
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.add(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.subtract(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.subtract(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.subtract(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.subtract(0,0,1);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}
		blockLoc.add(1,0,0);
		b = (Block) blockLoc.getBlock();
		if (b.getType() == Material.AIR) {
			if (b.getRelative(0,-1,0).getType() == Material.AIR) {
				b.getRelative(0,-1,0).setTypeId(30, true);
				changedBlocks[blockIndex] = b.getRelative(0,-1,0); 
				blockIndex++;
			} else {
				b.setTypeId(30, true); 
				changedBlocks[blockIndex] = b; 
				blockIndex++;
			}
		}

		BlockRemove br = new BlockRemove(changedBlocks);
		br.setId(Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(moArrows, br, moArrows.netSec));
			
	}
	
}

