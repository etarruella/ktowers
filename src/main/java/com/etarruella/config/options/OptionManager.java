package com.etarruella.config.options;

import com.etarruella.core.entities.KGenerator;
import com.etarruella.core.entities.KTeam;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;

public class OptionManager {

    public static <T> T getValue(String key, Class<T> type, FileConfiguration fileConfiguration) {
        return type.cast(getTypedValue(key, type, fileConfiguration));
    }

    private static Object getTypedValue(String key, Class<?> type, FileConfiguration fileConfiguration) {
        return switch (type.getSimpleName()) {
            // Basic type
            case "String" -> fileConfiguration.getString(key);
            case "Boolean" -> fileConfiguration.getBoolean(key);
            case "Integer" -> fileConfiguration.getInt(key);
            case "List" -> fileConfiguration.getList(key);

            // Bukkit type
            case "Location" -> fileConfiguration.getLocation(key);
            case "Color" -> fileConfiguration.getColor(key);
            case "PotionEffect" -> fileConfiguration.getSerializable(key, PotionEffect.class);

            // KTowers type
            case "KGenerator" -> fileConfiguration.getSerializable(key, KGenerator.class);
            case "KTeam" -> fileConfiguration.getSerializable(key, KTeam.class);

            default -> fileConfiguration.get(key);
        };
    }

    public static void setValue(String key, Class<?> type, FileConfiguration fileConfiguration, Object object) {
        String typeName = type.getSimpleName();

        switch (typeName) {
            // Basic types
            case "String" -> fileConfiguration.getString(key);
            case "Boolean" -> fileConfiguration.getBoolean(key);
            case "Integer" -> fileConfiguration.getInt(key);
            case "List" -> fileConfiguration.getList(key);

            // Bukkit types
            case "Location" -> fileConfiguration.set(key, object);

            // KTowers types

            default -> fileConfiguration.get(key);
        };
    }

}
