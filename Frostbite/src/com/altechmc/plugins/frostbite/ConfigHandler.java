package com.altechmc.plugins.frostbite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConfigHandler {
    int maxRange;
    int verboseRate;
    public HashMap<Material, Integer[]> heatblocks = new HashMap<Material, Integer[]>();
    public HashMap<Material, Integer[]> coolblocks = new HashMap<Material, Integer[]>();
    public HashMap<EnvTypes, List<PotionEffect>> effects = new HashMap<EnvTypes, List<PotionEffect>>();
    
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
        List<String> efflist = conf.getStringList("FrostEffects");
        List<PotionEffect> pef = new ArrayList<PotionEffect>();
        for(String s: efflist){
        	String type = conf.getString("FrostEffects." + s + ".Type");
        	int dur = conf.getInt("FrostEffects." + s + ".Duration");
        	int pow = conf.getInt("FrostEffects." + s + ".Power");
        	PotionEffect effect = new PotionEffect(PotionEffectType.getByName(type),dur,pow);
        	pef.add(effect);
        }
        effects.put(EnvTypes.FROST, pef);
        
        
    }
}
