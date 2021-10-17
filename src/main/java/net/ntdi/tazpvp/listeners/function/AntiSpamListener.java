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

    HashMap<Player, String> previousMessages = new HashMap<>();
    ArrayList<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (cooldown.contains(player)) {
            if(player.hasPermission("chat.bypass")){
                return;
            }else{
                player.sendMessage(ChatColor.RED + "You are talking too fast! Please wait before sending your next massage!");
                event.setCancelled(true);
            }
        }

        if (previousMessages.containsKey(player)){
            if (message.equalsIgnoreCase(previousMessages.get(player))) {
                if(player.hasPermission("chat.bypass")){
                    return;
                }else{
                    player.sendMessage(ChatColor.RED + "Spamming is not allowed!");
                    event.setCancelled(true);
                }
            }
        }
        previousMessages.put(player, message);
        cooldown.add(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown.remove(player);
            }
        }.runTaskLater(TazPvP.getInstance(), 15);
    }
}
