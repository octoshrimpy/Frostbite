package com.altechmc.plugins.frostbite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.NotNull;

@Entity
@Table(name="mz_player")
public class HeatHandler {
    
    @Id
    int id;
    @NotNull
    private int heat;
    @NotNull
    private int maxheat;
    @NotNull
    private boolean immune;
    @NotNull
    String UUID;
    
    private Player player;
    
    private static HashMap<Player, HeatHandler> playerMap = new HashMap<Player, HeatHandler>();
    
    public HeatHandler(Player p){
        heat = 100;
        maxheat = 100;
        this.player = p;
        playerMap.put(this.player, this);
        updateArmorRating();
        immune = false;
        UUID = p.getUniqueId().toString();
    }
    
    public static void addHandler(HeatHandler hh, Player p){
        playerMap.put(p, hh);
    }
    
    public static HeatHandler getHandlerByPlayer(Player p){
        return playerMap.get(p);
    }
    
    public void setImmune(boolean im){
        this.immune = im;
    }
    
    public void setHeat(int heat){
        this.heat = heat;
        HeatChangedEvent hc = new HeatChangedEvent(this);
        Bukkit.getPluginManager().callEvent(hc);
    }
    
    public void setMaxHeat(int mh){
        this.maxheat = mh;
        HeatChangedEvent hc = new HeatChangedEvent(this);
        Bukkit.getPluginManager().callEvent(hc);
    }
    
    public int getHeat(){
        return heat;
    }
    
    public int getMaxHeat(){
        return maxheat;
    }
    
    public int addHeat(int heat){
        this.heat += heat;
        HeatChangedEvent hc = new HeatChangedEvent(this);
        Bukkit.getPluginManager().callEvent(hc);
        return heat;
    }
    
    public int removeHeat(int heat){
        this.heat -= heat;
        HeatChangedEvent hc = new HeatChangedEvent(this);
        Bukkit.getPluginManager().callEvent(hc);
        return heat;
    }
    
    public int getNetHeat(){
        ConfigHandler config = Frostbite.getInstance().getConfigHandler();
        
        int netheat = 0;
        int range = config.maxRange;
        List<Block> s = new ArrayList<Block>();
        for(int x = range * -1; x < range; x++){
            for(int y = range * -1; y < range; y++){
                for(int z = range * -1; z < range; z++){
                   s.add(player.getLocation().add(x,y,z).getBlock());
                }
            }
        }
        for(Block b : s){
            if(config.heatblocks.containsKey(b.getType())){
                netheat += config.heatblocks.get(b.getType())[0];
            }
            if(config.coolblocks.containsKey(b.getType())){
                netheat -= config.coolblocks.get(b.getType())[0];
            }
        }
        
        return netheat;
    }
    
    public int tickHeat(){
        if(!immune){
            return addHeat(getNetHeat());
        }else{
            return maxheat;
        }
    }
    
    public void updateArmorRating(){
        //TODO: Get armor and set max heat
    }
    
    public Player getPlayer(){
        return player;
    }

}
