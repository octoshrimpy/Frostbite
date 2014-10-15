package com.altechmc.plugins.frostbite;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FrostEvents implements Listener{

    Frostbite instance;
    
    public FrostEvents() {
        instance = Frostbite.getInstance();
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        
        HeatHandler hnd = (HeatHandler)instance.getDatabase().find(HeatHandler.class).where().ieq("UUID", e.getPlayer().getUniqueId().toString()).findUnique();
        if(hnd == null){
            HeatHandler.addHandler(hnd, e.getPlayer());
        }else{
            HeatHandler newp = new HeatHandler(e.getPlayer());
            instance.getDatabase().save(newp);
        }
    }
    
    public void onPlayerLeave(PlayerQuitEvent e){
        instance.getDatabase().save(HeatHandler.getHandlerByPlayer(e.getPlayer()));
    }
    
    @EventHandler
    public void onHeatChanged(PlayerStatUpdatedEvent e){
        Player p = e.getPlayer();
        int heat = e.getStatHandle().getStat();

        Util.updateXPBar(e.getStatHandle());
        Util.updatePlayerEffects(e.getStatHandle());
    }
    
   
    
    //TODO: Stop outside XP sources

}
