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
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Player killer = p.getKiller();
        if (killer != p) {
            TazPvP.achievementsManager.onDeath(p);
            if(killer != null){
                TazPvP.achievementsManager.onKill(killer);
//        if(!p.getLocation().getWorld().getName().equals(TazPvP.configFile.getString("arena.name"))){
//            return;
//        }
                if (BountyCommand.bounties.get(p.getUniqueId()) != null) {
                    TazPvP.statsManager.addMoney(killer, BountyCommand.bounties.get(p.getUniqueId()));
                    killer.sendMessage(ChatColor.YELLOW + "You have claimed " + p.getDisplayName() + "'s bounty for " + ChatColor.WHITE + "$" + BountyCommand.bounties.get(p.getUniqueId()));
                    BountyCommand.bounties.remove(p.getUniqueId());
                }
//        event.getEntity().spigot().respawn();

//        World wrld = Bukkit.getWorld("spawn");

//        Location loc = new Location(wrld, 0.5, 51, 0.5, 180, 0);

//        p.teleport(loc);

                if (killer.getMaxHealth() != 26){
                    killer.setMaxHealth(killer.getMaxHealth()+2);
                }
                p.setMaxHealth(20);

                p.playSound(p.getLocation(), Sound.FIRE, 5, 1);
                TazPvP.statsManager.addStreak(killer, 1);
                TazPvP.statsManager.addKills(killer, 1);
                TazPvP.statsManager.addMoney(killer, 7);
                if (TazPvP.statsManager.getRebirths(killer) > 0) {
                    TazPvP.statsManager.addExp(killer, 8);
                    killer.sendMessage(ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + "" + p.getName() + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 8 Experience");
                    killer.setHealth(Math.min(killer.getHealth() + 6, 20));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, true, false));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, true, false));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 1, true, false));
                } else {
                    TazPvP.statsManager.addExp(killer, 5);
                    killer.sendMessage(ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + "" + p.getName() + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 5 Experience");
                    killer.setHealth(Math.min(killer.getHealth() + 6, 20));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 1, true, false));
                }
                p.sendMessage(ChatColor.DARK_GRAY + "You were killed by " + ChatColor.GRAY + "" + killer.getName());
            }
            TazPvP.statsManager.setStreak(p, 0);
            TazPvP.statsManager.addDeaths(p, 1);

            }
        }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player && e.getEntity().getWorld().getName().equals("arena")) {
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
