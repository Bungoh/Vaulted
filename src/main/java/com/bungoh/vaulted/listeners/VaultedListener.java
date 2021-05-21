package com.bungoh.vaulted.listeners;

import com.bungoh.vaulted.files.Data;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class VaultedListener implements Listener {

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if (!e.getView().getTitle().equals(ChatColor.GREEN + e.getPlayer().getName() + "'s Vault")) {
            return;
        }

        ItemStack[] contents = e.getInventory().getContents();
        Map<Integer, ItemStack> items = new HashMap<>();
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null) {
                items.put(i, contents[i]);
            }
        }

        Data.updateItems(e.getPlayer().getUniqueId(), items);
    }

}
