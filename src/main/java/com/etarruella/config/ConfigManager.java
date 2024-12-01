package com.etarruella.config;

import com.etarruella.core.entities.KMap;
import com.etarruella.utils.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

// TODO: Add connection to FileManager (unzip & files management)
public class ConfigManager {

    private static ConfigManager configManager;
    private final Plugin plugin;
    private List<KMap> maps;

    public ConfigManager(Plugin plugin) {
        maps = new ArrayList<>();

        configManager = this;
        this.plugin = plugin;

        FileManager.saveDefaultResources();
        mapLoader();
    }

    public FileConfiguration getMainConfig() {
        return plugin.getConfig();
    }

    public KMap getCurrentKMap() {
        return maps.getFirst();
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    private void mapLoader() {
        List<String> activeMaps = MainConfig.ACTIVE_MAPS.getValue();

        for (String mapName : activeMaps) {
            maps.add(FileManager.loadMap(mapName));
        }
    }

}
