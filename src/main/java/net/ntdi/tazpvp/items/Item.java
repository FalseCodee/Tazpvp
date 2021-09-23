package net.ntdi.tazpvp.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class Item {
    public final String name;
    public final String[] description;
    public final Material item;

    public Item(Items item) {
        this.name = item.display;
        this.description = item.description;
        this.item = item.item;
    }
}
