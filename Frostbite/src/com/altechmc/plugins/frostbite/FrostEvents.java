package com.altechmc.plugins.frostbite;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FrostEvents implements Listener{

    Frostbite instance;
    
    public FrostEvents() {
        instance = Frostbite.getInstance();
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        new HeatHandler(e.getPlayer());
        //TODO: Setup XP visual codes
    }

}
