package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void entityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            if(!event.getDamager().getLocation().getWorld().getName().equals(TazPvP.configFile.getString("arena.name"))){
                return;
            }

            OfflinePlayer damager = ((Player) event.getDamager()).getPlayer();

            if (TazPvP.punishmentManager.isBanned(damager)) {
                event.setCancelled(true);
                return;
            }
            if (TazPvP.invunerable.contains((Player) event.getEntity())){
                event.setCancelled(true);
                return;
            }

            Player p = (Player) event.getDamager();



            if(((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.WOOD_SWORD) || ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.STONE_SWORD) || ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.IRON_SWORD) || ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.GOLD_SWORD) || ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.DIAMOND_SWORD)){
                TazPvP.statsManager.addExp((OfflinePlayer) event.getDamager(), 1);
                if (TazPvP.statsManager.getExp(p) >= TazPvP.statsManager.getExpLeft(p)){
                    TazPvP.statsManager.setLevel(p, TazPvP.statsManager.getLevel(p)+1);
                    p.sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  LEVEL UP " + ChatColor.DARK_AQUA + "Combat Lvl. " + ChatColor.AQUA + TazPvP.statsManager.getLevel(p));
                    p.sendMessage("");
                    p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "  REWARDS");
                    p.sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.BLUE + "1 Point");
                    p.sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.GOLD + "60 Coins");
                    p.sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    TazPvP.statsManager.addPoints(p, 1);
                    TazPvP.statsManager.addMoney(p, 60);
                    TazPvP.statsManager.setExpLeft(p, TazPvP.statsManager.getExpLeft(p)*1.19);
                    TazPvP.statsManager.setExp(p, 0);
                    p.setLevel(TazPvP.statsManager.getLevel(p));

                }
            }
        }
    }
}
