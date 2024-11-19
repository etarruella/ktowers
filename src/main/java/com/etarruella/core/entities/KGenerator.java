package com.etarruella.core.entities;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class KGenerator implements ConfigurationSerializable {

    private UUID uuid;
    private ItemStack itemStack;
    private Location location;
    private int rate, activationRange;

    public KGenerator(Location location, ItemStack material, int rate, int activationRange) {
        this.location = location;
        this.itemStack = material;
        this.rate = rate;
        this.activationRange = activationRange;
    }

    public void generate() {
        Objects.requireNonNull(location.getWorld()).dropItem(location, itemStack);
    }

    public int getActivationRange() {
        return activationRange;
    }

    public void setActivationRange(int activationRange) {
        this.activationRange = activationRange;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> genMap = new HashMap<>();

        genMap.put("location", location);
        genMap.put("itemStack", itemStack);
        genMap.put("rate", rate);
        genMap.put("activationRange", activationRange);

        return  genMap;
    }

}
