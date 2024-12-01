package com.etarruella.config;

import com.etarruella.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;
import java.util.Map;

// TODO: Add connection to FileManager (unzip & files management)
public class ConfigManager {

    private static ConfigManager configManager;

    private final Plugin plugin;

    private Map<String, File> maps;
    private Map<String, FileConfiguration> mapsCfg;
    private FileConfiguration currentMapConfig;

    public ConfigManager(Plugin plugin) {
        configManager = this;

        this.plugin = plugin;

        FileManager.saveDefaultResources();

        mapLoader();
    }

    public FileConfiguration getMainConfig() {
        return plugin.getConfig();
    }

    public FileConfiguration getMapConfig(String name) {
        return mapsCfg.get(name);
    }

    public void setCurrentMap(String name) {
        currentMapConfig = mapsCfg.get(name);
    }

    public void loadMap(String name) {
        //FileManager.prepareMap(maps.get(name));
        Bukkit.getWorlds().add(new WorldCreator(name).createWorld());
    }

    /**
     * Gets the config of the current loaded map
     * @return The FileConfiguration of the map
     */
    public FileConfiguration getCurrentMapCfg() {
        return currentMapConfig;
    }

    public File getMap(String name) {
        return maps.get(name);
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * 1st -> Get the (.zip) maps in the MainConfig.ACTIVE_MAPS List that has a (.yml) config
     * 2nd -> Load the first map on the ACTIVE_MAPS List
     * 3rd -> Get the configurations
     */
    private void mapLoader() {
        List<String> activeMaps = MainConfig.ACTIVE_MAPS.getValue();

        for (String mapName : activeMaps) {
            FileManager.loadMap(mapName);
        }
    }

}
