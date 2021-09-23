package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        Player p = event.getEntity();
        Player killer = p.getKiller();
        TazPvP.achievementsManager.onDeath(p);
        TazPvP.achievementsManager.onKill(killer);
//        if(!p.getLocation().getWorld().getName().equals(TazPvP.configFile.getString("arena.name"))){
//            return;
//        }
        TazPvP.statsManager.setStreak(p,0);
        TazPvP.statsManager.addStreak(killer, 1);
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

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player whoWasHit = (Player) e.getEntity();
            Player whoHit = (Player) e.getDamager();
            // Isn't this supposed to only be when they punch each other?
            if(whoHit.getItemInHand().getType() == Material.AIR) {
                TazPvP.achievementsManager.onSmack(whoHit);
            }
            TazPvP.statsManager.addSmacks(whoHit, 1);

        }
    }


}
