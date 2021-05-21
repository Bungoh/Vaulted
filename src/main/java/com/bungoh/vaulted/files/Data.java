package com.bungoh.vaulted.files;

import com.bungoh.vaulted.Vaulted;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Data {

    private static File file;
    private static YamlConfiguration config;

    public static void setup(Vaulted plugin) {
        file = new File(plugin.getDataFolder(), "data.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        save();

        plugin.getLogger().info("Data file initialized successfully.");
    }

    public static Map<Integer, ItemStack> getItems(UUID uuid) {
        if (!hasVault(uuid)) {
            return Collections.emptyMap();
        }

        Map<Integer, ItemStack> items = new HashMap<>();
        Set<String> keys = config.getConfigurationSection(uuid.toString()).getKeys(false);

        for (String k : keys) {
            items.put(Integer.parseInt(k), config.getItemStack(uuid + "." + k));
        }

        return items;
    }

    public static void updateItems(UUID uuid, Map<Integer, ItemStack> items) {
        config.set(uuid.toString(), null);
        for (Map.Entry<Integer, ItemStack> item : items.entrySet()) {
            config.set(uuid + "." + item.getKey(), item.getValue());
        }

        save();
    }

    public static boolean hasVault(UUID uuid) {
        return config.contains(uuid.toString());
    }

    private static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
