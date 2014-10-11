package com.altechmc.plugins.frostbite;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class ConfigHandler {
    int maxRange;
    public HashMap<Material, Integer[]> heatblocks = new HashMap<Material, Integer[]>();
    public HashMap<Material, Integer[]> coolblocks = new HashMap<Material, Integer[]>();
    
    public ConfigHandler(){
        //TODO: Get data from config file
    }
}
