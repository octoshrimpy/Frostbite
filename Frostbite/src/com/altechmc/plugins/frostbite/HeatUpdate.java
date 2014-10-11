package com.altechmc.plugins.frostbite;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HeatUpdate implements Runnable {

    public void run() {
        
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            HeatHandler.getHandlerByPlayer(p).tickHeat();
        }
        
    }

    

}
