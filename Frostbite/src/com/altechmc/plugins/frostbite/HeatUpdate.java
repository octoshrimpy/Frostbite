package com.altechmc.plugins.frostbite;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HeatUpdate implements Runnable {

    int i = 0;
    
    public void run() {
        
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            HeatHandler.getHandlerByPlayer(p).tickHeat();
            if(i == Frostbite.getInstance().getConfigHandler().verboseRate){
                if(HeatHandler.getHandlerByPlayer(p).getHeat() < HeatHandler.getHandlerByPlayer(p).getThreshold()){
                    p.sendMessage(ChatColor.RED + "Your temperature is dopping, you should find a heat source now!");
                }
                i = 0;
            }
        }
        
        i++;
        
    }

    

}
