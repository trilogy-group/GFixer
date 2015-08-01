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

import com.tylerhyperHD.GFixer.Commands.GFixerCommand;
import com.tylerhyperHD.GFixer.GFixer;
import com.tylerhyperHD.GFixer.GFixerConfig;
import java.util.Collection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
    public void onPotionSplash(PotionSplashEvent event) {        
        // Remove the effects of death pots
        Collection<PotionEffect> effects = event.getPotion().getEffects();
        
        for(PotionEffect effect : effects) {
            if(effect.getType() == PotionEffectType.HEALTH_BOOST) {
                if(effect.getAmplifier() < 0) {
                    effects.remove(effect);
                    event.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Continue removing death pots
        Player p = (Player) event.getPlayer();
        if(p.hasPotionEffect(PotionEffectType.HEALTH_BOOST)) {
            Collection<PotionEffect> pe = p.getActivePotionEffects();
            for(PotionEffect effect : pe) {
                if(effect.getType().equals(PotionEffectType.HEALTH_BOOST)) {
                    if(effect.getAmplifier() < 0) {
                        p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        if(event.getReason().equals("You logged in from another location") && player.isOp()) {
            event.setCancelled(true);
        }
    }

    
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        
        if(command.contains("gfixer")) {
            String message = event.getMessage().toLowerCase();
            GFixerCommand.process(event.getPlayer(), message.split(" "));
            event.setCancelled(true);
            return;
        }
        else if(command.replaceAll("/", "").split(" ")[0].equalsIgnoreCase("regen")) {
            if (GFixer.configs.getMainConfig().getConfig().getBoolean("regen-disabled")) {
                String message = event.getMessage().toLowerCase();
                GFixerCommand.regenCommand(event.getPlayer(), message.split(" "));
                event.setCancelled(true);
                return;
            }
        }
    }
}
