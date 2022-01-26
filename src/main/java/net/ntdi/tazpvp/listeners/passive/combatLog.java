package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class combatLog implements Listener {

    public static final HashMap<Player, Integer> combatLog = new HashMap<>();

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
