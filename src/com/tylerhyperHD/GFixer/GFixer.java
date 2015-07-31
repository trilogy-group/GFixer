/*
 * Copyright (C) 2015 tylerhyperHD
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.tylerhyperHD.GFixer;

import com.tylerhyperHD.GFixer.Commands.GFixerCommand;
import com.tylerhyperHD.GFixer.Listeners.GListen;
import java.io.File;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GFixer extends JavaPlugin {
    //
    public static GFixer plugin;
    public static Server server;
    public static Loggers logger;
    public static GFixerConfigs config;
    //
    private File configFile;
    private FileConfiguration fileConfiguration;
    
    @Override
    public void onLoad() {
        GFixer.plugin = this;
    }
    
    @Override
    public void onEnable() {
        final PluginManager pm = server.getPluginManager();
        pm.registerEvents(new GListen(), plugin);
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
        plugin.getConfig().set("chat-cooldown", true);
        plugin.saveConfig();
        getCommand("gfixer").setExecutor(new GFixerCommand());
        Loggers.info("GFixer by tylerhyperHD has been enabled!");
    }
    
    @Override
    public void onDisable() {
        server.getScheduler().cancelTasks(plugin);
        Loggers.info("GFixer by tylerhyperHD has been disabled!");
    }
    
    public FileConfiguration getConfig() {
        if(fileConfiguration == null) {
            this.reloadConfig();
        }
        return fileConfiguration;
    }
}
