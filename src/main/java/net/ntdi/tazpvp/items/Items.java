package net.ntdi.tazpvp.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Items {
    GRAPPLING_HOOK(ChatColor.RED + "Grappling Hook", new String[]{ChatColor.GRAY + "Right click to fly.", ChatColor.GRAY + "NOTE: Disabled in combat."}, Material.FISHING_ROD),
    BUTTER(ChatColor.YELLOW + "Butter", new String[]{ChatColor.RED + "Instant Health"}, Material.GOLD_INGOT),
    AGILITY(ChatColor.AQUA + "Agility", new String[] {ChatColor.RED + "Instant Speed"}, Material.EYE_OF_ENDER),
    EXTINGUISH(ChatColor.AQUA + "Extinguish Flower", new String[] {ChatColor.DARK_AQUA + "Rightclick to get rid of fire!"}, Material.RED_ROSE),
    SQUID_LAUNCHER(ChatColor.RED + "Tactical Squid Launcher", new String[]{ChatColor.GRAY + "8 Second cooldown"}, Material.GOLD_HOE);
    public String display;
    public String[] description;
    public Material item;
    Items(String name, String[] description, Material item) {
        this.display = name;
        this.description = description;
        this.item = item;
    }
}
