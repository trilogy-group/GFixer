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
package com.tylerhyperHD.GFixer.Overrides;

import com.tylerhyperHD.GFixer.GFixer;
import java.io.InputStream;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;

@SuppressWarnings("deprecation")
public class Aggreggator {
    
    private static GFixer plugin;
    
    public static boolean isInitialized() {
        return plugin.isInitialized();
    }
    
    public static YamlConfiguration loadConfiguration(InputStream stream) {
        return YamlConfiguration.loadConfiguration(stream);
    }
    
    public static OfflinePlayer getOfflinePlayer(Server server, String name) {
        return server.getOfflinePlayer(name);
    }
}
