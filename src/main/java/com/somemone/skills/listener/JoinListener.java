package com.somemone.skills.listener;

import com.somemone.skills.account.PlayerAccount;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        if (event.getPlayer().getPersistentDataContainer().has(PlayerAccount.getPlayerMainSlots(), PersistentDataType.INTEGER)) return;

        event.getPlayer().getPersistentDataContainer().set(PlayerAccount.getPlayerMainSlots(), PersistentDataType.INTEGER, 3);
        event.getPlayer().getPersistentDataContainer().set(PlayerAccount.getPlayerSpecialSlots(), PersistentDataType.INTEGER, 3);
    }
}
