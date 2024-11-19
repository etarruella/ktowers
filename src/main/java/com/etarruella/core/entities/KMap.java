package com.etarruella.core.entities;

import com.etarruella.core.states.MapState;
import com.etarruella.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

public class KMap {

    private final String mapName;
    private final FileConfiguration mapConfig;
    private final File mapCompressed;

    private MapState mapState;
    private World mapWorld;

    public KMap(String mapName, File mapCompressed, FileConfiguration mapConfig) {
        this.mapName = mapName;
        this.mapCompressed = mapCompressed;
        this.mapConfig = mapConfig;

        mapState = MapState.CACHED;
    }

    public FileConfiguration getConfig() {
        return mapConfig;
    }

    public String getName() {
        return mapName;
    }

    public MapState getState() {
        return mapState;
    }

    @Nullable
    public World getWorld() {
        return mapWorld;
    }

    public void prepareMap() { // Sets map to ready
        if (mapState != MapState.CACHED) {
            return; // TODO: manage exception KMapException
        }

        try {
            FileManager.prepareMap(mapCompressed);
        } catch (IOException e) {
            mapState = MapState.FAILED;
            throw new RuntimeException(e); // TODO: same as top
        }

        mapState = MapState.READY;
    }

    public void loadMap() { // Load the map as dimension
        if (mapState != MapState.READY && mapState != MapState.UNLOADED) {
            return; // TODO: manage exception KMapException
        }

        mapWorld = new WorldCreator(mapName).createWorld();

        Bukkit.getWorlds().add(mapWorld);

        mapState = MapState.LOADED;
    }

    public void unloadMap() { // Unloads the map
        if (mapState != MapState.LOADED) {
            return; // TODO: manage exception KMapException
        }

        Bukkit.getWorlds().remove(mapWorld);

        mapState = MapState.UNLOADED;
    }

    public void clean() { // Deletes the files of the map (returns to MapState.CACHED)
        return; // TODO: delete the map manually for now
    }

}
