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
		if(stat.getStat() < stat.getHandlerByPlayer(stat.getPlayer()).getEffectLvl()){
			for(PotionEffect e : stat.getNegativeEffects()){
				stat.getPlayer().addPotionEffect(e, false);
			}
		}
	}
	
	public static boolean compareArmor(Item armor, ItemStack item){
		//TODO: Compare Armor
		return false;
	}

}
