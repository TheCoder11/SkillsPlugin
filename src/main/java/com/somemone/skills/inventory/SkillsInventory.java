package com.somemone.skills.inventory;

import com.somemone.skills.Skills;
import com.somemone.skills.account.PlayerAccount;
import com.somemone.skills.skill.MainSkillExecutor;
import com.somemone.skills.skill.SpecialSkillExecutor;
import com.somemone.skills.skill.VoucherManager;
import com.somemone.skills.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class SkillsInventory {

    public static ItemStack greenGlass = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setName(ChatColor.GREEN + "Drag a skill over me!").toItemStack();
    public static ItemStack redGlass = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName(ChatColor.RED + "Unlock this slot!").toItemStack();

    private Player player;
    private ArrayList<MainSkillExecutor> mainSkills = new ArrayList<>();
    private ArrayList<SpecialSkillExecutor> specialSkills = new ArrayList<>();

    public SkillsInventory (Player player) {
        this.player = player;
        grabValues();
    }

    public void grabValues() {

        int[] mainSkillsInts = player.getPersistentDataContainer().get(SpecialSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY);
        int[] specialSkillsInts = player.getPersistentDataContainer().get(MainSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY);

        if (mainSkillsInts != null) {
            for (int skill : mainSkillsInts) {
                mainSkills.add(Skills.getSkillRegistry().getMainSkillFromID(skill) );
            }
        }
        if (specialSkillsInts != null) {
            for (int skill : specialSkillsInts) {
                specialSkills.add(Skills.getSkillRegistry().getSpecialSkillFromID(skill));
            }
        }

    }

    public Inventory drawScreen() {
        Inventory inventory = Bukkit.createInventory(null, 27, "Your Skills");

        int playerMainSlots = player.getPersistentDataContainer().get(PlayerAccount.getPlayerMainSlots(), PersistentDataType.INTEGER);
        int playerSpecialSlots = player.getPersistentDataContainer().get(PlayerAccount.getPlayerSpecialSlots(), PersistentDataType.INTEGER);

        int count = 1;
        for (int i = 0; i < mainSkills.size(); i++) {
            inventory.setItem(count, new VoucherManager().toItemVoucher(mainSkills.get(i)));
            count++;
        }
        for (int i = 0; i < (playerMainSlots - mainSkills.size()); i++) {
            inventory.setItem(count, greenGlass);
            count++;
        }
        for (int i = 0; i < (7 - playerMainSlots); i++) {
            inventory.setItem(count, redGlass);
            count++;
        }

        count = 19;
        for (int i = 0; i < specialSkills.size(); i++) {
            inventory.setItem(count, new VoucherManager().toItemVoucher(specialSkills.get(i)));
            count++;
        }
        for (int i = 0; i < (playerSpecialSlots - specialSkills.size()); i++) {
            inventory.setItem(count, greenGlass);
            count++;
        }
        for (int i = 0; i < (7 - playerSpecialSlots); i++) {
            inventory.setItem(count, redGlass);
            count++;
        }

        return inventory;
    }
}
