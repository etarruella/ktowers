package com.etarruella.core.entities;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KPlayer {

    private final UUID uuid;
    private final String name;

    private KTeam kTeam;

    private int kills, deaths;

    public KPlayer(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }

    // Getters
    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public KTeam getKTeam() {
        return kTeam;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    // Setters
    public void setKTeam(KTeam kTeam) {
        this.kTeam = kTeam;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    // Increment methods
    public void increaseKills() {
        this.kills++;
    }

    public void increaseDeaths() {
        this.deaths++;
    }

    // Get Bukkit player object
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

}