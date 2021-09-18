package net.ntdi.tazpvp.listeners;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandCancelerListener implements Listener {

    public TazPvP plugin;

    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();
        String[] args = message.split(" ");
        if (!player.hasPermission("op")) {
            if (args[0].toLowerCase().startsWith("/minecraft")) {
                event.setCancelled(true);

            }
        }
    }
}
