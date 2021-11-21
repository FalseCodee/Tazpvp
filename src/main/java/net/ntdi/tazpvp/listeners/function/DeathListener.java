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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Random;

public class DeathListener implements Listener {

    private final Random rand = new Random();
    public static HashMap<Player, Player> revenge = new HashMap<>();


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
                Location loc = p.getLocation();

                p.spigot().respawn();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.setVelocity(new Vector(0, 0, 0));
                        p.teleport(new Location(Bukkit.getWorld("spawn"), 0.5, 50, 0.5, 180, 0));
                    }
                }.runTaskLater(TazPvP.getInstance(), 1);
                //p.teleport(new Location(Bukkit.getWorld("spawn"), 0.5, 50, 0.5, 180, 0));

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
                loc.getWorld().playEffect(loc, Effect.LARGE_SMOKE, Material.REDSTONE_BLOCK);
                loc.getWorld().playEffect(loc, Effect.LARGE_SMOKE, Material.REDSTONE_BLOCK);

                if ((TazPvP.statsManager.getStreak(killer) % 5) == 0) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + killer.getDisplayName() + ChatColor.YELLOW + " has a kill streak of " + ChatColor.GOLD + TazPvP.statsManager.getStreak(killer));
                    TazPvP.statsManager.addMoney(killer, 25);
                    killer.sendMessage(ChatColor.GOLD + "+ $" + 25);
                }


                if (TazPvP.statsManager.getRebirths(killer) > 0) {
                    TazPvP.statsManager.addExp(killer, 8);
                    killer.playSound(killer.getLocation(), Sound.ORB_PICKUP, 1, 1 );
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1 );
                    killer.sendMessage(ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + "" + p.getName() + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 8 Experience");
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, true, false));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, true, false));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 92, 2, true, false));
                } else {
                    TazPvP.statsManager.addExp(killer, 5);
                    killer.playSound(killer.getLocation(), Sound.ORB_PICKUP, 1, 1 );
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1 );
                    killer.sendMessage(ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + "" + p.getName() + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 5 Experience");
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 92, 2, true, false));

                    if (rand.nextInt(10) == 1) {
                        if (TazPvP.perkManager.getButter(killer)){
                            if(killer.getHealth() <= 16) {
                                killer.setHealth(p.getHealth() + 4);
                            } else {
                                killer.setHealth(20);
                            }
                            killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*60, 0));
                            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*5, 0));
                            //killer.sendMessage(ChatColor.GRAY + "Butter Perk activated!");
                        }
                    }
                    if (rand.nextInt(10) == 2) {
                        if (TazPvP.perkManager.getAgility(killer)){
                            killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*5, 0));
                            //killer.sendMessage(ChatColor.GRAY + "Agility Perk activated!");
                        }
                    }
                    if (rand.nextInt(10) == 3) {
                        if (TazPvP.perkManager.getExtinguish(killer)){
                            killer.setFireTicks(0);
                            //killer.sendMessage(ChatColor.GRAY + "Extinguish Perk activated!");
                        }
                    }
                    if (rand.nextInt(10) == 4) {
                        if (TazPvP.perkManager.getHunger(killer)){
                            killer.setFoodLevel(20);
                            //killer.sendMessage(ChatColor.GRAY + "Hunger Perk activated!");
                        }
                    }
                    if(rand.nextInt(10) == 5){
                        if (TazPvP.perkManager.getStrength(killer)){
                            //killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 1, true, false));
                        }
                    }

                }
                p.sendMessage(ChatColor.DARK_GRAY + "You were killed by " + ChatColor.GRAY + "" + killer.getName());
//                if (Bukkit.getOnlinePlayers().size() < 10){
//                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + event.getEntity().getName() + " was killed by " + event.getEntity().getKiller().getName());
//                }
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
