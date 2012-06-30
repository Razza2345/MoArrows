package com.gmail.l0g1clvl.MoArrows;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.l0g1clvl.MoArrows.MoArrows.ArrowType;

public class CommandHandler implements CommandExecutor {
	private MoArrows moArrows = MoArrows.moArrows;
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		//MaterialHandler matHandler = new MaterialHandler(moArrows);
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("moarrows") && sender instanceof Player){ // If the player typed /basic then do the following...
				if (args.length >= 1) {
					if (args[0].equalsIgnoreCase("list") && player.hasPermission("moarrows.list")) {
						sender.sendMessage(ChatColor.GREEN + "---------------------------");
						sender.sendMessage(ChatColor.GREEN + "Base bow damage multiplier: *" + moArrows.baseDamageMultiplier);
						sender.sendMessage(ChatColor.GREEN + "Crouch damage multiplier: *" + moArrows.baseCrouchMultiplier);
						sender.sendMessage(ChatColor.GREEN + "Crit damage multiplier: *" + moArrows.baseCritMultiplier);
						sender.sendMessage(ChatColor.GREEN + "Crit chance percentage: " + moArrows.baseCritChance + "%");
						sender.sendMessage(ChatColor.GREEN + "Massive damage multiplier: *" + moArrows.baseMassiveMultiplier);
						sender.sendMessage(ChatColor.GREEN + "Massive chance percentage: " + moArrows.baseMassiveChance + "%");
						sender.sendMessage(ChatColor.GREEN + "Allow critical hits: " + moArrows.allowCrits);
						sender.sendMessage(ChatColor.GREEN + "Allow armor penalty: " + moArrows.allowArmorPenalty);
						sender.sendMessage(ChatColor.GREEN + "---------------------------");
					} else if (args[0].equalsIgnoreCase("help") && player.hasPermission("moarrows.help")) {
						
						if (args.length == 2) {
							
							//------------------arrow help code----------------------
							if (args[1].equalsIgnoreCase("normal") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Normal arrow: The standard minecraft arrow.");
								
							} else if (args[1].equalsIgnoreCase("razor") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Razor arrow: Doubles your chances of critical or massive hit.");
								ItemStack stack[] = moArrows.removedItemStacks.get("razor");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("explosive") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Explosive arrow: Explodes on impact.");
								ItemStack stack[] = moArrows.removedItemStacks.get("explosive");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("compression") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Compression arrow: An explosive arrow, but without the block damage.");
								ItemStack stack[] = moArrows.removedItemStacks.get("compression");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("lightning") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Lightning arrow: Summons lightning to arrow's impact location.");
								ItemStack stack[] = moArrows.removedItemStacks.get("lightning");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("net") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Net arrow: Surrounds the target in cobwebs.");
								ItemStack stack[] = moArrows.removedItemStacks.get("net");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("fire") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Fire arrow: Ignites the target on impact.");
								ItemStack stack[] = moArrows.removedItemStacks.get("fire");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("slow") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Slow arrow: Slows the target on impact.");
								ItemStack stack[] = moArrows.removedItemStacks.get("slow");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("poison") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Poison arrow: Poisons the target on impact.");
								ItemStack stack[] = moArrows.removedItemStacks.get("poison");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("teleport") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Teleport arrow: Teleports you to where the arrow lands.");
								ItemStack stack[] = moArrows.removedItemStacks.get("teleport");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("doombringer") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Doombringer: Unleash mass destruction.");
								ItemStack stack[] = moArrows.removedItemStacks.get("doombringer");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else if (args[1].equalsIgnoreCase("piercing") && player.hasPermission("moarrows.help")) {
								sender.sendMessage(ChatColor.GREEN + "Piercing: Completely negates armor.");
								ItemStack stack[] = moArrows.removedItemStacks.get("piercing");
								String s = "";
								if (stack[0].getTypeId() == 0) {
									s += "None";
								} else {
									for (int i = 0; i < stack.length; i++) {
										if (stack[i] != null)
											s += stack[i].getType() + ":" + stack[i].getDurability()+ "x" + stack[i].getAmount() + " ";
									}
								}
								sender.sendMessage(ChatColor.GREEN + "Required materials: " + s);
								
							} else {
								sender.sendMessage(ChatColor.RED + "Unknown arrow type.");
								sender.sendMessage(ChatColor.RED + "Usage: /moarrows help [arrowtype]");
							}
							
						} else {
							sender.sendMessage(ChatColor.GREEN + "----Command reference----");
							sender.sendMessage(ChatColor.GREEN + "/moarrows list - lists all config values");
							sender.sendMessage(ChatColor.GREEN + "/moarrows help - shows this page");
							sender.sendMessage(ChatColor.GREEN + "/moarrows help [arrowtype] - shows arrow information");
							sender.sendMessage(ChatColor.GREEN + "/moarrows version - displays MoArrows version");
							sender.sendMessage(ChatColor.GREEN + "-------------------------");
						}
						
					} else if (args[0].equalsIgnoreCase("version") && player.hasPermission("moarrows.help")) {
						sender.sendMessage(ChatColor.GREEN + "This server is currently running MoArrows v" + moArrows.getDescription().getVersion());
					} else if (args[0].equalsIgnoreCase("reload") && player.hasPermission("moarrows.reload")) {
						moArrows.reloadConfig();
						if (moArrows.configHandler.getConfig()) {
							moArrows.configHandler.getValues();
							sender.sendMessage(ChatColor.GREEN + "MoArrows config values successfully reloaded.");
						} else {
							sender.sendMessage(ChatColor.RED + "Error reloading MoArrows config values!");
						}
					} 
					
					
					
					else if (args[0].equalsIgnoreCase("Compression") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Compression)) {
							int i = tempList.indexOf(ArrowType.Compression);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Compression arrow enabled.");
						} else {
							tempList.add(ArrowType.Compression);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Compression arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Doombringer") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Doombringer)) {
							int i = tempList.indexOf(ArrowType.Doombringer);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Doombringer arrow enabled.");
						} else {
							tempList.add(ArrowType.Doombringer);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Doombringer arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Explosive") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Explosive)) {
							int i = tempList.indexOf(ArrowType.Explosive);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Explosive arrow enabled.");
						} else {
							tempList.add(ArrowType.Explosive);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Explosive arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Fire") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Fire)) {
							int i = tempList.indexOf(ArrowType.Fire);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Fire arrow enabled.");
						} else {
							tempList.add(ArrowType.Fire);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Fire arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Lightning") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Lightning)) {
							int i = tempList.indexOf(ArrowType.Lightning);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Lightning arrow enabled.");
						} else {
							tempList.add(ArrowType.Lightning);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Lightning arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Net") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Net)) {
							int i = tempList.indexOf(ArrowType.Net);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Net arrow enabled.");
						} else {
							tempList.add(ArrowType.Net);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Net arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Piercing") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Piercing)) {
							int i = tempList.indexOf(ArrowType.Piercing);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Piercing arrow enabled.");
						} else {
							tempList.add(ArrowType.Piercing);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Piercing arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Poison") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Poison)) {
							int i = tempList.indexOf(ArrowType.Poison);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Poison arrow enabled.");
						} else {
							tempList.add(ArrowType.Poison);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Poison arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Razor") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Razor)) {
							int i = tempList.indexOf(ArrowType.Razor);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Razor arrow enabled.");
						} else {
							tempList.add(ArrowType.Razor);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Razor arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Slow") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Slow)) {
							int i = tempList.indexOf(ArrowType.Slow);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Slow arrow enabled.");
						} else {
							tempList.add(ArrowType.Slow);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Slow arrow disabled.");
						}
					} else if (args[0].equalsIgnoreCase("Teleport") && sender instanceof Player) {
						List tempList = moArrows.removedTypes.get(sender);
						if (tempList.contains(ArrowType.Teleport)) {
							int i = tempList.indexOf(ArrowType.Teleport);
							tempList.remove(i);
							sender.sendMessage(ChatColor.GREEN + "Teleport arrow enabled.");
						} else {
							tempList.add(ArrowType.Teleport);
							moArrows.removedTypes.put((Player)sender, tempList);
							sender.sendMessage(ChatColor.YELLOW + "Teleport arrow disabled.");
						}
					} 
					
					
					
					else {
						sender.sendMessage(ChatColor.RED + "MoArrows command error: command not recognized");
						sender.sendMessage(ChatColor.RED + "Do you have permission to use this?");
					}
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "MoArrows command error: too few arguments");
					sender.sendMessage(ChatColor.RED + "See /ma help for more info.");
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isInteger( String input )  
	{  
	   try  
	   {  
	      Integer.parseInt( input );  
	      return true;  
	   }  
	   catch( Exception e )  
	   {  
	      return false;  
	   }  
	} 
	
	public boolean isDouble( String input )  
	{  
	   try  
	   {  
	      Double.parseDouble( input );  
	      return true;  
	   }  
	   catch( Exception e )  
	   {  
	      return false;  
	   }  
	} 
	
	public boolean isBoolean( String input )  
	{  
	   try  
	   {  
	      Boolean.parseBoolean( input );  
	      return true;  
	   }  
	   catch( Exception e )  
	   {  
	      return false;  
	   }  
	} 
}
