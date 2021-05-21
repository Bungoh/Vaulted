package com.bungoh.vaulted.files;

import com.bungoh.vaulted.Vaulted;

public class Config {

    private static Vaulted plugin;

    public static void setup(Vaulted plugin) {
        Config.plugin = plugin;

        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();

        plugin.getLogger().info("Config initialized successfully.");
    }

    public static String getPrefix() { return plugin.getConfig().getString("plugin-prefix"); }

}
