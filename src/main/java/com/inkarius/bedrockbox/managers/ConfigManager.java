package com.inkarius.bedrockbox.managers;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
    private final JavaPlugin plugin;
    private final FileConfiguration config;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void setLobby(Location loc) {
        config.set("lobby", loc);
        plugin.saveConfig();
    }

    public void createArena(String arenaName) {
        config.set("arenas." + arenaName + ".setup", true);
        plugin.saveConfig();
    }

    public void setCenterShop(String arenaName, Location loc) {
        config.set("arenas." + arenaName + ".center_shop", loc);
        plugin.saveConfig();
    }

    public void setTeamShop(String arenaName, String team, Location loc) {
        config.set("arenas." + arenaName + ".teams." + team + ".shop", loc);
        plugin.saveConfig();
    }

    public void setTeamBed(String arenaName, String team, Location loc) {
        config.set("arenas." + arenaName + ".teams." + team + ".bed", loc);
        plugin.saveConfig();
    }
}
