package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Iterator;

public class combatLog implements Listener {

    public static HashMap<Player, Long> combatLog = new HashMap<Player, Long>();
    Long dur = 15L;

    @EventHandler
    public void onDamage(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player p = (Player) e.getDamager();
            Player killer = (Player) e.getEntity();
            if (!combatLog.containsKey(p) && combatLog.containsKey(killer)) {
                combatLog.put(p, dur);
                combatLog.put(killer, dur);
            } else {
                combatLog.replace(p, dur);
                combatLog.replace(killer, dur);
            }
        }
    }

    public static void tick() {
        if (!combatLog.isEmpty()) {
            Iterator<Player> it = combatLog.keySet().iterator();
            while (it.hasNext()) {
                Player p = it.next();
                if(combatLog.get(p)-1 > 0) {
                    combatLog.replace(p, combatLog.get(p) - 1);
                } else {
                    combatLog.remove(p);
                    p.sendMessage(ChatColor.GREEN + "You have been removed from combat log.");
                }
            }
        }
    }
}
