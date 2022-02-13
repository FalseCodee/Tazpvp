package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

public class combatLog implements Listener {

    public static final WeakHashMap<UUID, Integer> combatLog = new WeakHashMap<>();

    public static void tick() {
        List<UUID> queue = new ArrayList<>();

        if (!combatLog.isEmpty()) {
            for (UUID uuid : combatLog.keySet()) {
                if (combatLog.get(uuid) - 1 > 0) {
                    combatLog.replace(uuid, combatLog.get(uuid) - 1);
                } else {
                    queue.add(uuid);
                }
            }
            for (UUID uuid : queue) {
                combatLog.remove(uuid);
                Bukkit.getPlayer(uuid).sendMessage(ChatColor.RED + "You are no longer in combat.");
            }
        }
    }
}
