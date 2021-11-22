package com.somemone.skills.registry;

import com.somemone.skills.Skills;
import com.somemone.skills.skill.MainSkillExecutor;
import com.somemone.skills.skill.SpecialSkillExecutor;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class SkillRegistry {

    public static NamespacedKey getBookSkillID() {
        return new NamespacedKey(Skills.getPlugin(Skills.class), "book-skill-id");
    }

    private ArrayList<MainSkillExecutor> mainSkills;

    private ArrayList<SpecialSkillExecutor> specialSkills;

    public ArrayList<SpecialSkillExecutor> getValidatedSpecialSkills(PlayerInteractEvent event) {
        ArrayList<SpecialSkillExecutor> results = new ArrayList<>();

        for (SpecialSkillExecutor specialSkill : specialSkills) {
            if (specialSkill.validate(event)) {
                results.add(specialSkill);
            }
        }

        return results;
    }

    public ArrayList<SpecialSkillExecutor> getAllowedSpecialSkills(int[] skills) {
        HashMap<Integer, SpecialSkillExecutor> intMap = new HashMap<>();

        for (SpecialSkillExecutor skill : specialSkills) {
            intMap.put(skill.getID(), skill);
        }

        ArrayList<SpecialSkillExecutor> sSkill = new ArrayList<>();
        for (int skill : skills) {
            if (intMap.containsKey(skill)) sSkill.add(intMap.get(skill));
        }
        return sSkill;
    }

    public SpecialSkillExecutor getSpecialSkillFromID(int id) {
        for (SpecialSkillExecutor skill : specialSkills) {
            if (skill.getID() == id) {
                return skill;
            }
        }
        return null;
    }

    public ArrayList<MainSkillExecutor> getAllowedMainSkills(int[] skills) {
        HashMap<Integer, MainSkillExecutor> intMap = new HashMap<>();

        for (MainSkillExecutor skill : mainSkills) {
            intMap.put(skill.getID(), skill);
        }

        ArrayList<MainSkillExecutor> sSkill = new ArrayList<>();
        for (int skill : skills) {
            if (intMap.containsKey(skill)) sSkill.add(intMap.get(skill));
        }
        return sSkill;
    }

    public MainSkillExecutor getMainSkillFromID(int id) {
        for (MainSkillExecutor skill : mainSkills) {
            if (skill.getID() == id) {
                return skill;
            }
        }
        return null;
    }

    public ArrayList<MainSkillExecutor> getMainSkills() {return mainSkills;}

    public void addMainSkill (MainSkillExecutor mainSkill) {
        mainSkills.add(mainSkill);
    }

    public void addSpecialSkill (SpecialSkillExecutor specialSkill) {
        specialSkills.add(specialSkill);
    }
}
