package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class AntiSpamListener implements Listener {

    final HashMap<Player, String> previousMessages = new HashMap<>();
    final ArrayList<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (player.hasPermission("staff.chatbypass")){
            return;
        }

        if (cooldown.contains(player)) {
            event.setCancelled(true);
        }

        if (previousMessages.containsKey(player)){
            if (message.equalsIgnoreCase(previousMessages.get(player))) {
                event.setCancelled(true);

            }
        }
        previousMessages.put(player, message);
        cooldown.add(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown.remove(player);
            }
        }.runTaskLater(TazPvP.getInstance(), 10);
    }
}
