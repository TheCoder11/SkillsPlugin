package com.somemone.skills.listener;

import com.somemone.skills.inventory.SkillsInventory;
import com.somemone.skills.skill.MainSkillExecutor;
import com.somemone.skills.skill.SpecialSkillExecutor;
import com.somemone.skills.skill.VoucherManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick (InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Your Skills")) return;

        ItemStack carriedItem = event.getCursor();
        Player player = (Player) event.getWhoClicked();

        if (!carriedItem.getType().equals(Material.WRITTEN_BOOK)) {
            event.setCancelled(true);
            return;
        }

        if (event.getCurrentItem().equals(SkillsInventory.greenGlass)) {

            VoucherManager voucher = new VoucherManager();
            MainSkillExecutor mainSkill = voucher.fromMainVoucher(carriedItem);
            SpecialSkillExecutor specialSkill = voucher.fromSpecialVoucher(carriedItem);

            if (!(mainSkill == null)) {

                List<Integer> mainSkills = new ArrayList<>();
                for (int value : player.getPersistentDataContainer().get(MainSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY)) {
                    mainSkills.add(value);
                }
                mainSkills.add(mainSkill.getID());
                int[] skillsArray = new int[mainSkills.size()];
                for (int i = 0; i < mainSkills.size(); i++) {
                    skillsArray[i] = mainSkills.get(i);
                }

                player.getPersistentDataContainer().set(MainSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY, skillsArray );

            }
            else if (!(specialSkill == null)) {

                List<Integer> specialSkills = new ArrayList<>();
                for (int value : player.getPersistentDataContainer().get(MainSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY)) {
                    specialSkills.add(value);
                }
                specialSkills.add(specialSkill.getID());
                int[] skillsArray = new int[specialSkills.size()];
                for (int i = 0; i < specialSkills.size(); i++) {
                    skillsArray[i] = specialSkills.get(i);
                }

                player.getPersistentDataContainer().set(MainSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY, skillsArray );

            }

            player.closeInventory();
            SkillsInventory inventory = new SkillsInventory(player);
            inventory.grabValues();
            player.openInventory(inventory.drawScreen());
        }
        else if (event.getCurrentItem().getType().equals(Material.WRITTEN_BOOK)) {
            ItemStack voucherBook = event.getCurrentItem();
            VoucherManager manager = new VoucherManager();

            if (event.getSlot() < 9) {

                MainSkillExecutor mainSkill = manager.fromMainVoucher(voucherBook);
                if (mainSkill == null) {
                    event.setCancelled(true);
                    return;
                }

                int[] playerMainSkills = player.getPersistentDataContainer().get(MainSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY);
                int itemSkill = mainSkill.getID();
                ArrayList<Integer> playerMainSkillsList = new ArrayList<>(); // i hate java i hate java

                for (int playerSkill : playerMainSkills) {
                    if (playerSkill != itemSkill) {
                        playerMainSkillsList.add(playerSkill);
                    }
                }

                int[] finalSkills = new int[playerMainSkillsList.size()];
                for (int i = 0; i < playerMainSkillsList.size(); i++) {
                    finalSkills[i] = playerMainSkillsList.get(i);
                }

                player.getPersistentDataContainer().set(MainSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY, finalSkills);

            }
            else if (event.getSlot() > 18 && event.getSlot() < 26) {
                SpecialSkillExecutor specialSkill = manager.fromSpecialVoucher(voucherBook);
                if (specialSkill == null) {
                    event.setCancelled(true);
                    return;
                }

                int[] playerMainSkills = player.getPersistentDataContainer().get(SpecialSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY);
                int itemSkill = specialSkill.getID();
                ArrayList<Integer> playerSpecialSkillsList = new ArrayList<>(); // i hate java i hate java

                for (int playerSkill : playerMainSkills) {
                    if (playerSkill != itemSkill) {
                        playerSpecialSkillsList.add(playerSkill);
                    }
                }

                int[] finalSkills = new int[playerSpecialSkillsList.size()];
                for (int i = 0; i < playerSpecialSkillsList.size(); i++) {
                    finalSkills[i] = playerSpecialSkillsList.get(i);
                }

                player.getPersistentDataContainer().set(SpecialSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY, finalSkills);

                SkillsInventory inventory = new SkillsInventory(player);
                inventory.grabValues();

                player.closeInventory();
                player.openInventory(inventory.drawScreen());
            }
        }
        else {
            event.setCancelled(true);
        }

    }
}
