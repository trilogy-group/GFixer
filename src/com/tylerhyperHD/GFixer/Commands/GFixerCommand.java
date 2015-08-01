package com.tylerhyperHD.GFixer.Commands;

import com.tylerhyperHD.GFixer.GFixer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

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

public class GFixerCommand {
    
    private GFixerCommand() {}
    
    public static boolean process(CommandSender sender, String[] args) {
            Plugin plugin = GFixer.getInstance();
            sender.sendMessage(ChatColor.RED + "GFixer was made to resolve bugs in minecraft for server owners.");
            sender.sendMessage(ChatColor.RED + "Made by tylerhyperHD");
        return true;
    }
}
