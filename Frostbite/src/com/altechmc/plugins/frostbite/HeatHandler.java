package com.altechmc.plugins.frostbite;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class HeatHandler {
    
    private int heat;
    private int maxheat;
    private Player player;
    
    public HeatHandler(Player p){
        heat = 100;
        maxheat = 100;
        this.player = p;
    }
    
    
    public void setHeat(int heat){
        this.heat = heat;
    }
    
    public void setMaxHeat(int mh){
        this.maxheat = mh;
    }
    
    public int getHeat(){
        return heat;
    }
    
    public int getMaxHeat(){
        return maxheat;
    }
    
    public int addHeat(int heat){
        this.heat += heat;
        return heat;
    }
    
    public int removeHeat(int heat){
        this.heat -= heat;
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
        return addHeat(getNetHeat());
    }

}
