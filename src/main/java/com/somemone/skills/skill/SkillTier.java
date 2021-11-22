package com.somemone.skills.skill;

import org.bukkit.ChatColor;

public enum SkillTier {

    NOVICE(1.2, ChatColor.DARK_GRAY + "Novice", ChatColor.DARK_GRAY + ""),
    APPRENTICE(2.3, ChatColor.RED + "Apprentice", ChatColor.RED + ""),
    EXPERT(4.4, ChatColor.AQUA + "Expert", ChatColor.AQUA + ""),
    MASTER(7.5, ChatColor.BOLD + "" + ChatColor.YELLOW + "Master", ChatColor.BOLD + "" + ChatColor.YELLOW),
    LEGEND(9.3, ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "Legend", ChatColor.BOLD + "" + ChatColor.DARK_PURPLE);

    private final double weight;
    private final String title;
    private final String color;

    private SkillTier(double weight, String title, String color) {
        this.weight = weight;
        this.title = title;
        this.color = color;
    }

    /*
    Returns number between 1 and 10 representing the weight of the tier
     */
    public Double getWeight() {
        return weight;
    }

    public String getTitle() { return title; }

    public String getColor() { return color; }

}
