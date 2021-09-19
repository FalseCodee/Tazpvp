package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.managers.CurrencyManager;
import net.ntdi.tazpvp.managers.JoinsManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class WelcomeListener implements Listener {

    public TazPvP plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player p = (Player) event.getPlayer();
        p.sendMessage(ChatColor.AQUA + "");
        p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  |  TAZPVP SEASON 5");
        p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  |  Discord: /discord");
        p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  |  IP: tazpvp.net");
        p.sendMessage(ChatColor.AQUA + "");

        if(!TazPvP.statsManager.statsFile.contains(event.getPlayer().getUniqueId().toString())) {
            TazPvP.statsManager.initPlayer(p);
        }

        if (TazPvP.joinsManager.getFromList(p)) {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + p.getName());
        } else {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "+" + ChatColor.GRAY + "] " + p.getName());
            TazPvP.joinsManager.addToList(p);
            Player player = event.getPlayer();
            if (player.getInventory().getArmorContents() != null) {
                ItemStack armor1 = new ItemStack(Material.LEATHER_BOOTS);
                ItemStack armor2 = new ItemStack(Material.LEATHER_HELMET);
                ItemStack armor3 = new ItemStack(Material.LEATHER_CHESTPLATE);
                ItemStack armor4 = new ItemStack(Material.LEATHER_LEGGINGS);
                ItemStack sword = new ItemStack(Material.WOOD_SWORD);
                ItemStack pickaxe = new ItemStack(Material.WOOD_PICKAXE);
                ItemStack fishingrod = new ItemStack(Material.FISHING_ROD);
                ItemStack bow = new ItemStack(Material.BOW);
                ItemStack steak = new ItemStack(Material.COOKED_BEEF, 10);
                ItemStack arrow = new ItemStack(Material.ARROW, 15);

                ItemMeta meta1 = armor1.getItemMeta();
                meta1.spigot().setUnbreakable(true);
                armor1.setItemMeta(meta1);

                ItemMeta meta2 = armor2.getItemMeta();
                meta2.spigot().setUnbreakable(true);
                armor2.setItemMeta(meta2);

                ItemMeta meta3 = armor3.getItemMeta();
                meta3.spigot().setUnbreakable(true);
                armor3.setItemMeta(meta3);

                ItemMeta meta4 = armor4.getItemMeta();
                meta4.spigot().setUnbreakable(true);
                armor4.setItemMeta(meta4);

                ItemMeta swordMeta = sword.getItemMeta();
                swordMeta.spigot().setUnbreakable(true);
                sword.setItemMeta(swordMeta);

                ItemMeta pickaxeMeta = sword.getItemMeta();
                pickaxeMeta.spigot().setUnbreakable(true);
                pickaxe.setItemMeta(pickaxeMeta);

                ItemMeta fishingMeta = fishingrod.getItemMeta();
                fishingMeta.spigot().setUnbreakable(true);
                fishingrod.setItemMeta(fishingMeta);

                ItemMeta bowMeta = bow.getItemMeta();
                bowMeta.spigot().setUnbreakable(true);
                bow.setItemMeta(bowMeta);


                PlayerInventory inv = player.getInventory();
                inv.setHelmet(armor2);
                inv.setChestplate(armor3);
                inv.setLeggings(armor4);
                inv.setBoots(armor1);
                inv.addItem(sword);
                inv.addItem(fishingrod);
                inv.addItem(bow);
                inv.addItem(pickaxe);
                inv.addItem(steak);
                inv.addItem(arrow);
            }
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player p = (Player) event.getPlayer();
        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + p.getName());

    }

    @EventHandler
    public void onPlayerWorldChange(PlayerChangedWorldEvent event){
        if (event.getPlayer().getWorld().getName() == "ul_tazpvp") {
            World world = event.getPlayer().getWorld();
            Player p = event.getPlayer();

            int min = 1;
            int max = 6;

            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);

            if (random_int == 1) {
                Location location = new Location(world, 0, 30, 0);
                p.teleport(location);
            } else if (random_int == 2) {
                Location location = new Location(world, -18, 30, -11);
                p.teleport(location);
            } else if (random_int == 3) {
                Location location = new Location(world, 13, 30, -28);
                p.teleport(location);
            } else if (random_int == 4) {
                Location location = new Location(world, 26, 30, -1);
                p.teleport(location);
            } else if (random_int == 5) {
                Location location = new Location(world, 6, 30, 35);
                p.teleport(location);
            } else {
                Location location = new Location(world, -30, 30, 16);
                p.teleport(location);
            }
        }
    }
}
