package com.gmail.l0g1clvl.MoArrows.arrows;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.l0g1clvl.MoArrows.MoArrows;

public class BlockRemove implements Runnable {
	private MoArrows moArrows = MoArrows.moArrows;
	Block[] b;
	int count = 0;
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BlockRemove(Block[] blockSet) { 
		this.b = blockSet;
	}
	
	@Override
    public void run() {
	    for (int i = 0; i < b.length; i++) {
	    	if (b[i] != null) {
	    		b[i].setTypeId(0, true);
	    	}
	    }
    }
}
