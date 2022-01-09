package net.ntdi.tazpvp.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Items {
    GRAPPLING_HOOK(ChatColor.WHITE + "Grappling Hook", Material.FISHING_ROD),
    BUTTER(ChatColor.WHITE + "Butter", Material.GOLD_INGOT),
    AGILITY(ChatColor.WHITE + "Agility", Material.EYE_OF_ENDER),
    EXTINGUISH(ChatColor.WHITE + "Extinguish Flower", Material.RED_ROSE),
    SQUID_LAUNCHER(ChatColor.WHITE + "Tactical Squid Launcher", Material.GOLD_HOE),
    RIDEPEARL(ChatColor.WHITE + "Ridable Pearl", Material.EYE_OF_ENDER),
    FIREGUN(ChatColor.WHITE + "Fireballz Gun", Material.FIREBALL),
    LEVFEATHER(ChatColor.WHITE + "Lethal Injection", Material.FEATHER),
    FIREBALL(ChatColor.WHITE + "Fireballz", Material.FIREBALL),
    HAMMER(ChatColor.WHITE + "Hammer", Material.GOLD_SPADE),
    CREDITPAPER(ChatColor.WHITE + "Paper", Material.PAPER);

    public final String display;
    public final Material item;
    Items(String name, Material item) {
        this.display = name;
        this.item = item;
    }
}