// Credit goes to Camzie99 for implementing our configuration
// This file has no known restrictions

package com.tylerhyperHD.GFixer;


public class GFixerConfigs
{

    private final GFixerConfig playerfetch;
    private final GFixerConfig mainconfig;

    public GFixerConfigs() {
        playerfetch = new GFixerConfig(GFixer.plugin, "players.yml");
        playerfetch.saveDefaultConfig();
        mainconfig = new GFixerConfig(GFixer.plugin, "config.yml");
        mainconfig.saveDefaultConfig();
    }
    
    public void reloadConfigs() {
        playerfetch.reloadConfig();
        mainconfig.reloadConfig();
    }

    public GFixerConfig getPlayers() {
        return playerfetch;
    }
    
    public GFixerConfig getMainConfig()
    {
        return mainconfig;
    }
}