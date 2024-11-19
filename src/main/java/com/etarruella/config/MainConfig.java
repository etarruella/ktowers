package com.etarruella.config;

import com.etarruella.config.options.OptionManager;

import java.util.List;

public enum MainConfig {
    ACTIVE_MAPS("active-maps", List.class),
    MONGODB_ENABLED("mongodb-enabled", Boolean.class),
    MONGODB_URI("mongodb-uri", String.class),
    RESTART_TIMEOUT("restart-timeout", Integer.class);

    private final String key;
    private final Class<?> type;

    MainConfig(String key, Class<?> type) {
        this.key = key;
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public <T> T getValue() {
        return (T) OptionManager.getValue(key, type, ConfigManager.getConfigManager().getMainConfig());
    }

    // TODO: Support value writing on MainConfig

    @Override
    public String toString() {
        return key;
    }
}