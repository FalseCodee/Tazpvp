package net.ntdi.tazpvp.listeners;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.managers.DeathsManager;
import net.ntdi.tazpvp.managers.JoinsManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class DeathListener implements Listener {

    public TazPvP plugin;

    public void onPlayerDeathEvent(PlayerDeathEvent event){
        if(event.getEntity() instanceof Player) {
            event.getEntity().spigot().respawn();
            Player p = event.getEntity();

            DeathsManager manager = new DeathsManager(plugin);

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.playSound(p.getLocation(), Sound.DIG_GRAVEL, 5, 1);
                manager.addDeathsToPlayer(p, 1);
            }, 1L); // amount to wait in ticks , 20 ticks = 1 second
        }
    }
}
