package net.ntdi.tazpvp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.List;

public class ChatSpamListener implements Listener {

    private HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    @EventHandler()
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("op")) return;
        Long time = System.currentTimeMillis();
        try {
            Long lastUse = this.cooldowns.get(p.getName());
            if (lastUse + 2*1000 > time) {
                p.sendMessage(ChatColor.GREEN + "There is a 2 second chat cooldown");
                p.sendMessage(ChatColor.GREEN + "Buy a rank to remove it!");
                e.setCancelled(true);
            }
            if (lastUse + 1*1000 > time) {
                p.sendMessage(ChatColor.GREEN + "Dont Spam!");
                e.setCancelled(true);
            }
        } catch (Exception ex) {
        }
        try {
            cooldowns.remove(p.getName());
        } catch (Exception ex) {
        }
        cooldowns.put(p.getName(), time);
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        cooldowns.remove(p.getName());
    }

}
