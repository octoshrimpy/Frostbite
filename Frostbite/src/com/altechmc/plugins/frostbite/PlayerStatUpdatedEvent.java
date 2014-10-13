package com.altechmc.plugins.frostbite;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class PlayerStatUpdatedEvent extends Event{

    private static final HandlerList handlers = new HandlerList();
    private PlayerHandler heats;
    
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public PlayerStatUpdatedEvent(PlayerHandler heath){
        this.heats = heath;
    }
    
    public Player getPlayer(){
        return heats.getPlayer();
    }
    
    public PlayerHandler getHeatHandle(){
        return heats;
    }
    
    

}
