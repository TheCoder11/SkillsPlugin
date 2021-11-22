package com.somemone.skills;

import com.somemone.skills.command.GiveSkillCommand;
import com.somemone.skills.command.MySkillsCommand;
import com.somemone.skills.listener.*;
import com.somemone.skills.registry.SkillRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skills extends JavaPlugin {

    private static SkillRegistry skillRegistry;

    @Override
    public void onEnable() {
        skillRegistry = new SkillRegistry();

        this.getCommand("myskills").setExecutor(new MySkillsCommand());
        this.getCommand("giveskill").setExecutor(new GiveSkillCommand());

        this.getServer().getPluginManager().registerEvents(new HitListener(), this);
        this.getServer().getPluginManager().registerEvents(new InteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new SwitchListener(), this);
        this.getServer().getPluginManager().registerEvents(new DeathListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SkillRegistry getSkillRegistry() {
        return skillRegistry;
    }
}
