package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    public TazPvP plugin;

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        Player p = event.getEntity();
        Player killer = p.getKiller();
        if(!p.getLocation().getWorld().getName().equals(TazPvP.configFile.getString("arena.name"))){
            return;
        }
        event.getEntity().spigot().respawn();

            p.playSound(p.getLocation(), Sound.DIG_GRAVEL, 5, 1);
            TazPvP.statsManager.addDeaths(p, 1);

            TazPvP.statsManager.addKills(killer, 1);
            killer.sendMessage("+1 " + ChatColor.RED +"Kills");

            TazPvP.statsManager.addMoney(killer, 5);
            killer.sendMessage("+5 " + ChatColor.YELLOW +"Coins");

            killer.giveExp(5);
            killer.sendMessage("+5 " + ChatColor.GREEN +"Exp");
    }
}
