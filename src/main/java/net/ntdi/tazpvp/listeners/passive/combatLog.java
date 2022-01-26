package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class combatLog implements Listener {

    public static final HashMap<Player, Integer> combatLog = new HashMap<>();
    Integer dur = 15;

    static List<Player> queue;

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player p = (Player) e.getDamager();
            Player killer = (Player) e.getEntity();
//            combatLog.put(p, dur);
//            combatLog.put(killer, dur);
//            p.sendMessage(ChatColor.GREEN + "You have been added from combat log.");
//            killer.sendMessage(ChatColor.GREEN + "You have been added from combat log.");
        }
    }

    public static void tick() {
        if (!combatLog.isEmpty()) {
            for (Player p : combatLog.keySet()) {
                if (combatLog.get(p) - 1 > 0) {
                    combatLog.replace(p, combatLog.get(p) - 1);
                } else {
//                    queue.add(p);
                    p.sendMessage(ChatColor.GREEN + "You have been removed from combat log.");
                    combatLog.remove(p);
                }
            }
//            for (Player p : queue) {
//                p.sendMessage(ChatColor.GREEN + "You have been removed from combat log.");
//                combatLog.remove(p);
//            }
        }
    }
}
