package com.somemone.skills.command;

import com.somemone.skills.inventory.SkillsInventory;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MySkillsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            SkillsInventory inventory = new SkillsInventory(player);

            player.openInventory(inventory.drawScreen());
        } else {
            sender.sendMessage(ChatColor.RED + "You are not a player!");
        }
        return true;

    }
}
