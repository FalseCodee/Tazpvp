package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.listeners.function.RankBuying;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIPerk extends GUI {
    public GUIPerk(Player player) {
        super(player, 36, "BUY PERKS");
        setItems();
        player.openInventory(inventory);
    }

    Player p = player;

    public void addShopItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    if (!TazPvP.perkManager.getSaveBlocks(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 500){
                            TazPvP.statsManager.addMoney(p, -500);
                            TazPvP.perkManager.setSaveBlocks(p, true);
                            p.sendMessage(ChatColor.GREEN + "Successfully bought perk!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Not enough money!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "You already own this perk!");
                    }
                    break;
                case 2:
                    if (!TazPvP.perkManager.getButter(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 500){
                            TazPvP.statsManager.addMoney(p, -500);
                            TazPvP.perkManager.setButter(p, true);
                            p.sendMessage(ChatColor.GREEN + "Successfully bought perk!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Not enough money!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "You already own this perk!");
                    }
                    break;
                case 3:
                    if (!TazPvP.perkManager.getAgility(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 500){
                            TazPvP.statsManager.addMoney(p, -500);
                            TazPvP.perkManager.setAgility(p, true);
                            p.sendMessage(ChatColor.GREEN + "Successfully bought perk!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Not enough money!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "You already own this perk!");
                    }
                    break;
                case 4:
                    if (!TazPvP.perkManager.getExtinguish(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 250){
                            TazPvP.statsManager.addMoney(p, -250);
                            TazPvP.perkManager.setExtinguish(p, true);
                            p.sendMessage(ChatColor.GREEN + "Successfully bought perk!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Not enough money!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "You already own this perk!");
                    }
                    break;
                case 5:
                    if (!TazPvP.perkManager.getFallDamage(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 500){
                            TazPvP.statsManager.addMoney(p, -500);
                            TazPvP.perkManager.setFallDamage(p, true);
                            p.sendMessage(ChatColor.GREEN + "Successfully bought perk!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Not enough money!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "You already own this perk!");
                    }
                    break;
                case 6:
                    if (!TazPvP.perkManager.getHunger(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 500){
                            TazPvP.statsManager.addMoney(p, -500);
                            TazPvP.perkManager.setHunger(p, true);
                            p.sendMessage(ChatColor.GREEN + "Successfully bought perk!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Not enough money!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "You already own this perk!");
                    }
                    break;
                case 7:
                    if (!TazPvP.perkManager.getHaste(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 500){
                            TazPvP.statsManager.addMoney(p, -500);
                            TazPvP.perkManager.setHaste(p, true);
                            p.sendMessage(ChatColor.GREEN + "Successfully bought perk!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Not enough money!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "You already own this perk!");
                    }
                    break;
//                case 8:
//                    if (!TazPvP.perkManager.getRobbery(p)){
//                        if (TazPvP.statsManager.getMoney(p) >= 500){
//                            TazPvP.statsManager.addMoney(p, -500);
//                            TazPvP.perkManager.setRobbery(p, true);
//                            p.sendMessage(ChatColor.GREEN + "Successfully bought perk!");
//                        }else{
//                            p.sendMessage(ChatColor.RED + "Not enough money!");
//                        }
//                    }else{
//                        p.sendMessage(ChatColor.RED + "You already own this perk!");
//                    }
//                    break;
                case 9:
                    if (!TazPvP.perkManager.getStrength(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 1000){
                            TazPvP.statsManager.addMoney(p, -1000);
                            TazPvP.perkManager.setStrength(p, true);
                            p.sendMessage(ChatColor.GREEN + "Successfully bought perk!");
                        }else{
                            p.sendMessage(ChatColor.RED + "Not enough money!");
                        }
                    }else{
                        p.sendMessage(ChatColor.RED + "You already own this perk!");
                    }
                    break;
            }
        });
    }

    ItemStack flower = new ItemStack(Material.RED_ROSE, 1, (short) 1);

    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        ItemStack pickaxe = new ItemStack(Material.GOLD_PICKAXE, 1);
        ItemMeta pickaxeMeta = pickaxe.getItemMeta();
        pickaxeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pickaxe.setItemMeta(pickaxeMeta);

        addShopItem(10, new ItemStack(Material.WOOD, 1), 1, ChatColor.DARK_AQUA + "BUY SAVE BLOCKS PERK", ChatColor.GRAY + "Get a chance to get not loose your blocks when you place it\n" + ChatColor.YELLOW + "$500");
        addShopItem(11, new ItemStack(Material.GOLD_INGOT, 1), 2, ChatColor.DARK_AQUA + "BUY BUTTER PERK", ChatColor.GRAY + "Get a chance to get butter affects on kill\n" + ChatColor.YELLOW + "$500");
        addShopItem(12, new ItemStack(Material.EYE_OF_ENDER, 1), 3, ChatColor.DARK_AQUA + "BUY AGILITY PERK", ChatColor.GRAY + "Get a chance to get agility affects on kill\n" + ChatColor.YELLOW + "$500");
        addShopItem(13, new ItemStack(Material.RED_ROSE, 1), 4, ChatColor.DARK_AQUA + "BUY EXTINGUISH PERK", ChatColor.GRAY + "Get a chance to get extinguish yourself on hit\n" + ChatColor.YELLOW + "$250");
        addShopItem(14, new ItemStack(Material.FEATHER, 1), 5, ChatColor.DARK_AQUA + "BUY FALLDAMAGE PERK", ChatColor.GRAY + "Get a chance to get ignore fall damage when you fall\n" + ChatColor.YELLOW + "$500");
        addShopItem(15, new ItemStack(Material.COOKED_BEEF, 1), 6, ChatColor.DARK_AQUA + "BUY HUNGER PERK", ChatColor.GRAY + "Get a chance to get refuel your hunger bar on kill\n" + ChatColor.YELLOW + "$500");
        addShopItem(16, pickaxe, 7, ChatColor.DARK_AQUA + "BUY HASTE PERK", ChatColor.GRAY + "Get a chance to get haste affects on ore mined\n" + ChatColor.YELLOW + "$500");
        addShopItem(19, new ItemStack(Material.POTION, 1), 9, ChatColor.DARK_AQUA + "BUY STRENGTH PERK", ChatColor.GRAY + "Get a chance to get strength affects on kill\n" + ChatColor.YELLOW + "$1000");

        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
