package com.gmail.l0g1clvl.MoArrows;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;


/**
 * Handles external plugin integration
 * @author ayan4m1
 */
public class MoArrowsServerListener implements Listener {
	private MoArrows plugin;

	@EventHandler
	public void onPluginDisable(PluginDisableEvent event) {
//		if (plugin.iconomy != null) {
//			if (event.getPlugin().getDescription().getName().equals("iConomy")) {
//				plugin.iconomy = null;
//				plugin.log.info("MultiArrow unhooked from iConomy.");
//			}
//		}
	}

	@EventHandler
	public void onPluginEnable(PluginEnableEvent event) {
//		if (plugin.iconomy == null) {
//			Plugin iConomy = plugin.getServer().getPluginManager().getPlugin("iConomy");
//
//			if (iConomy != null) {
//				if (iConomy.isEnabled()	&& iConomy.getClass().getName().equals("com.iConomy.iConomy")) {
//					plugin.iconomy = (iConomy)iConomy;
//					plugin.log.info("MultiArrow hooked into iConomy.");
//				}
//			}
//		}
	}
}

