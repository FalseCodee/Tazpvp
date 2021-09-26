package net.ntdi.tazpvp.items;

import net.ntdi.tazpvp.items.items.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemManager {
    public static final ArrayList<Item> items = new ArrayList<>();
    public static void init() {
        items.add(new GrapplingHook());
        items.add(new Agility());
        items.add(new Butter());
        items.add(new Extinguish());
        items.add(new SquidLauncher());
    }

    public static void givePlayerItem(Player player, Items item, int count) {
        player.getInventory().addItem(createItem(item.item, count, item.display));
    }

    public static ItemStack createItem(Material item, int count, String name) {
        ItemStack itemStack = new ItemStack(item, count);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
