package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.PlayerUtils;
import org.bukkit.*;
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

        Player p = event.getPlayer();
        p.sendMessage(ChatColor.AQUA + "");
        p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  |  TAZPVP SEASON 5");
        p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  |  Discord: /discord");
        p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  |  IP: tazpvp.net");
        p.sendMessage(ChatColor.AQUA + "");


        if(TazPvP.statsManager.statsFile.contains(event.getPlayer().getUniqueId().toString())) {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + p.getName());
        } else {
            TazPvP.statsManager.initPlayer(p);

            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "+" + ChatColor.GRAY + "] " + p.getName());
            Player player = event.getPlayer();
            PlayerUtils.equipStarter(player);
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + p.getName());

    }

    @EventHandler
    public void onPlayerWorldChange(PlayerChangedWorldEvent event){
        if (event.getPlayer().getWorld().getName().equals("ul_tazpvp")) {
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
