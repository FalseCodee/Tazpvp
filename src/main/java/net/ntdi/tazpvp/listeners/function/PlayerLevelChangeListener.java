package net.ntdi.tazpvp.listeners.function;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class PlayerLevelChangeListener implements Listener {

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent event) {
//        if(!event.getPlayer().getLocation().getWorld().getName().equals(TazPvP.configFile.getString("arena.name"))){
//            return;
//        }
//        if (event.getNewLevel() != 0) {
//            TazPvP.statsManager.setLevel(event.getPlayer(), event.getNewLevel());
//            event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
//            event.getPlayer().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  LEVEL UP " + ChatColor.DARK_AQUA + "Combat Lvl. " + ChatColor.AQUA + TazPvP.statsManager.getLevel(event.getPlayer()));
//            event.getPlayer().sendMessage("");
//            event.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "  REWARDS");
//            event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.BLUE + "1 Point");
//            event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.GOLD + "60 Coins");
//            event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
//            TazPvP.statsManager.addPoints(event.getPlayer(), 1);
//            TazPvP.statsManager.addMoney(event.getPlayer(), 60);
//        }
    }

    @EventHandler
    public void onGainExp(PlayerExpChangeEvent event){
        event.setAmount(0);
    }

}
