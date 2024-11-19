package com.etarruella.core;

import com.etarruella.core.entities.KPlayer;
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

    public static void deletePlayer(KPlayer kPlayer) {
        players.remove(kPlayer.getUuid());
    }

    private static void addKPlayer(Player player) {
        KPlayer kPlayer = new KPlayer(player);
        players.put(kPlayer.getUuid(), kPlayer);
    }

}
