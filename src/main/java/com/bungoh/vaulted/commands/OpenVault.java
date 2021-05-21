package com.bungoh.vaulted.commands;

import com.bungoh.vaulted.files.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class OpenVault implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            //Open their vault.
            Inventory vault = Bukkit.createInventory(player, 54, ChatColor.GREEN + player.getName() + "'s Vault");

            //Load the items as they were into the vault.
            Map<Integer, ItemStack> items = Data.getItems(player.getUniqueId());
            for (Map.Entry<Integer, ItemStack> item : items.entrySet()) {
                vault.setItem(item.getKey(), item.getValue());
            }

            //Open the inventory
            player.openInventory(vault);
        } else {
            sender.sendMessage("Only players can use this command.");
        }

        return true;
    }

}
