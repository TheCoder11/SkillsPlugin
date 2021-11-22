package com.somemone.skills.listener;

import com.somemone.skills.Skills;
import com.somemone.skills.skill.SpecialSkillExecutor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.persistence.PersistentDataType;

public class SwitchListener implements Listener {

    @EventHandler
    public void onItemSwitch (PlayerItemHeldEvent event) {

        if (!event.getPlayer().isSneaking()) return;
        if (!event.getPlayer().getPersistentDataContainer().has(SpecialSkillExecutor.getPlayerSkills(),
                PersistentDataType.INTEGER_ARRAY)) return;

        int newSlot = event.getNewSlot();

        int[] skills = event.getPlayer().getPersistentDataContainer().get(SpecialSkillExecutor.getPlayerSkills(),
                PersistentDataType.INTEGER_ARRAY);

        if (skills == null || skills.length > newSlot) { // IMPORTANT: THIS COULD CHANGE!!!
            event.getPlayer().sendMessage(ChatColor.RED + "There's no skill in this slot!");
            return;
        }

        SpecialSkillExecutor skill = Skills.getSkillRegistry().getSpecialSkillFromID( skills[newSlot] );

        event.getPlayer().getPersistentDataContainer().set(SpecialSkillExecutor.getPlayerSelectedSkill(), PersistentDataType.INTEGER, skill.getID());

        event.getPlayer().sendMessage(ChatColor.GREEN + "Activated Skill " + ChatColor.BLUE + skill.getName() + ChatColor.GREEN + "!");

    }

}
