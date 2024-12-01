package com.etarruella.core;

import com.etarruella.config.MapMetadata;
import com.etarruella.core.entities.KPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private static final Map<UUID, KPlayer> players = new HashMap<>();

    public static KPlayer getKPlayer(Player player) {
        UUID playerId = player.getUniqueId();
        return players.computeIfAbsent(playerId, id -> new KPlayer(player));
    }

    public static boolean isPlayerLogged(Player player) {
        return players.containsKey(player.getUniqueId());
    }

    public static void deletePlayer(Player player) {
        players.remove(player.getUniqueId());
    }

    private static void addKPlayer(Player player) {
        KPlayer kPlayer = new KPlayer(player);
        players.put(kPlayer.getUuid(), kPlayer);
    }

    public static void preparePlayer(KPlayer kPlayer) {
        Location lobbyLocation = MapMetadata.LOBBY_GENERAL.getValue();
        kPlayer.getPlayer().teleport(lobbyLocation);
    }

}
