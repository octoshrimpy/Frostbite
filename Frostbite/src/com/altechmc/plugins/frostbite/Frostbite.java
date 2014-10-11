package com.altechmc.plugins.frostbite;

import org.bukkit.plugin.java.JavaPlugin;


public class Frostbite extends JavaPlugin {
    
    ConfigHandler config;
    static Frostbite instance;
    
    public void onEnable(){
        this.instance = this;
        config = new ConfigHandler();
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
