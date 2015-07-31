package com.tylerhyperHD.GFixer;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class GFixer extends JavaPlugin {
    //
    public static GFixer plugin;
    public static Server server;
    public static Loggers logger;
    //
    
    
    @Override
    public void onLoad() {
        GFixer.plugin = this;
    }
    
    @Override
    public void onEnable() {
        Loggers.info("GFixer by tylerhyperHD has been enabled!");
    }
    
    @Override
    public void onDisable() {
        Loggers.info("GFixer by tylerhyperHD has been disabled!");
    }
}
