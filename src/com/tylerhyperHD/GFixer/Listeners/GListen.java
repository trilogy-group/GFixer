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
package com.tylerhyperHD.GFixer.Listeners;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.tylerhyperHD.GFixer.Commands.GFixerCommand;
import com.tylerhyperHD.GFixer.GFixer;
import com.tylerhyperHD.GFixer.GFixerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class GListen implements Listener {    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        GFixerConfig playerfetch = GFixer.configs.getPlayers();
        FileConfiguration config = playerfetch.getConfig();
        config.set(player.getUniqueId().toString() + ".lastLoginTime", System.currentTimeMillis());
        config.set(player.getUniqueId().toString() + ".lastUsername", player.getName());
        config.set(player.getUniqueId().toString() + ".lastIpLoggedInWith", player.getAddress().getHostString());
        playerfetch.saveConfig();
    }
    
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if(event.getFrom() == null || event.getTo() == null) {
            return;
        }
        Player player = event.getPlayer();
        if(event.getTo().getBlockX() >= 29999000 || event.getTo().getBlockZ() >= 29999000) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        
        if (command.contains("gfixer")) {
            String message = event.getMessage().toLowerCase();
            GFixerCommand.process(event.getPlayer(), message.split(" "));
            event.setCancelled(true);
            return;
        }
        
        LocalSession session;
        WorldEditPlugin plugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        if (command.replaceAll("/", "").split(" ")[0].equalsIgnoreCase("set")) {
            int selection = plugin.getSelection(player).getWidth();
            int selection2 = plugin.getSelection(player).getHeight();
            if (selection > 300 && selection2 > 300) {
                player.sendMessage(ChatColor.RED + "You may not set a block with more than a 300x300 selection.");
                event.setCancelled(true);
            }
        }
    }
}
