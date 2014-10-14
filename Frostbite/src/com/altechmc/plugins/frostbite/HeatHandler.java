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
@Table(name="frostbite_heat")
public class HeatHandler extends PlayerHandler {

    public HeatHandler(Player p) {
        super(p);
    }
    
    @Override
    public int getNet(){
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
        	double dist = b.getLocation().distance(player.getLocation());
            if(config.heatblocks.containsKey(b.getType())){
            	if(dist == config.heatblocks.get(b.getType())[1]){
            		netheat += config.heatblocks.get(b.getType())[0];
            	}
            }
            if(config.coolblocks.containsKey(b.getType())){
            	if(dist == config.coolblocks.get(b.getType())[1]){
            		netheat -= config.coolblocks.get(b.getType())[0];
            	}
            }
        }

        return netheat;
    }
    

    public static void addHandler(HeatHandler hh, Player p){
        playerMap.put(p, hh);
    }
    

    public static HeatHandler getHandlerByPlayer(Player p){
        return (HeatHandler) playerMap.get(p);
    }


}
