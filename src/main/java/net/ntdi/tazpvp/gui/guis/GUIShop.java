package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.apache.commons.lang.StringUtils;

public class GUIShop extends GUI {

    public GUIShop(Player player) {
        super(player, 54, ChatColor.BLUE + "" + ChatColor.BOLD + "SHOP");
        setItems();
        player.openInventory(inventory);

    }

    public void addShopItem(int slot, ItemStack item, int cost, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);
                if(TazPvP.statsManager.getMoney(player) >= cost) {
                    if (item.getType() == Material.WOOL) {
                        if (player.hasPermission("rank.buy")){
                            TazPvP.statsManager.addMoney(player, -cost);

                            ItemMeta namin = item.getItemMeta();
                            namin.setDisplayName(name);
                            item.setItemMeta(namin);

                            String displName = namin.getDisplayName();

                            player.getInventory().addItem(item);
                            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1 );
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have permission to buy this item.");
                        }
                    } else {
                        TazPvP.statsManager.addMoney(player, -cost);

                        ItemMeta namin = item.getItemMeta();
                        namin.setDisplayName(name);
                        item.setItemMeta(namin);

                        String displName = namin.getDisplayName();

                        player.getInventory().addItem(item);
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1 );
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Insufficient funds");
                }
        });
    }

    ItemStack flower = new ItemStack(Material.RED_ROSE, 1, (short) 1);

    @SuppressWarnings("deprecation")
    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }
        addShopItem(10, new ItemStack(Material.GOLD_INGOT, 1), 45, ChatColor.WHITE + "Butter", ChatColor.BLUE + "Health Boost\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$45");
        addShopItem(11, new ItemStack(Material.EYE_OF_ENDER, 1), 45, ChatColor.WHITE + "Agility", ChatColor.BLUE + "Speed Boost\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$45");
        addShopItem(12, new ItemStack(Material.PRISMARINE_SHARD, 1), 45, ChatColor.WHITE + "Extinguisher", ChatColor.BLUE + "Feel the mist\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$45");
        addShopItem(13, new ItemStack(Material.SNOW_BALL, 16), 30, ChatColor.WHITE + "Balls", ChatColor.BLUE + "Brrrr\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$30");
        addShopItem(14, new ItemStack(Material.WOOD, 64), 40, ChatColor.WHITE + "Planks", ChatColor.BLUE + "Placeable Blocks\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$40");
        addShopItem(15, new ItemStack(Material.GOLD_HOE, 1), 1500, ChatColor.WHITE + "Tactical Squid Launcher", ChatColor.BLUE + "Explosive Squids\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$1500");
        addShopItem(16, new ItemStack(Material.ARROW, 5), 25, ChatColor.WHITE + "Arrow", ChatColor.BLUE + "Bow Projectiles\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$25");
        addShopItem(19, new ItemStack(Material.FEATHER, 1), 150, ChatColor.WHITE + "Lethal Injection", ChatColor.BLUE + "Yikes\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$150");
        addShopItem(20, new ItemStack(Material.COOKIE, 5), 10, ChatColor.WHITE + "Cookie", ChatColor.BLUE + "Yummy\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$10");
        addShopItem(21, new ItemStack(Material.BREAD, 5), 10, ChatColor.WHITE + "Bread", ChatColor.BLUE + "Hot n' Fresh\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$10");
        addShopItem(22, new ItemStack(Material.BAKED_POTATO, 5), 10, ChatColor.WHITE + "Baked Potato", ChatColor.BLUE + "What's that smell?\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$10");
        addShopItem(23, new ItemStack(Material.COOKED_BEEF, 5), 15, ChatColor.WHITE + "Steak", ChatColor.BLUE + "Arbies\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$15");
        addShopItem(24, new ItemStack(Material.GOLDEN_CARROT, 1), 15, ChatColor.WHITE + "Gold Carrot", ChatColor.BLUE + "Healthy Choice\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$15");
        addShopItem(25, new ItemStack(Material.GOLDEN_APPLE, 1), 250, ChatColor.WHITE + "Gold Apple", ChatColor.BLUE + "Not Steroids\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$225");
        addShopItem(28, new ItemStack(Material.GOLD_AXE, 1), 20, ChatColor.WHITE + "Axe", ChatColor.BLUE + "Break Wood\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$20");
        addShopItem(29, new ItemStack(Material.SHEARS, 1), 25, ChatColor.WHITE + "Scissors", ChatColor.BLUE + "Break Wool\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$25");
        addShopItem(37, new ItemStack(Material.WOOL, 64, DyeColor.BLUE.getData()), 50, ChatColor.WHITE + "Blue Blocks", ChatColor.BLUE + "Drip\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required");
        addShopItem(38, new ItemStack(Material.WOOL, 64, DyeColor.PURPLE.getData()), 50, ChatColor.WHITE + "Purple Blocks", ChatColor.BLUE + "Portal?\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required");
        addShopItem(39, new ItemStack(Material.WOOL, 64, DyeColor.MAGENTA.getData()), 50, ChatColor.WHITE + "Pink Blocks", ChatColor.BLUE + "Ice cream\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required");
        addShopItem(40, new ItemStack(Material.WOOL, 64, DyeColor.YELLOW.getData()), 50, ChatColor.WHITE + "Yellow Blocks", ChatColor.BLUE + "Who peed?\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required");
        addShopItem(41, new ItemStack(Material.WOOL, 64, DyeColor.GREEN.getData()), 50, ChatColor.WHITE + "Green Blocks", ChatColor.BLUE + "Bushy\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required");
        addShopItem(42, new ItemStack(Material.WOOL, 64, DyeColor.BROWN.getData()), 50, ChatColor.WHITE + "Brown Blocks", ChatColor.BLUE + "Ew\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required");
        addShopItem(43, new ItemStack(Material.WOOL, 64, DyeColor.RED.getData()), 50, ChatColor.WHITE + "Red Blocks", ChatColor.BLUE + "u mad?\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required");

        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
