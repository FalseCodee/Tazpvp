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
            event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            event.getPlayer().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  LEVEL UP " + ChatColor.DARK_AQUA + "Combat " + ChatColor.DARK_GRAY + "Lvl. " + ChatColor.AQUA + TazPvP.statsManager.getLevel(event.getPlayer()));
            event.getPlayer().sendMessage("");
            event.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "  REWARDS");
            event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.BLUE + "1 Point");
            event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.GOLD + "100 Coins");
            event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        }
    }
}
