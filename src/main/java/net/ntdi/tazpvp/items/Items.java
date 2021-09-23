package net.ntdi.tazpvp.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Items {
    GRAPPLING_HOOK(ChatColor.RED + "Grappling Hook", new String[]{"Travel in style."}, Material.FISHING_ROD);
    public String display;
    public String[] description;
    public Material item;
    Items(String name, String[] description, Material item) {
        this.display = name;
        this.description = description;
        this.item = item;
    }
}
