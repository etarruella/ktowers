package com.etarruella.utils;

import com.etarruella.KTowers;
import com.etarruella.core.entities.KMap;
import com.etarruella.exceptions.KMapException;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
        plugin.saveResource(MAPS_CFG_DIR + "/" + DEFAULT_MAP_NAME + ".yml", false);
        plugin.saveResource(MAPS_DIR + "/" + DEFAULT_MAP_NAME + ".zip", false);
    }

    private static void deleteMapFolder(String mapName) throws KMapException {
        File world = new File(Bukkit.getWorldContainer(), mapName);

        try {
            FileUtils.deleteDirectory(world);
        } catch (IOException e) {
            throw new KMapException("Cannot delete previous " + mapName + " map!");
        }
    }

    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    private static void unzipFile(File zipFile, File destFile) throws IOException {
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            File newFile = newFile(destFile, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
    }

    private static void createWorld(String mapName) {
        WorldCreator worldCreator = new WorldCreator(mapName);
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.type(WorldType.NORMAL);

        worldCreator.createWorld();
    }

    public static KMap loadMap(String mapName) throws KMapException {
        File compressedMap = new File(KTowers.getPlugin().getDataFolder(), MAPS_DIR + "/" + mapName + ".zip");
        File worldFolder = new File(Bukkit.getWorldContainer(), mapName);
        File mapConfig = new File(KTowers.getPlugin().getDataFolder(), MAPS_CFG_DIR + "/" + mapName + ".yml");

        if (!compressedMap.exists()) {
            throw new KMapException("Map " + mapName + " not found!");
        }

        // TODO: Generate new map config if not found

        if (worldFolder.exists()) {
            deleteMapFolder(mapName);
        }

        try {
            unzipFile(compressedMap, worldFolder);
        } catch (IOException e) {
            throw new KMapException("Something went wrong while unzipping map " + mapName + "!");
        }

        createWorld(mapName);

        KTowers.getPlugin().getLogger().info("Map " + mapName + " successfully loaded!");

        return new KMap(mapName, YamlConfiguration.loadConfiguration(mapConfig));
    }

}
