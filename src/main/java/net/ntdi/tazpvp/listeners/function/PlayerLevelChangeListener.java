package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class PlayerLevelChangeListener implements Listener {

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent event) {
//        if(!event.getPlayer().getLocation().getWorld().getName().equals(TazPvP.configFile.getString("arena.name"))){
//            return;
//        }
        if (event.getNewLevel() != 0) {
            TazPvP.statsManager.setLevel(event.getPlayer(), event.getNewLevel());
            event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "Level up! " + ChatColor.AQUA + " You are now level " + TazPvP.statsManager.getLevel(event.getPlayer()));
        }
    }
}
