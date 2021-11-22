package com.somemone.skills.listener;

import com.somemone.skills.Skills;
import com.somemone.skills.skill.MainSkillExecutor;
import com.somemone.skills.skill.SpecialSkillExecutor;
import com.somemone.skills.skill.VoucherManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent event) {

        // Delete player skills
        int[] playerMainSkills = event.getPlayer().getPersistentDataContainer().get(MainSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY);
        int[] playerSpecialSkills = event.getPlayer().getPersistentDataContainer().get(SpecialSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY);

        ItemStack[] items = new ItemStack[ playerSpecialSkills.length + playerMainSkills.length ];
        VoucherManager manager = new VoucherManager();

        event.getPlayer().getPersistentDataContainer().remove(MainSkillExecutor.getPlayerSkills());
        event.getPlayer().getPersistentDataContainer().remove(SpecialSkillExecutor.getPlayerSkills());
        event.getPlayer().getPersistentDataContainer().remove(SpecialSkillExecutor.getPlayerSelectedSkill());

        for (int i = 0; i < playerMainSkills.length; i++) {
            MainSkillExecutor mainSkill = Skills.getSkillRegistry().getMainSkillFromID( playerMainSkills[i] );
            items[i] = manager.toItemVoucher(mainSkill);
        }

        for (int i = 0; i < playerSpecialSkills.length; i++) {
            SpecialSkillExecutor specialSkill = Skills.getSkillRegistry().getSpecialSkillFromID( playerSpecialSkills[i] );
            items[i + playerMainSkills.length] = manager.toItemVoucher(specialSkill);
        }

        for (ItemStack item : items) {

            double xAdj = (Math.random() * 5) - 2.5;
            double zAdj = (Math.random() * 5) - 2.5;

            Location playerLocation = event.getPlayer().getLocation();
            playerLocation.add(xAdj, 0, zAdj);

            event.getPlayer().getWorld().dropItemNaturally( playerLocation, item );

        }

    }
}
