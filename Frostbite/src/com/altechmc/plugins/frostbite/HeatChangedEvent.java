package com.altechmc.plugins.frostbite;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class HeatChangedEvent extends Event{

    private static final HandlerList handlers = new HandlerList();
    private HeatHandler heats;
    
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public HeatChangedEvent(HeatHandler heath){
        this.heats = heath;
    }
    
    public Player getPlayer(){
        return heats.getPlayer();
    }
    
    public HeatHandler getHeatHandle(){
        return heats;
    }
    
    

}
