package net.ntdi.tazpvp.gui;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public abstract class GUI {

    public Player player;
    public Inventory inventory;
    public ItemStack[] items;
    public Map<Integer, Button> buttons = Maps.newHashMap();
    public GUI(Player player, int size, String title) {
        inventory = Bukkit.createInventory(player, size, title);
        this.player = player;
        items = new ItemStack[size];
        GUIManager.addGUI(this);
    }

    public void setButtons(int slot, Button button) {
        buttons.put(slot, button);
    }

    public void update()
    {
        inventory.setContents(items);
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
    public ItemStack createItem(ItemStack itemStack, String name, String lore) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore.split("\n")));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public ItemStack createItem(ItemStack itemStack, String name) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public void onInventoryClick(InventoryClickEvent e, GUI gui){

    }

    public void onInventoryClose(InventoryCloseEvent e, GUI gui){
        GUIManager.guiHashMap.remove(player.getUniqueId());
    }

    public void onInventoryOpen(InventoryOpenEvent e, GUI gui){

    }
}
