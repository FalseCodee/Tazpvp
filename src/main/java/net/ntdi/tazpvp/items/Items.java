package net.ntdi.tazpvp.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Items {
    GRAPPLING_HOOK(ChatColor.DARK_AQUA + "Grappling Hook", Material.CARROT_STICK),
    BUTTER(ChatColor.DARK_AQUA + "Butter", Material.GOLD_INGOT),
    AGILITY(ChatColor.DARK_AQUA + "Agility", Material.EYE_OF_ENDER),
    EXTINGUISH(ChatColor.DARK_AQUA + "Extinguish Flower", Material.RED_ROSE),
    SQUID_LAUNCHER(ChatColor.DARK_AQUA + "Tactical Squid Launcher", Material.GOLD_HOE);
    public String display;
    public Material item;
    Items(String name, Material item) {
        this.display = name;
        this.item = item;
    }
}