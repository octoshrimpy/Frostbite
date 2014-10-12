package com.altechmc.plugins.frostbite;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
    int maxRange;
    public HashMap<Material, Integer[]> heatblocks = new HashMap<Material, Integer[]>();
    public HashMap<Material, Integer[]> coolblocks = new HashMap<Material, Integer[]>();
    
    public ConfigHandler(){
        FileConfiguration conf = Frostbite.getInstance().getConfig();
        //TODO: Get Configs
    }
}
