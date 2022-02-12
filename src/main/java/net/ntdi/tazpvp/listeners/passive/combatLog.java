package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class combatLog implements Listener {

    public static final WeakHashMap<Player, Integer> combatLog = new WeakHashMap<>();

    public static void tick() {
        List<Player> queue = new ArrayList<>();

        if (!combatLog.isEmpty()) {
            for (Player p : combatLog.keySet()) {
                if (combatLog.get(p) - 1 > 0) {
                    combatLog.replace(p, combatLog.get(p) - 1);
                } else {
                    queue.add(p);
                }
            }
            for (Player p : queue) {
                combatLog.remove(p);
                p.sendMessage(ChatColor.GREEN + "You have been removed from combat log.");
            }
        }
    }
}
