package com.etarruella.config;

import org.bukkit.Location;

import java.util.List;

public enum MapMetadata {
    LOBBY_GENERAL("map-metadata.lobby.general", Location.class),
    LOBBY_TEAM_A("map-metadata.lobby.team-a", Location.class),
    LOBBY_TEAM_B("map-metadata.lobby.team-a", Location.class),
    SPAWN_TEAM_A("map-metadata.lobby.team-a", Location.class),
    SPAWN_EFFECTS("map-metadata.spawn-effects", List.class), // TODO: Create PotionEffectList type
    ARENA_SPAWN_TEAM_A("map-metadata.arena-spawn.team-a", Location.class),
    ARENA_SPAWN_TEAM_B("map-metadata.arena-spawn.team-b", Location.class),
    GENERATORS("map-metadata.generators", List.class), // TODO: Create KGenerators type
    MAP_CENTER("map-metadata.map-center", Location.class),
    TEAM_A("map-metadata.team-a", null), // TODO: Create KTeam type
    TEAM_B("map-metadata.team-b", null);

    private String key;
    private Class<?> type;

    MapMetadata(String key, Class<?> type) {
        this.key = key;
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String toString() {
        return key;
    }
}
