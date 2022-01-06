package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Iterator;

public class combatLog implements Listener {

    public static HashMap<Player, Integer> combatLog = new HashMap<Player, Integer>();
    Integer dur = 10;

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
            Iterator<Player> it = combatLog.keySet().iterator();
            while (it.hasNext()) {
                Player p = it.next();
                if(combatLog.get(p)-1 > 0) {
                    combatLog.replace(p, combatLog.get(p) - 1);
                } else {
                    combatLog.remove(p);
                    p.sendMessage(ChatColor.RED + "You are no longer in combat.");
                }
            }
        }
    }
}
