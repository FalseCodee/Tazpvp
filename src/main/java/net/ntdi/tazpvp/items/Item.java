package net.ntdi.tazpvp.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Item {
    public final String name;
    public final Material item;
    public final Items enumeration;
    public Item(Items item) {
        this.name = item.display;
        this.item = item.item;
        this.enumeration = item;
    }

    public abstract boolean execute(Player p, ItemStack itemStack);
}
