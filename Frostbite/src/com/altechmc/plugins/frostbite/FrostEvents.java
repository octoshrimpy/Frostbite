package com.altechmc.plugins.frostbite;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class FrostEvents implements Listener{

    Frostbite instance;
    
    public FrostEvents() {
        instance = Frostbite.getInstance();
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        new HeatHandler(e.getPlayer());
        //e.getPlayer().setExp(100);
    }
    
    @EventHandler
    public void onHeatChanged(HeatChangedEvent e){
        Player p = e.getPlayer();
        int heat = e.getHeatHandle().getHeat();
        //TODO: Setup XP visual codes
        //TODO: Setup negative effects
    }
    
   
    
    //TODO: Stop outside XP sources

}
