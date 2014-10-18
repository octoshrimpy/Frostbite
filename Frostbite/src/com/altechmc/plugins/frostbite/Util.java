package com.altechmc.plugins.frostbite;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class Util {
	
	public static void updateXPBar(PlayerHandler stat){
		int max = stat.getMaxStat();
		int val = stat.getStat();
		float div = val/max;
		stat.getPlayer().setExp(div);
		
	}
	
	public static void updatePlayerEffects(PlayerHandler stat){

	    for(PotionEffect e : stat.getNegativeEffects().keySet()){
	        if(stat.getStat() < stat.getHandlerByPlayer(stat.getPlayer()).getEffectLvl(e)){
	            stat.getPlayer().addPotionEffect(e, false);
	        }
	    }
	}
	
	public static boolean compareArmor(ItemStack armor, ItemStack item){
		ItemStack ai = armor;
		boolean name = ai.getItemMeta().getDisplayName().equalsIgnoreCase(item.getItemMeta().getDisplayName());
		boolean lore = ai.getItemMeta().getLore().containsAll(item.getItemMeta().getLore());
		boolean itid = ai.getType() == item.getType();
		boolean valm = ai.getDurability() == item.getDurability();
		return name && lore && itid && valm;
		
	}

}
