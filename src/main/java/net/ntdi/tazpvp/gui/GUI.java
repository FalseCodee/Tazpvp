package net.ntdi.tazpvp.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class GUI implements Listener {

    public Player player;
    public Inventory inventory;

    public GUI(Player player, int size, String title) {
        inventory = Bukkit.createInventory(player, size, title);
        this.player = player;
    }

    public ItemStack createItem(Material item, int count, String name, String lore) {
        ItemStack itemStack = new ItemStack(item, count);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore.split("\n")));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack createItem(Material item, int count, String name) {
        ItemStack itemStack = new ItemStack(item, count);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @EventHandler
    public abstract void onInventoryClick(InventoryClickEvent e);

    @EventHandler
    public abstract void onInventoryClose(InventoryCloseEvent e);

    @EventHandler
    public abstract void onInventoryOpen(InventoryOpenEvent e);

    @EventHandler
    public abstract void onInventoryPickupItem(InventoryPickupItemEvent e);

    @EventHandler
    public abstract void onInventoryDrag(InventoryDragEvent e);

}
