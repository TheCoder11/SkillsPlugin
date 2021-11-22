package com.somemone.skills.account;

import com.somemone.skills.Skills;
import com.somemone.skills.skill.MainSkillExecutor;
import com.somemone.skills.skill.SpecialSkillExecutor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class PlayerAccount {

    public static NamespacedKey getPlayerMainSlots () {
        return new NamespacedKey(Skills.getPlugin(Skills.class), "player-main-slots");
    }

    public static NamespacedKey getPlayerSpecialSlots() {
        return new NamespacedKey(Skills.getPlugin(Skills.class), "player-special-slots");
    }

    private Player player;
    private ArrayList<MainSkillExecutor> mainSkills;
    private ArrayList<SpecialSkillExecutor> specialSkills;

    public PlayerAccount(Player player) {

        this.player = player;
        mainSkills = new ArrayList<>();
        specialSkills = new ArrayList<>();

        int[] rawMainSkills = player.getPersistentDataContainer().get(MainSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY);
        int[] rawSpecialSkills = player.getPersistentDataContainer().get(SpecialSkillExecutor.getPlayerSkills(), PersistentDataType.INTEGER_ARRAY);

        if (rawMainSkills != null) {
            for (int skill : rawMainSkills) {
                mainSkills.add(Skills.getSkillRegistry().getMainSkillFromID(skill));
            }
        }
        if (rawSpecialSkills != null) {
            for (int skill : rawSpecialSkills) {
                specialSkills.add(Skills.getSkillRegistry().getSpecialSkillFromID(skill));
            }
        }

    }

    public void updateValues() {

    }

    public ArrayList<MainSkillExecutor> getMainSkills() {return mainSkills;}
    public ArrayList<SpecialSkillExecutor> getSpecialSkills() {return specialSkills;}
}
