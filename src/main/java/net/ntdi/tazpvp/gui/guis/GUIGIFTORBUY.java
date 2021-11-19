package net.ntdi.tazpvp.gui.guis;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.listeners.function.RankBuying;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIGIFTORBUY extends GUI {

    public int gooeey;

    public GUIGIFTORBUY(Player player, Integer gooey) {
        super(player, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "PURCHASE");
        this.gooeey = gooey;
        setItems();
        player.openInventory(inventory);
    }

    final Player p = player;

    public void addShopItem(int slot, ItemStack item, int runnable, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

            switch (runnable){
                case 1:
                    if (this.gooeey == 1){
                        if (TazPvP.statsManager.getCredits(p) >= 250) {
                            new RankBuying().buyPro(p);
                        } else {
                            TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! " + ChatColor.WHITE + "[CLICK HERE]");
                            nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                            p.spigot().sendMessage(nocred);
                        }
                    } else if (this.gooeey == 2){
                        if (TazPvP.statsManager.getCredits(p) >= 500) {
                            new RankBuying().buyChampion(p);
                        } else {
                            TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! " + ChatColor.WHITE + "[CLICK HERE]");
                            nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                            p.spigot().sendMessage(nocred);
                        }
                    } else if (this.gooeey == 3){
                        if (TazPvP.statsManager.getCredits(p) >= 1000) {
                            new RankBuying().buyLegend(p);
                        } else {
                            TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! " + ChatColor.WHITE + "[CLICK HERE]");
                            nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                            p.spigot().sendMessage(nocred);
                        }
                    }
                    break;
                case 2:
                    if (this.gooeey == 1){
                        if (TazPvP.statsManager.getCredits(p) >= 250) {
                            if (!TazPvP.ProGiftRank.contains(p) && !TazPvP.ChampionGiftRank.contains(p) && !TazPvP.LegendGiftRank.contains(p) && !TazPvP.MythicalGiftRank.contains(p)){
                                TazPvP.ProGiftRank.add(p);
                                p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "GIFT" + ChatColor.DARK_PURPLE + "Please type who you want to gift below.");
                            } else {
                                p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "GIFT" + ChatColor.DARK_PURPLE + "Please type who you want to gift below.");
                            }

                        } else {
                            TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! " + ChatColor.WHITE + "[CLICK HERE]");
                            nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                            p.spigot().sendMessage(nocred);
                        }
                    } else if (this.gooeey == 2){
                        if (TazPvP.statsManager.getCredits(p) >= 500) {
                            if (!TazPvP.ProGiftRank.contains(p) && !TazPvP.ChampionGiftRank.contains(p) && !TazPvP.LegendGiftRank.contains(p) && !TazPvP.MythicalGiftRank.contains(p)){
                                TazPvP.ChampionGiftRank.add(p);
                                p.sendMessage("type in chat who you want to gift rank to:");

                            }

                        } else {
                            TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! " + ChatColor.WHITE + "[CLICK HERE]");
                            nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                            p.spigot().sendMessage(nocred);
                        }
                    } else if (this.gooeey == 3){
                        if (TazPvP.statsManager.getCredits(p) >= 1000) {
                            if (!TazPvP.ProGiftRank.contains(p) && !TazPvP.ChampionGiftRank.contains(p) && !TazPvP.LegendGiftRank.contains(p) && !TazPvP.MythicalGiftRank.contains(p)){
                                TazPvP.LegendGiftRank.add(p);
                                p.sendMessage("type in chat who you want to gift rank to:");
                            }

                        } else {
                            TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! " + ChatColor.WHITE + "[CLICK HERE]");
                            nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://taznet.tebex.io/"));
                            p.spigot().sendMessage(nocred);
                        }
                    }
                    break;
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }
        if (this.gooeey == 1){
            addShopItem(12, new ItemStack(Material.EYE_OF_ENDER, 1), 1, ChatColor.DARK_AQUA + "Buy VIP", ChatColor.GRAY + "Buy yourself the VIP Rank\n" + ChatColor.AQUA + "250 Credits");
            addShopItem(14, new ItemStack(Material.ENDER_PEARL, 1), 2, ChatColor.DARK_AQUA + "Gift VIP", ChatColor.GRAY + "Gift someone the VIP Rank\n" + ChatColor.AQUA + "250 Credits");
        } else if (this.gooeey == 2){
            addShopItem(12, new ItemStack(Material.EYE_OF_ENDER, 1), 1, ChatColor.DARK_AQUA + "Buy MVP", ChatColor.GRAY + "Buy yourself the MVP Rank\n" + ChatColor.AQUA + "500 Credits");
            addShopItem(14, new ItemStack(Material.ENDER_PEARL, 1), 2, ChatColor.DARK_AQUA + "Gift MVP", ChatColor.GRAY + "Gift someone the MVP Rank\n" + ChatColor.AQUA + "500 Credits");
        } else if (this.gooeey == 3){
            addShopItem(12, new ItemStack(Material.EYE_OF_ENDER, 1), 1, ChatColor.DARK_AQUA + "Buy MVP+", ChatColor.GRAY + "Buy yourself the MVP+ Rank\n" + ChatColor.AQUA + "1000 Credits");
            addShopItem(14, new ItemStack(Material.ENDER_PEARL, 1), 2, ChatColor.DARK_AQUA + "Gift MVP+", ChatColor.GRAY + "Gift someone the MVP+ Rank\n" + ChatColor.AQUA + "1000 Credits");
        }
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }



}
