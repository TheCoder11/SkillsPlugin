package com.somemone.skills.listener;

import com.somemone.skills.Skills;
import com.somemone.skills.account.PlayerAccount;
import com.somemone.skills.registry.SkillRegistry;
import com.somemone.skills.skill.MainSkillExecutor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;

public class HitListener implements Listener {

    @EventHandler
    public void onPlayerHit (EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        // Both entities are players

        Player player = (Player) event.getDamager();

        // Player has a selected skill

        PlayerAccount playerAccount = new PlayerAccount(player);

        for (MainSkillExecutor skill : playerAccount.getMainSkills()) {
            skill.run(event);
        }

    }
}
