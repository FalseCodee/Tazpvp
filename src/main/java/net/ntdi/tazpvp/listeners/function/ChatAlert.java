package net.ntdi.tazpvp.listeners.function;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatAlert {
    public void Text(Plugin plugin){
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("This will run every second!");
            }
        }.runTaskTimer(plugin, 20, 20);
    }
}

