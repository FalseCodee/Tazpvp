package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
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

            if(((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.WOOD_SWORD) ||
                    ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.STONE_SWORD) ||
                    ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.IRON_SWORD) ||
                    ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.GOLD_SWORD) ||
                    ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.DIAMOND_SWORD)){
                ((Player) event.getDamager()).giveExp(1);

            }
        }
    }
}
