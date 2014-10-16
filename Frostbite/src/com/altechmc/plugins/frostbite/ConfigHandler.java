package com.altechmc.plugins.frostbite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConfigHandler {
    int maxRange;
    int verboseRate;
    public HashMap<Material, Integer[]> heatblocks = new HashMap<Material, Integer[]>();
    public HashMap<Material, Integer[]> coolblocks = new HashMap<Material, Integer[]>();
    public HashMap<EnvTypes, List<PotionEffect>> effects = new HashMap<EnvTypes, List<PotionEffect>>();
    public HashMap<EnvTypes, Integer> mins = new HashMap<EnvTypes, Integer>();
    public HashMap<EnvTypes, HashMap<ArmorLevel, List<ItemStack>>> armor = new HashMap<EnvTypes, HashMap<ArmorLevel, List<ItemStack>>>();
    
    //Not Implemented
    public HashMap<EnvTypes, Integer> defaultMax = new HashMap<EnvTypes, Integer>();
    public HashMap<ArmorLevel, Integer> amap = new HashMap<ArmorLevel, Integer>();
    
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
        	List<PotionEffect> pef = new ArrayList<PotionEffect>();
        	if(!conf.contains("Effects." + e.name())) continue;
        	for(String s: efflist){
        		String type = conf.getString("Effects." + e.name() + "." + s + ".Type");
        		int dur = conf.getInt("Effects." + e.name() + "." + s + ".Duration");
        		int pow = conf.getInt("Effects." + e.name() + "." + s + ".Power");
        		PotionEffect effect = new PotionEffect(PotionEffectType.getByName(type),dur,pow);
        		pef.add(effect);
        	}
        	effects.put(EnvTypes.FROST, pef);
        	mins.put(EnvTypes.FROST, conf.getInt("Effects.EffectLevel"));
        	defaultMax.put(EnvTypes.FROST, conf.getInt("Effects.DefaultMax"));
        }
        verboseRate = conf.getInt("AlertRate");
        
        //Load up Armor information
        List<String> arl = conf.getStringList("Armor");
        for(EnvTypes e : EnvTypes.values()){
        	HashMap<ArmorLevel, List<ItemStack>> damp = new HashMap<ArmorLevel, List<ItemStack>>();
        	for(ArmorLevel al : ArmorLevel.values()){
        		List<String> armlist = conf.getStringList("Armor." + e.name() + "." + al.name());
        		List<ItemStack> itls = new ArrayList<ItemStack>();
        		if(!conf.contains("Armor." + e.name() + "." + al.name())) continue;
        		for(String s: armlist){
        			int id = conf.getInt("Armor." + e.name() + "." + al.name() + "." + s + ".ID");
        			int dur = conf.getInt("Armor." + e.name() + "." + al.name() + "." + s + ".Durability");
        			String name = conf.getString("Armor." + e.name() + "." + al.name() + "." + s + ".Name");
        			String lore = conf.getString("Armor." + e.name() + "." + al.name() + "." + s + ".Lore");
        			ItemStack i = new ItemStack(Material.getMaterial(id));
        			i.getItemMeta().setDisplayName(name);
        			List<String> lorel = new ArrayList<String>();
        			lorel.add(lore);
        			i.getItemMeta().setLore(lorel);
        			itls.add(i);
        		}
        		int amt = conf.getInt("Armor." + e.name() + "." + al.name() + ".Amount");
        		amap.put(al, amt);
        		damp.put(al, itls);
        	}
        	armor.put(e, damp);
        }
        
    }
}
