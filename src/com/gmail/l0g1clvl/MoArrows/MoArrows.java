package com.gmail.l0g1clvl.MoArrows;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import com.gmail.l0g1clvl.MoArrows.arrows.ArrowType;

/**
 * MoArrows for Bukkit (originally MultiArrow)
 * @author MrAverage with code from ayan4m1
 */
public class MoArrows extends JavaPlugin {

    public static Logger log;
    public static Map<Player, ArrowType> activeArrowType;
    public ConfigHandler config;
    public static int removedArrows[] = new int[10];

	public MoArrows() {
		this.log = Logger.getLogger("minecraft");
		this.activeArrowType = new HashMap<Player, ArrowType>();
	}

	public String toProperCase(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

	public void onEnable() {
		this.config = new ConfigHandler(this);
		getServer().getPluginManager().registerEvents(new MoArrowsPlayerListener(), this);
		getServer().getPluginManager().registerEvents(new MoArrowsEntityListener(), this);
		getServer().getPluginManager().registerEvents(new MoArrowsServerListener(), this);

		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled!");
		
//		for (int i = 0; i < 10; i++) {
//			if (com.gmail.l0g1clvl.MoArrows.ConfigHandler.getArrowRemove(ArrowType.Water)) {
//				removedArrows[i] = ArrowType.values()[i].ordinal();
//				String str = ("removed: " + removedArrows[i]);
//				log.info(str);
//			}
//			String str = ("removed: " + com.gmail.l0g1clvl.MoArrows.ConfigHandler.getArrowRemove(ArrowType.values()[i]));
//			log.info(str);
//		}
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " shutting down.");
	}
}

