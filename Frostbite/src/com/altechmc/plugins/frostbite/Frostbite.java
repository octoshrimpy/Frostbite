package com.altechmc.plugins.frostbite;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Frostbite extends JavaPlugin {
    
    ConfigHandler config;
    static Frostbite instance;
    
    @SuppressWarnings("deprecation")
    public void onEnable(){
        this.instance = this;
        config = new ConfigHandler();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new HeatUpdate(), 40, 40);
    }
    
    public void onDisable(){
        
    }
    
    public ConfigHandler getConfigHandler(){
        return config;
    }
    
    public static Frostbite getInstance(){
        return instance;
    }
}
