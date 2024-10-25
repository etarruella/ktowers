package com.etarruella.core.entities;

import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;

public class KTeam implements ConfigurationSerializable {

    private final UUID uuid;

    private String name, tag;
    private ChatColor color;
    private List<KPlayer> members;
    private List<KPoint> points;

    public KTeam(UUID uuid, String name, String tag, ChatColor color) {
        this.uuid = uuid;
        this.name = name;
        this.tag = tag;
        this.color = color;

        this.members = new ArrayList<>();
    }

    public void join(KPlayer kPlayer) {
        members.add(kPlayer);
    }

    public void leave(KPlayer kPlayer) {
        members.remove(kPlayer);
    }

    public KPlayer getLeader() {
        return members.getFirst();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public List<KPoint> getPoints() {
        return points;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", name);
        map.put("tag", tag);
        map.put("color", color);

        return map;
    }

}
