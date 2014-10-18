package com.altechmc.plugins.frostbite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConfigHandler {
    int maxRange;
    int verboseRate;
    public HashMap<Material, Integer[]> heatblocks = new HashMap<Material, Integer[]>();
    public HashMap<Material, Integer[]> coolblocks = new HashMap<Material, Integer[]>();
    public HashMap<EnvTypes, HashMap<PotionEffect, Integer>> effects = new HashMap<EnvTypes, HashMap<PotionEffect, Integer>>();
    public HashMap<EnvTypes, HashMap<ItemStack, Integer>> armor = new HashMap<EnvTypes, HashMap<ItemStack, Integer>>();
    
    //Not Implemented
    public HashMap<EnvTypes, Integer> defaultMax = new HashMap<EnvTypes, Integer>();
    
    public ConfigHandler(){
        FileConfiguration conf = Frostbite.getInstance().getConfig();

        List<String> hlist = conf.getStringList("HeatBlocks");
        for(String s: hlist){
            String smat = conf.getString("HeatBlocks." + s + ".Type");
            int samount = conf.getInt("HeatBlocks." + s + ".Rate");
            int srange = conf.getInt("HeatBlocks." + s + ".Range");
            Material mat = Material.getMaterial(smat);
            int amount = samount;
            int range = samount;
            Integer[] dat = {amount, range};
            heatblocks.put(mat, dat);
            if(range > maxRange) maxRange = range;
        }
        
        List<String> clist = conf.getStringList("CoolBlocks");
        for(String s: clist){
            String smat = conf.getString("CoolBlocks." + s + ".Type");
            int samount = conf.getInt("CoolBlocks." + s + ".Rate");
            int srange = conf.getInt("CoolBlocks." + s + ".Range");
            Material mat = Material.getMaterial(smat);
            int amount = samount;
            int range = samount;
            Integer[] dat = {amount, range};
            coolblocks.put(mat, dat);
            if(range > maxRange) maxRange = range;
        }
        List<String> etl = conf.getStringList("Effects");
        for(EnvTypes e : EnvTypes.values()){
        	List<String> efflist = conf.getStringList("Effects." + e.name());
        	HashMap<PotionEffect, Integer> pef = new HashMap<PotionEffect, Integer>();
        	if(!conf.contains("Effects." + e.name())) continue;
        	for(String s: efflist){
        		String type = conf.getString("Effects." + e.name() + "." + s + ".Type");
        		int dur = conf.getInt("Effects." + e.name() + "." + s + ".Duration");
        		int pow = conf.getInt("Effects." + e.name() + "." + s + ".Power");
        		int lvl  = conf.getInt("Effects." + e.name() + "." + s + ".Threshold");
        		PotionEffect effect = new PotionEffect(PotionEffectType.getByName(type),dur,pow);
        		pef.put(effect, lvl);
        	}
        	effects.put(e, pef);
        	
        	defaultMax.put(e, conf.getInt("DefaultMax"));
        }
        verboseRate = conf.getInt("AlertRate");
        
        //Load up Armor information
        List<String> arl = conf.getStringList("Armor");
        for(EnvTypes e : EnvTypes.values()){

            List<String> armlist = conf.getStringList("Armor." + e.name() + ".");
            HashMap<ItemStack, Integer> itls = new HashMap<ItemStack, Integer>();
            if(!conf.contains("Armor." + e.name())) continue;
            for(String s: armlist){
                String id = conf.getString("Armor." + e.name() + "." + "." + s + ".Name");
                int dur = conf.getInt("Armor." + e.name() + "." + "." + s + ".Color");
                int amt = conf.getInt("Armor." + e.name() + "." + "." + s + ".Amount");
                String name = conf.getString("Armor." + e.name() + "." + "." + s + ".Name");
                String lore = conf.getString("Armor." + e.name() + "." + "." + s + ".Lore");
                ItemStack i = new ItemStack(Material.getMaterial(id));
                i.getItemMeta().setDisplayName(name);
                List<String> lorel = new ArrayList<String>();
                lorel.add(lore);
                i.getItemMeta().setLore(lorel);
                LeatherArmorMeta im = (LeatherArmorMeta) i.getItemMeta();
                im.setColor(org.bukkit.Color.fromRGB(dur));
                itls.put(i,amt);
            }
            int amt = conf.getInt("Armor." + e.name() + "." + ".Amount");


            armor.put(e, itls);
        }
        
    }
}
