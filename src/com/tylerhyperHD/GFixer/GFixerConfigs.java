package com.tylerhyperHD.GFixer;


public class GFixerConfigs
{

    private final GFixerConfig playerfetch;

    public GFixerConfigs() {
        playerfetch = new GFixerConfig(GFixer.plugin, "players.yml");
        playerfetch.saveDefaultConfig();
    }
    
    public void reloadConfigs() {
        playerfetch.reloadConfig();
    }

    public GFixerConfig getPlayers() {
        return playerfetch;
    }
}