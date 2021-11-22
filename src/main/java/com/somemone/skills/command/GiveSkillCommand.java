package com.somemone.skills.command;

import com.somemone.skills.Skills;
import com.somemone.skills.skill.MainSkillExecutor;
import com.somemone.skills.skill.SpecialSkillExecutor;
import com.somemone.skills.skill.VoucherManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveSkillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            if (!((Player) sender).isOp()) {
                sender.sendMessage(ChatColor.RED + "You are not an operator!");
                return true;
            }
        }

        if (args.length != 3) return false;

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player does not exist!");
            return true;
        }

        VoucherManager manager = new VoucherManager();

        switch (args[1]) {
            case "main":
                try {
                    int skillID = Integer.parseInt(args[2]);
                    MainSkillExecutor mainSkill = Skills.getSkillRegistry().getMainSkillFromID(skillID);

                    player.getInventory().addItem(manager.toItemVoucher(mainSkill));
                    player.sendMessage("[Skills] " + ChatColor.GREEN + "You have been given skill " + mainSkill.getName() + "!");
                } catch (NumberFormatException e) {
                    return false;
                }
                break;
            case "special":
                try {
                    int skillID = Integer.parseInt(args[2]);
                    SpecialSkillExecutor specialSkill = Skills.getSkillRegistry().getSpecialSkillFromID(skillID);

                    player.getInventory().addItem(manager.toItemVoucher(specialSkill));
                    player.sendMessage("[Skills] " + ChatColor.GREEN + "You have been given skill " + specialSkill.getName() + "!");
                } catch (NumberFormatException e) {
                    return false;
                }
                break;
            default:
                return false;
        }

        return true;


    }
}
