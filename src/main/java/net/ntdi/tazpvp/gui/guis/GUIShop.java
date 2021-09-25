package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
        addShopItem(10, new ItemStack(Material.GOLD_INGOT, 1), 45, ChatColor.BLUE + "Butter", ChatColor.GRAY + "Health Boost\n" + ChatColor.GOLD + "Cost: $45");
        addShopItem(11, new ItemStack(Material.EYE_OF_ENDER, 1), 45, ChatColor.BLUE + "Agility", ChatColor.GRAY + "Speed Boost\n" + ChatColor.GOLD + "Cost: $45");
        addShopItem(12, new ItemStack(Material.RED_ROSE, 1), 45, ChatColor.BLUE + "Extinguisher", ChatColor.GRAY + "Feel the mist\n" + ChatColor.GOLD + "Cost: $45");
        addShopItem(13, new ItemStack(Material.WOOD, 64), 50, ChatColor.BLUE + "Planks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.GOLD + "Cost: $50");
        addShopItem(14, new ItemStack(Material.GOLD_HOE, 1), 500, ChatColor.BLUE + "Tactical Launcher", ChatColor.GRAY + "Explosive Squids\n" + ChatColor.GOLD + "Cost: $500");
        addShopItem(15, new ItemStack(Material.CARROT_STICK, 1), 410, ChatColor.BLUE + "Grappling Hook", ChatColor.GRAY + "Swing away\n" + ChatColor.GOLD + "Cost: $410");
        addShopItem(16, new ItemStack(Material.ARROW, 5), 25, ChatColor.BLUE + "Arrows", ChatColor.GRAY + "Bow Projectiles\n" + ChatColor.GOLD + "Cost: $25");
        addShopItem(19, new ItemStack(Material.MILK_BUCKET, 1), 30, ChatColor.BLUE + "Milk", ChatColor.GRAY + "Negate Poison\n" + ChatColor.GOLD + "Cost: $30");
        addShopItem(20, new ItemStack(Material.COOKIE, 5), 10, ChatColor.BLUE + "Cookies", ChatColor.GRAY + "Yummy\n" + ChatColor.GOLD + "Cost: $10");
        addShopItem(21, new ItemStack(Material.BREAD, 5), 10, ChatColor.BLUE + "Bread", ChatColor.GRAY + "Hot n' Fresh\n" + ChatColor.GOLD + "Cost: $10");
        addShopItem(22, new ItemStack(Material.BAKED_POTATO, 5), 10, ChatColor.BLUE + "Baked Potatoes", ChatColor.GRAY + "What's that smell?\n" + ChatColor.GOLD + "Cost: $10");
        addShopItem(23, new ItemStack(Material.COOKED_BEEF, 5), 15, ChatColor.BLUE + "Steak", ChatColor.GRAY + "Arbies\n" + ChatColor.GOLD + "Cost: $15");
        addShopItem(24, new ItemStack(Material.GOLDEN_CARROT, 1), 15, ChatColor.BLUE + "Gold Carrot", ChatColor.GRAY + "Healthy Choice\n" + ChatColor.GOLD + "Cost: $15");
        addShopItem(25, new ItemStack(Material.GOLDEN_APPLE, 1), 225, ChatColor.BLUE + "Gold Apple", ChatColor.GRAY + "Not Steroids\n" + ChatColor.GOLD + "Cost: $225");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
