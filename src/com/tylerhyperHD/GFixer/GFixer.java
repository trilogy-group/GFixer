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
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GFixer extends JavaPlugin {
    //
    public static GFixer plugin;
    public static Server server;
    public static Loggers logger;
    public static GFixerConfigs configs;
    //
    private File configFile;
    private FileConfiguration fileConfiguration;
    private static Plugin instance;
    
    @Override
    public void onLoad() {
        plugin = this;
        GFixer.server = plugin.getServer();
    }
    
    @Override
    public void onEnable() {
        Loggers.info("Loading GFixer by tylerhyperHD");
        configs = new GFixerConfigs();
        final PluginManager pm = server.getPluginManager();
        pm.registerEvents(new GListen(), plugin);
        configs = GFixer.configs;
        Loggers.info("GFixer by tylerhyperHD has been enabled!");
    }
    
    @Override
    public void onDisable() {
        server.getScheduler().cancelTasks(plugin);
        Loggers.info("GFixer by tylerhyperHD has been disabled!");
    }
    
    
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("gfixer")) {
            GFixerCommand.process(commandSender, args);
        }
        return false;
    }
    
    public static Plugin getInstance() {
        return instance;
    }
    
    public FileConfiguration getConfig() {
        if(fileConfiguration == null) {
            this.reloadConfig();
        }
        return fileConfiguration;
    }
}
