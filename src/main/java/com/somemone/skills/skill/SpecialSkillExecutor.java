package com.somemone.skills.skill;

import com.somemone.skills.Skills;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface SpecialSkillExecutor {

    public static NamespacedKey getPlayerSkills() {
        return new NamespacedKey(Skills.getPlugin(Skills.class), "player-special-skills");
    }

    public static NamespacedKey getPlayerSelectedSkill() {
        return new NamespacedKey(Skills.getPlugin(Skills.class), "player-selected-special-skill");
    }

    public int getID();

    public SkillTier getTier();

    public String getName();

    public String getDescription();

    /**
     * Checks if special skill should actually be run.
     * Make sure that the player actually has the ID.
     *
     * @param event PlayerInteractEvent
     * @return if skill should be ran
     */
    public boolean validate (PlayerInteractEvent event);

    /**
     * Validate first, but runs the event without validating
     *
     * @param event
     */
    public void run(PlayerInteractEvent event);
}
