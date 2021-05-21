package com.bungoh.vaulted;

import com.bungoh.vaulted.commands.OpenVault;
import com.bungoh.vaulted.files.Config;
import com.bungoh.vaulted.files.Data;
import com.bungoh.vaulted.listeners.VaultedListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vaulted extends JavaPlugin {

    @Override
    public void onEnable() {
        //Register Files
        Config.setup(this);
        Data.setup(this);
        //Register Command
        getCommand("openvault").setExecutor(new OpenVault());
        //Register Listener
        Bukkit.getPluginManager().registerEvents(new VaultedListener(), this);
    }
}
