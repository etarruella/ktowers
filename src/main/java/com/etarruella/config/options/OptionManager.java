package com.etarruella.config.options;

import com.etarruella.entities.KGenerator;
import org.bukkit.configuration.file.FileConfiguration;

public class OptionManager {

    public static Object getValue(String key, Class<?> type, FileConfiguration fileConfiguration) {
        String typeName = type.getSimpleName();

        return switch (typeName) {
            // Basic types
            case "String" -> fileConfiguration.getString(key);
            case "Boolean" -> fileConfiguration.getBoolean(key);
            case "Integer" -> fileConfiguration.getInt(key);
            case "List" -> fileConfiguration.getList(key);

            // Bukkit types
            case "Location" -> fileConfiguration.getLocation(key);
            case "Color" -> fileConfiguration.getColor(key); // TODO: Check if ChatColor is serializable
            case "KGenerator" -> fileConfiguration.getSerializable(key, KGenerator.class); // TODO: see MapMetadata

            // KTowers types
            default -> fileConfiguration.get(key);
        };
    }

    public static void setValue(String key, Class<?> type, FileConfiguration fileConfiguration) {
        String typeName = type.getSimpleName();

        switch (typeName) {
            case "String" -> fileConfiguration.getString(key);
            case "Boolean" -> fileConfiguration.getBoolean(key);
            case "Integer" -> fileConfiguration.getInt(key);
            case "List" -> fileConfiguration.getList(key);
            default -> fileConfiguration.get(key);
        };
    }

}
