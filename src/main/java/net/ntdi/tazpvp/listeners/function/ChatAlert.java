package net.ntdi.tazpvp.listeners.function;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatAlert {
    public void Text(Plugin plugin){
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("ALERT Join our community using /discord!");
            }
        }.runTaskTimer(plugin, 7200, 7200);
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("ALERT Check out our great credit deals with /buy!");
            }
        }.runTaskTimer(plugin, 8200, 8200);
    }
}

