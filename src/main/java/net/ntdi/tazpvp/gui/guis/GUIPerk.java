package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
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

    final Player p = player;

    public void addShopItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    if (!TazPvP.perkManager.getSaveBlocks(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 350){
                            TazPvP.statsManager.addMoney(p, -350);
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
                        if (TazPvP.statsManager.getMoney(p) >= 400){
                            TazPvP.statsManager.addMoney(p, -400);
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
                        if (TazPvP.statsManager.getMoney(p) >= 200){
                            TazPvP.statsManager.addMoney(p, -200);
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
                        if (TazPvP.statsManager.getMoney(p) >= 150){
                            TazPvP.statsManager.addMoney(p, -150);
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
                        if (TazPvP.statsManager.getMoney(p) >= 300){
                            TazPvP.statsManager.addMoney(p, -300);
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
                        if (TazPvP.statsManager.getMoney(p) >= 50){
                            TazPvP.statsManager.addMoney(p, -50);
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
                case 10:
                    if (!TazPvP.perkManager.getArrow(p)){
                        if (TazPvP.statsManager.getMoney(p) >= 250){
                            TazPvP.statsManager.addMoney(p, -250);
                            TazPvP.perkManager.setArrow(p, true);
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
    @SuppressWarnings("deprecation")
    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }

        ItemStack pickaxe = new ItemStack(Material.GOLD_PICKAXE, 1);
        ItemMeta pickaxeMeta = pickaxe.getItemMeta();
        pickaxeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pickaxe.setItemMeta(pickaxeMeta);

        addShopItem(10, new ItemStack(Material.WOOD, 1), 1, ChatColor.DARK_AQUA + "Builder I", ChatColor.GRAY + "Chance to not consume placed blocks.\n" + ChatColor.YELLOW + "$350");
        addShopItem(11, new ItemStack(Material.GOLD_INGOT, 1), 2, ChatColor.DARK_AQUA + "Health I", ChatColor.GRAY + "Chance to gain absorption hearts on kill.\n" + ChatColor.YELLOW + "$400");
        addShopItem(12, new ItemStack(Material.EYE_OF_ENDER, 1), 3, ChatColor.DARK_AQUA + "Agility I", ChatColor.GRAY + "Chance to get speed on kill.\n" + ChatColor.YELLOW + "$200");
        addShopItem(13, new ItemStack(Material.RED_ROSE, 1), 4, ChatColor.DARK_AQUA + "Firefighter I", ChatColor.GRAY + "Instantly extinguish yourself when on fire.\n" + ChatColor.YELLOW + "$150");
        addShopItem(14, new ItemStack(Material.FEATHER, 1), 5, ChatColor.DARK_AQUA + "Super I", ChatColor.GRAY + "Reduce fall damage.\n" + ChatColor.YELLOW + "$500");
        addShopItem(15, new ItemStack(Material.COOKED_BEEF, 1), 6, ChatColor.DARK_AQUA + "Not Hobo I", ChatColor.GRAY + "Chance to gain hunger bars on kill.\n" + ChatColor.YELLOW + "$300");
        addShopItem(16, pickaxe, 7, ChatColor.DARK_AQUA + "Miner I", ChatColor.GRAY + "Chance to gain haste when mining.\n" + ChatColor.YELLOW + "$50");
        addShopItem(19, new ItemStack(Material.POTION, 1), 9, ChatColor.DARK_AQUA + "Buff I", ChatColor.GRAY + "Chance to gain strength after kill.\n" + ChatColor.YELLOW + "$1000");
        addShopItem(20, new ItemStack(Material.ARROW, 1), 9, ChatColor.DARK_AQUA + "Archer I", ChatColor.GRAY + "Chance to get your arrow back.\n" + ChatColor.YELLOW + "$250");

        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }

}
