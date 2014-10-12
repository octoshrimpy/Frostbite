package com.altechmc.plugins.frostbite;

import java.util.logging.Handler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler {

    public static boolean executeCommand(String cmd, String args[], CommandSender sender){
        if(!cmd.equalsIgnoreCase("ws")) return false;

        if(args.length == 0){
            sender.sendMessage(ChatColor.GOLD + "Possible commands are:");
            sender.sendMessage(ChatColor.GOLD + "/ws temp " + ChatColor.BLUE + "Shows current temperature");
            sender.sendMessage(ChatColor.GOLD + "/ws temp auto [Threshold] " + ChatColor.BLUE + "Sets temperature notification threshold.");
            return false;
        }
        if(args[0].equalsIgnoreCase("temp")){
            if(!(sender instanceof Player)){
                sender.sendMessage(ChatColor.RED + "Only players can run this command");
                return false;
            }
            if(args.length == 1){            
                sender.sendMessage(ChatColor.BLUE + "Your temperature is " + HeatHandler.getHandlerByPlayer((Player)sender).getHeat());
            }else if(args.length == 3){
                if(args[1].equalsIgnoreCase("auto")){
                    int thresh = Integer.parseInt(args[2]);
                    sender.sendMessage(ChatColor.BLUE + "Notification threshold set to " + thresh);
                    HeatHandler.getHandlerByPlayer((Player)sender).setThreshold(thresh);
                }else{
                    sender.sendMessage(ChatColor.RED + "Invalid syntax.");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Invalid syntax.");
            }
            
        }else if(args[0].equalsIgnoreCase("admin")){
            if(!sender.hasPermission("ws.admin")){
                sender.sendMessage(ChatColor.RED + "You do not have permission to run this command.");
            }
            sender.sendMessage(ChatColor.GOLD + "Possible commands are:");
            sender.sendMessage(ChatColor.GOLD + "/ws admin " + ChatColor.BLUE + "Shows current temperature");
            sender.sendMessage(ChatColor.GOLD + "/ws warm [Name] " + ChatColor.BLUE + "Sets heat of player to player's max heat");
            sender.sendMessage(ChatColor.GOLD + "/ws setmaxheat [Name] [Max Heat] " + ChatColor.BLUE + "Sets max heat for the player");
            sender.sendMessage(ChatColor.GOLD + "/ws enable [Name] " + ChatColor.BLUE + "Enables frostbite for the player");
            sender.sendMessage(ChatColor.GOLD + "/ws disable [Name] " + ChatColor.BLUE + "Disabled frostbite for the plyer");
          
        }else if(args[0].equalsIgnoreCase("warm")){
            if(args.length == 2){
                Player p = Bukkit.getPlayer(args[1]);
                if(p == null){
                    sender.sendMessage(ChatColor.RED + "Invalid player.");
                    return false;
                }
                sender.sendMessage(ChatColor.BLUE + p.getDisplayName() + " warmed.");
                HeatHandler handle = HeatHandler.getHandlerByPlayer(p);
                handle.setHeat(handle.getMaxHeat());
            }else{
                sender.sendMessage(ChatColor.RED + "Invalid syntax.");
            }
        }else if(args[0].equalsIgnoreCase("setmaxheat")){
            if(args.length == 3){
                Player p = Bukkit.getPlayer(args[1]);
                int mh = Integer.parseInt(args[2]);
                if(p == null){
                    sender.sendMessage(ChatColor.RED + "Invalid player.");
                    return false;
                }
                sender.sendMessage(ChatColor.BLUE + p.getDisplayName() + "'s max heat set to " + mh);
                HeatHandler handle = HeatHandler.getHandlerByPlayer(p);
                handle.setMaxHeat(mh);
            }else{
                sender.sendMessage(ChatColor.RED + "Invalid syntax.");
            }
        }else if(args[0].equalsIgnoreCase("enable")){
            if(args.length == 2){
                Player p = Bukkit.getPlayer(args[1]);
                if(p == null){
                    sender.sendMessage(ChatColor.RED + "Invalid player.");
                    return false;
                }
                sender.sendMessage(ChatColor.BLUE + "Frostbite enabled for " + p.getDisplayName());
                HeatHandler handle = HeatHandler.getHandlerByPlayer(p);
                handle.setImmune(false);
            }else{
                sender.sendMessage(ChatColor.RED + "Invalid syntax.");
            }
        }else if(args[0].equalsIgnoreCase("disable")){
            if(args.length == 2){
                Player p = Bukkit.getPlayer(args[1]);
                if(p == null){
                    sender.sendMessage(ChatColor.RED + "Invalid player.");
                    return false;
                }
                sender.sendMessage(ChatColor.BLUE + "Frostbite disabled for " + p.getDisplayName());
                HeatHandler handle = HeatHandler.getHandlerByPlayer(p);
                handle.setImmune(true);
            }else{
                sender.sendMessage(ChatColor.RED + "Invalid syntax.");
            }
        }else{
            sender.sendMessage(ChatColor.RED + "Invalid command.");
        }
        return false;
    }

}
