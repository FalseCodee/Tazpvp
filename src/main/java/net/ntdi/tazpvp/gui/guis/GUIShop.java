package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIShop extends GUI {

    public GUIShop(Player player) {
        super(player, 36, "SHOP");
        setItems();
        player.openInventory(inventory);

    }

    public void addShopItem(int slot, ItemStack item, int cost, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);
                if(TazPvP.statsManager.getMoney(player) >= cost) {
                    TazPvP.statsManager.addMoney(player, -cost);

                    ItemMeta namin = item.getItemMeta();
                    namin.setDisplayName(name);
                    item.setItemMeta(namin);

                    player.getInventory().addItem(item);
                } else {
                    player.sendMessage(ChatColor.RED + "Insufficient funds");
                }
        });
    }

    ItemStack flower = new ItemStack(Material.RED_ROSE, 1, (short) 1);

    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }
        addShopItem(10, new ItemStack(Material.GOLD_INGOT, 1), 45, ChatColor.DARK_AQUA + "Butter", ChatColor.GRAY + "Health Boost\n" + ChatColor.AQUA + "$45");
        addShopItem(11, new ItemStack(Material.EYE_OF_ENDER, 1), 45, ChatColor.DARK_AQUA + "Agility", ChatColor.GRAY + "Speed Boost\n" + ChatColor.AQUA + "$45");
        addShopItem(12, new ItemStack(Material.RED_ROSE, 1), 45, ChatColor.DARK_AQUA + "Extinguisher", ChatColor.GRAY + "Feel the mist\n" + ChatColor.AQUA + "$45");
        addShopItem(13, new ItemStack(Material.WOOD, 64), 50, ChatColor.DARK_AQUA + "Planks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.AQUA + "$50");
        addShopItem(14, new ItemStack(Material.GOLD_HOE, 1), 500, ChatColor.DARK_AQUA + "Tactical Squid Launcher", ChatColor.GRAY + "Explosive Squids\n" + ChatColor.AQUA + "$500");
        addShopItem(15, new ItemStack(Material.CARROT_STICK, 1), 410, ChatColor.DARK_AQUA + "Grappling Hook", ChatColor.GRAY + "Swing away\n" + ChatColor.AQUA + "$410");
        addShopItem(16, new ItemStack(Material.ARROW, 5), 25, ChatColor.DARK_AQUA + "Arrow", ChatColor.GRAY + "Bow Projectiles\n" + ChatColor.AQUA + "$25");
        addShopItem(19, new ItemStack(Material.MILK_BUCKET, 1), 30, ChatColor.DARK_AQUA + "Milk", ChatColor.GRAY + "Negate Poison\n" + ChatColor.AQUA + "$30");
        addShopItem(20, new ItemStack(Material.COOKIE, 5), 10, ChatColor.DARK_AQUA + "Cookie", ChatColor.GRAY + "Yummy\n" + ChatColor.AQUA + "$10");
        addShopItem(21, new ItemStack(Material.BREAD, 5), 10, ChatColor.DARK_AQUA + "Bread", ChatColor.GRAY + "Hot n' Fresh\n" + ChatColor.AQUA + "$10");
        addShopItem(22, new ItemStack(Material.BAKED_POTATO, 5), 10, ChatColor.DARK_AQUA + "Baked Potato", ChatColor.GRAY + "What's that smell?\n" + ChatColor.AQUA + "$10");
        addShopItem(23, new ItemStack(Material.COOKED_BEEF, 5), 15, ChatColor.DARK_AQUA + "Steak", ChatColor.GRAY + "Arbies\n" + ChatColor.AQUA + "$15");
        addShopItem(24, new ItemStack(Material.GOLDEN_CARROT, 1), 15, ChatColor.DARK_AQUA + "Gold Carrot", ChatColor.GRAY + "Healthy Choice\n" + ChatColor.AQUA + "$15");
        addShopItem(25, new ItemStack(Material.GOLDEN_APPLE, 1), 225, ChatColor.DARK_AQUA + "Gold Apple", ChatColor.GRAY + "Not Steroids\n" + ChatColor.AQUA + "$225");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}