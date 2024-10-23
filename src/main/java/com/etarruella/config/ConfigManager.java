package com.etarruella.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// TODO: Add connection to FileManager (unzip & files management)
public class ConfigManager {

    private final static String MAPS_DIR = "maps";
    private final static String MAPS_CFG_DIR = "maps/cfg";
    private final static String DEFAULT_MAP_NAME = "ktowers";

    private static ConfigManager configManager;

    private Map<String, File> maps;
    private Map<String, FileConfiguration> mapsCfg;
    private Plugin plugin;

    public ConfigManager(Plugin plugin) {
        configManager = this;

        this.plugin = plugin;

        maps = new HashMap<>();
        mapsCfg = new HashMap<>();
    }

    public FileConfiguration getMainConfig() {
        return plugin.getConfig();
    }

    public FileConfiguration getMapConfig(String name) {
        return mapsCfg.get(name);
    }

    public File getMap(String name) {
        return maps.get(name);
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

}
