package net.ntdi.tazpvp.listeners.passive;

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
        System.out.println(event.getMessage());
//        String message = event.getMessage();
//        String[] args = message.split(" ");
        if (!player.hasPermission("staff.commandbypass")) {
            if (event.getMessage().toLowerCase().startsWith("/minecraft")) {
                event.setCancelled(true);
            }
        } else if (player.hasPermission("staff.commandbypass")) {
            System.out.println(event.getPlayer().getName() + ": " + event.getMessage());

        }
    }
}
