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
        super(player, 54, "SHOP");
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
        addShopItem(12, new ItemStack(Material.PRISMARINE_SHARD, 1), 45, ChatColor.DARK_AQUA + "Extinguisher", ChatColor.GRAY + "Feel the mist\n" + ChatColor.AQUA + "$45");
        addShopItem(13, new ItemStack(Material.WOOD, 64), 40, ChatColor.DARK_AQUA + "Planks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.AQUA + "$40");
        addShopItem(14, new ItemStack(Material.GOLD_HOE, 1), 1500, ChatColor.DARK_AQUA + "Tactical Squid Launcher", ChatColor.GRAY + "Explosive Squids\n" + ChatColor.AQUA + "$1500");
        addShopItem(15, new ItemStack(Material.FISHING_ROD, 1), 2000, ChatColor.DARK_AQUA + "Grappling Hook", ChatColor.GRAY + "Swing away\n" + ChatColor.AQUA + "$2000");
        addShopItem(16, new ItemStack(Material.ARROW, 5), 25, ChatColor.DARK_AQUA + "Arrow", ChatColor.GRAY + "Bow Projectiles\n" + ChatColor.AQUA + "$25");
        addShopItem(19, new ItemStack(Material.FEATHER, 1), 55, ChatColor.DARK_AQUA + "Levitation Feather", ChatColor.GRAY + "Fly up in the air\n" + ChatColor.AQUA + "$55");
        addShopItem(20, new ItemStack(Material.COOKIE, 5), 10, ChatColor.DARK_AQUA + "Cookie", ChatColor.GRAY + "Yummy\n" + ChatColor.AQUA + "$10");
        addShopItem(21, new ItemStack(Material.BREAD, 5), 10, ChatColor.DARK_AQUA + "Bread", ChatColor.GRAY + "Hot n' Fresh\n" + ChatColor.AQUA + "$10");
        addShopItem(22, new ItemStack(Material.BAKED_POTATO, 5), 10, ChatColor.DARK_AQUA + "Baked Potato", ChatColor.GRAY + "What's that smell?\n" + ChatColor.AQUA + "$10");
        addShopItem(23, new ItemStack(Material.COOKED_BEEF, 5), 15, ChatColor.DARK_AQUA + "Steak", ChatColor.GRAY + "Arbies\n" + ChatColor.AQUA + "$15");
        addShopItem(24, new ItemStack(Material.GOLDEN_CARROT, 1), 15, ChatColor.DARK_AQUA + "Gold Carrot", ChatColor.GRAY + "Healthy Choice\n" + ChatColor.AQUA + "$15");
        addShopItem(25, new ItemStack(Material.GOLDEN_APPLE, 1), 250, ChatColor.DARK_AQUA + "Gold Apple", ChatColor.GRAY + "Not Steroids\n" + ChatColor.AQUA + "$225");
        addShopItem(37, new ItemStack(Material.WOOL, 60, DyeColor.BLUE.getData()), 50, ChatColor.DARK_AQUA + "Blue BLocks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.GREEN + "Rank Required" + ChatColor.AQUA + "$60");
        addShopItem(37, new ItemStack(Material.WOOL, 60, DyeColor.LIGHT_BLUE.getData()), 50, ChatColor.DARK_AQUA + "Light Blue BLocks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.GREEN + "Rank Required" + ChatColor.AQUA + "$60");
        addShopItem(37, new ItemStack(Material.WOOL, 60, DyeColor.GREEN.getData()), 50, ChatColor.DARK_AQUA + "Green BLocks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.GREEN + "Rank Required" + ChatColor.AQUA + "$60");
        addShopItem(37, new ItemStack(Material.WOOL, 60, DyeColor.YELLOW.getData()), 50, ChatColor.DARK_AQUA + "Yellow BLocks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.GREEN + "Rank Required" + ChatColor.AQUA + "$60");
        addShopItem(37, new ItemStack(Material.WOOL, 60, DyeColor.ORANGE.getData()), 50, ChatColor.DARK_AQUA + "Orange BLocks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.GREEN + "Rank Required" + ChatColor.AQUA + "$60");
        addShopItem(37, new ItemStack(Material.WOOL, 60, DyeColor.PINK.getData()), 50, ChatColor.DARK_AQUA + "Pink BLocks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.GREEN + "Rank Required" + ChatColor.AQUA + "$60");
        addShopItem(37, new ItemStack(Material.WOOL, 60, DyeColor.PURPLE.getData()), 50, ChatColor.DARK_AQUA + "Purple BLocks", ChatColor.GRAY + "Placeable Blocks\n" + ChatColor.GREEN + "Rank Required" + ChatColor.AQUA + "$60");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
