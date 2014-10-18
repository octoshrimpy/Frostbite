package com.altechmc.plugins.frostbite;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Frostbite extends JavaPlugin {
    
    ConfigHandler config;
    static Frostbite instance;
    
    
    public void onEnable(){
        this.instance = this;
        config = new ConfigHandler();
        
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new HeatUpdate(), 40, 40);
    }
    
    public void onDisable(){
        saveData();
    }
    
   
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args){
        return CommandHandler.executeCommand(cmd, args, sender);
    }
    
    public ConfigHandler getConfigHandler(){
        return config;
    }
    
    public static Frostbite getInstance(){
        return instance;
    }
    
    void saveData(){
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            try{
                this.getDatabase().update(HeatHandler.getHandlerByPlayer(p));
            }catch(Exception e){}
        }
    }
    

}
