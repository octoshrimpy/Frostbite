package com.altechmc.plugins.frostbite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Id;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.avaje.ebean.validation.NotNull;

public abstract class PlayerHandler {

    @Id
    int id;
    @NotNull
    protected int stat;
    @NotNull
    protected int maxstat;
    @NotNull
    protected boolean immune;
    @NotNull
    String UUID;
    @NotNull
    int threshold;
    
    protected Player player;
    public EnvTypes type;
    
    protected static HashMap<Player, PlayerHandler> playerMap = new HashMap<Player, PlayerHandler>();
    
    public PlayerHandler(Player p){
        stat = 100;
        maxstat = 100;
        this.player = p;
        playerMap.put(this.player, this);
        updateArmorRating();
        immune = false;
        UUID = p.getUniqueId().toString();
    }
    
    public abstract List<PotionEffect> getNegativeEffects();
    
    public EnvTypes getType(){
    	return type;
    }
    
    public int getEffectLvl(){
    	return Frostbite.getInstance().getConfigHandler().mins.get(this.getType());
    }
    
    public float getAdvancement(){
    	return stat/maxstat;
    }
    
    public void setThreshold(int thresh){
        this.threshold = thresh;
    }
    
    public int getThreshold(){
        return threshold;
    }
    
    public void setImmune(boolean im){
        this.immune = im;
    }
    
    public boolean getImmune(){
        return immune;
    }
    
    
    public static void addHandler(PlayerHandler hh, Player p){
        playerMap.put(p, hh);
    }
    
    public static PlayerHandler getHandlerByPlayer(Player p){
        return playerMap.get(p);
    }
    
    
    public void setStat(int heat){
        this.stat = heat;
        PlayerStatUpdatedEvent hc = new PlayerStatUpdatedEvent(this);
        Bukkit.getPluginManager().callEvent(hc);
    }
    
    public void setMaxStat(int mh){
        this.maxstat = mh;
        PlayerStatUpdatedEvent hc = new PlayerStatUpdatedEvent(this);
        Bukkit.getPluginManager().callEvent(hc);
    }
    
    public int getStat(){
        return stat;
    }
    
    public int getMaxStat(){
        return maxstat;
    }
    
    public int addStat(int heat){
        this.stat += heat;
        PlayerStatUpdatedEvent hc = new PlayerStatUpdatedEvent(this);
        Bukkit.getPluginManager().callEvent(hc);
        return heat;
    }
    
    public int removeStat(int heat){
        this.stat -= heat;
        PlayerStatUpdatedEvent hc = new PlayerStatUpdatedEvent(this);
        Bukkit.getPluginManager().callEvent(hc);
        return heat;
    }
    
    public abstract int getNet();
    
    public int tickStat(){
        if(!immune){
            return addStat(getNet());
        }else{
            return maxstat;
        }
    }
    
    public void updateArmorRating(){
    	HashMap<ArmorLevel, List<Item>> hmap = Frostbite.getInstance().getConfigHandler().armor.get(this.getType());
    	int rating = 0;
    	for(ArmorLevel lvl : hmap.keySet()){
    		List<Item> items = hmap.get(lvl);
    		for(Item i : items){
    			for(ItemStack is :this.getPlayer().getInventory().getArmorContents()){
    				if(Util.compareArmor(i, is)){
    					rating += Frostbite.getInstance().getConfigHandler().amap.get(lvl);
    				}
    			}
    		}
    	}
    	this.maxstat = Frostbite.getInstance().getConfigHandler().defaultMax.get(this.getType()) + rating;
    }
    
    public Player getPlayer(){
        return player;
    }

}
