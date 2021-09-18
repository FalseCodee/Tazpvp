package net.ntdi.tazpvp.listeners;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandCancelerListener implements Listener {

    public TazPvP plugin;

    public void onCommandSend(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("op")) {
            if (event.getMessage().contains("/minecraft:me")) {
                event.setCancelled(true);
            }
        }


    }

}
