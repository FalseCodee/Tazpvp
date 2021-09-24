package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.commands.functions.BountyCommand;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        if(BountyCommand.bounties.get(p.getUniqueId()) != null) {
            TazPvP.statsManager.addMoney(killer, BountyCommand.bounties.get(p.getUniqueId()));
            killer.sendMessage(ChatColor.YELLOW + "You have claimed " + p.getDisplayName() + "'s bounty for " + ChatColor.WHITE + "$" + BountyCommand.bounties.get(p.getUniqueId()));
            BountyCommand.bounties.remove(p.getUniqueId());
        }
        TazPvP.statsManager.setStreak(p,0);
        TazPvP.statsManager.addStreak(killer, 1);
        event.getEntity().spigot().respawn();

            p.playSound(p.getLocation(), Sound.DIG_GRAVEL, 5, 1);
            TazPvP.statsManager.addDeaths(p, 1);

            TazPvP.statsManager.addKills(killer, 1);
            killer.sendMessage("+1 " + ChatColor.RED +"Kills");

            TazPvP.statsManager.addMoney(killer, 5);
            killer.sendMessage("+5 " + ChatColor.YELLOW +"Coins");
        if(TazPvP.statsManager.getRebirths(killer) > 0) {
            killer.giveExp(8);
            killer.sendMessage("+8 " + ChatColor.GREEN +"Exp");
            killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1,true, false));
            killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 1,true, false));
        } else {
            killer.giveExp(5);
            killer.sendMessage("+5 " + ChatColor.GREEN +"Exp");
        }

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
