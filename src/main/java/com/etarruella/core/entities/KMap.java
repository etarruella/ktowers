package com.etarruella.core.entities;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nullable;

public class KMap {

    private final String mapName;
    private final FileConfiguration mapConfig;

    public KMap(String mapName, FileConfiguration mapConfig) {
        this.mapName = mapName;
        this.mapConfig = mapConfig;
    }

    public String getName() {
        return mapName;
    }

    public FileConfiguration getConfig() {
        return mapConfig;
    }

    public World getWorld() {
        return Bukkit.getWorld(mapName);
    }

}
