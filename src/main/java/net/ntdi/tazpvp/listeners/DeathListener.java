package net.ntdi.tazpvp.listeners;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    public TazPvP plugin;

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        if(event.getEntity() instanceof Player) {
            event.getEntity().spigot().respawn();
            Player p = event.getEntity();

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.playSound(p.getLocation(), Sound.DIG_GRAVEL, 5, 1);
                TazPvP.statsManager.addDeaths(p, 1);
                TazPvP.statsManager.addKills(p.getKiller(), 1);
            }, 1L); // amount to wait in ticks , 20 ticks = 1 second
        }
    }
}
