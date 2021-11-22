package com.somemone.skills.listener;

import com.somemone.skills.Skills;
import com.somemone.skills.skill.SpecialSkillExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class InteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent event) {

        Player player = event.getPlayer();

        ArrayList<SpecialSkillExecutor> validatedSkills = Skills.getSkillRegistry().getValidatedSpecialSkills(event);

        if (!player.getPersistentDataContainer().has(SpecialSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY)) return;
        if (!player.getPersistentDataContainer().has(SpecialSkillExecutor.getPlayerSelectedSkill(), PersistentDataType.INTEGER)) return;

        int selectedSpecialSkill = player.getPersistentDataContainer().get(SpecialSkillExecutor.getPlayerSelectedSkill(), PersistentDataType.INTEGER);
        SpecialSkillExecutor skill = Skills.getSkillRegistry().getSpecialSkillFromID(selectedSpecialSkill);

        if (skill.validate(event)) {
            skill.run(event);
        }

    }
}
