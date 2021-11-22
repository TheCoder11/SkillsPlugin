package com.somemone.skills.skill;

import com.somemone.skills.Skills;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public interface MainSkillExecutor {

    public static NamespacedKey getPlayerSkills() {
        return new NamespacedKey(Skills.getPlugin(Skills.class), "player-main-skills");
    }

    public int getID();

    public SkillTier getTier();

    public String getName();

    public String getDescription();

    /*
    NOTE: The run needs to be validated
     */
    public void run(EntityDamageByEntityEvent hitEvent);

}
