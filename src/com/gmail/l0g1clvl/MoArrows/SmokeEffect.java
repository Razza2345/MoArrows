package com.gmail.l0g1clvl.MoArrows;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class SmokeEffect implements Runnable {
	private MoArrows moArrows;
	Entity e;
	World w;
	int count = 0;
	UUID wid;
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public SmokeEffect(Entity ent, World world) { 
		this.e = ent;
		this.w = world;
		this.wid = w.getUID();
	}
	
	@Override
    public void run() {
	    if (e != null) {
	    	try {
	    		Bukkit.getServer().getWorld(wid).playEffect(e.getLocation(), Effect.SMOKE, 4);
	    	} catch (Exception ex) {
	    		
	    	}
	    	
	        count++;
	        if (count >= 60) {
	        	Bukkit.getServer().getScheduler().cancelTask(id);
	        }
	    }
    }
}
