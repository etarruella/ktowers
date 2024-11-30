package com.etarruella.utils;

import com.etarruella.KTowers;
import com.etarruella.config.MainConfig;
import com.etarruella.core.entities.KMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.apache.commons.io.FilenameUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileManager {

    private final static String MAPS_DIR = "maps";
    private final static String MAPS_CFG_DIR = "maps/cfg";
    private final static String DEFAULT_MAP_NAME = "ktowers";

    public static void saveDefaultResources() {
        Plugin plugin = KTowers.getPlugin();

        // Saves the main config & the default map and its config
        plugin.saveDefaultConfig();
        plugin.saveResource(MAPS_CFG_DIR + "/ktowers.yml", false);
        plugin.saveResource(MAPS_DIR + "/ktowers.zip", false);
    }

    /**
     * Collects the files (.yml) found in the "maps/cfg" path
     * @return Null if (.yml) files not found
     */
    @Nullable
    public static Map<String, FileConfiguration> getMapConfigs() {
        HashMap<String, FileConfiguration> mapConfigs = new HashMap<>();
        Plugin plugin = KTowers.getPlugin();
        File configDir = new File(plugin.getDataFolder(), MAPS_CFG_DIR);

        if (!configDir.exists() || !configDir.isDirectory()) {
            System.err.println("Configuration directory not found: " + MAPS_CFG_DIR);
            return null;
        }

        File[] configFiles = configDir.listFiles((dir, name) -> name.endsWith(".yml"));

        if (configFiles == null) {
            return null;
        }

        for (File configFile : configFiles) {
            if (isActiveMap(configFile)) {
                continue;
            }
            FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            mapConfigs.put(FilenameUtils.removeExtension(configFile.getName()), config);
        }

        return mapConfigs;
    }

    /**
     * Collects the zip files found in the "maps" path.
     * @return A map with the map names as keys and the corresponding zip files as values.
     */
    public static Map<String, File> getMaps() {
        HashMap<String, File> maps = new HashMap<>();
        Plugin plugin = KTowers.getPlugin();
        File mapsDir = new File(plugin.getDataFolder(), MAPS_DIR);

        if (!mapsDir.exists() || !mapsDir.isDirectory()) {
            System.err.println("Maps directory not found: " + MAPS_DIR);
            return maps; // Return empty map
        }

        File[] mapFiles = mapsDir.listFiles((dir, name) -> name.endsWith(".zip"));
        if (mapFiles == null) {
            return maps; // Return empty map if no files found
        }

        for (File mapFile : mapFiles) {
            if (isActiveMap(mapFile)) {
                continue;
            }
            maps.put(FilenameUtils.removeExtension(mapFile.getName()), mapFile);
        }

        return maps;
    }

    private static boolean isActiveMap(File mapFile) {
        List<String> activeMaps = MainConfig.ACTIVE_MAPS.getValue();

        return !activeMaps.contains(FilenameUtils.removeExtension(mapFile.getName()));
    }

    /**
     * Prepares a map by decompressing the zip file into the server's world folder.
     * @param file The zip file to be decompressed.
     */
    public static KMap loadMap(File file) throws IOException {
        String worldName = FilenameUtils.removeExtension(file.getName());
        File worldFolder = new File(KTowers.getPlugin().getServer().getWorldContainer(), worldName);

        ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null) {
            File newFile = new File(worldFolder, zipEntry.getName());
            if (zipEntry.isDirectory()) {
                newFile.mkdirs(); // Create the directory
            } else {
                new File(newFile.getParent()).mkdirs(); // Create necessary parent directories
                // Copy file contents
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zis.read(buffer)) >= 0) {
                        fos.write(buffer, 0, length);
                    }
                }
            }
            zis.closeEntry();
        }

        return null;
    }

    public static void saveModifiedMapCfg(FileConfiguration fileConfiguration) {
        Plugin plugin = KTowers.getPlugin();

        try {
            fileConfiguration.save(new File(plugin.getDataFolder(), String.format("%s/%s", MAPS_CFG_DIR, "ktowers.yml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
