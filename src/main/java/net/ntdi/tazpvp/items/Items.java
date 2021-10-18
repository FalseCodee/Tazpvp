package net.ntdi.tazpvp.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Items {
    GRAPPLING_HOOK(ChatColor.DARK_AQUA + "Grappling Hook", Material.FISHING_ROD),
    BUTTER(ChatColor.DARK_AQUA + "Butter", Material.GOLD_INGOT),
    AGILITY(ChatColor.DARK_AQUA + "Agility", Material.EYE_OF_ENDER),
    EXTINGUISH(ChatColor.DARK_AQUA + "Extinguish Flower", Material.RED_ROSE),
    SQUID_LAUNCHER(ChatColor.DARK_AQUA + "Tactical Squid Launcher", Material.GOLD_HOE),
    RIDEPEARL(ChatColor.DARK_AQUA + "Ridable Pearl", Material.EYE_OF_ENDER),
    FIREGUN(ChatColor.DARK_AQUA + "Fireball Gun", Material.FIREBALL),
    LEVFEATHER(ChatColor.DARK_AQUA + "Levitation Feather", Material.FEATHER),
    CREDITPAPER(ChatColor.DARK_AQUA + "Paper", Material.PAPER);

    public final String display;
    public final Material item;
    Items(String name, Material item) {
        this.display = name;
        this.item = item;
    }
}