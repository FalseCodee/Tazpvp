package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.listeners.passive.combatLog;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class EntityDamageByEntityListener implements Listener {

    Random rand = new Random();

    @EventHandler
    public void entityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            if(!event.getDamager().getLocation().getWorld().getName().equals("arena") || !event.getEntity().getLocation().getWorld().getName().equals("grind")) {
                return;
            }

            OfflinePlayer damager = ((Player) event.getDamager()).getPlayer();

            if (TazPvP.punishmentManager.isBanned(damager)) {
                event.setCancelled(true);
                return;
            }
            if (TazPvP.invunerable.contains((Player) event.getEntity())){
                event.setCancelled(true);
                return;
            }

            Player p = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();

            if (TazPvP.AllowBlocks) {
                if (p != victim) {
                    if (!combatLog.combatLog.containsKey(p)) {
                        p.sendMessage(ChatColor.DARK_GREEN + "You are now in combat with " + ChatColor.GREEN + victim.getName());
                    }
                    if (!combatLog.combatLog.containsKey(victim)) {
                        victim.sendMessage(ChatColor.DARK_GREEN + "You are now in combat with " + ChatColor.GREEN + p.getName());
                    }
                    combatLog.combatLog.put(victim, 10);
                    combatLog.combatLog.put(p, 10);

                }
            }


            victim.setMetadata("combat", new FixedMetadataValue(TazPvP.getInstance(), p.getName()));

            if(((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.WOOD_SWORD) || ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.STONE_SWORD) || ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.IRON_SWORD) || ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.GOLD_SWORD) || ((Player) event.getDamager()).getInventory().getItemInHand().getType().equals(Material.DIAMOND_SWORD)){
                TazPvP.statsManager.addExp((OfflinePlayer) event.getDamager(), 1);
                if (TazPvP.statsManager.getExp(p) >= TazPvP.statsManager.getExpLeft(p)){
                    TazPvP.statsManager.setLevel(p, TazPvP.statsManager.getLevel(p)+1);
                    p.sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  LEVEL UP " + ChatColor.DARK_AQUA + "Combat Lvl. " + ChatColor.AQUA + TazPvP.statsManager.getLevel(p));
                    p.sendMessage("");
                    p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "  REWARDS");
                    p.sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.BLUE + "1 Point");
                    p.sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.GOLD + "60 Coins");
                    p.sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    TazPvP.statsManager.addPoints(p, 1);
                    TazPvP.statsManager.addMoney(p, 60);
                    TazPvP.statsManager.setExpLeft(p, TazPvP.statsManager.getExpLeft(p)*1.05);
                    TazPvP.statsManager.setExp(p, 0);
                    p.setLevel(TazPvP.statsManager.getLevel(p));
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1 );
                }

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        TazPvP.getInstance().initScoreboard(((Player) event.getDamager()).getPlayer());
                    }
                }.runTaskLater(TazPvP.getInstance(), 20L);
            } //else {
//                if (TazPvP.perkManager.getRobbery(p)){
//                    if(rand.nextInt(100) == 52){
//                        if (!TazPvP.robbery.containsKey((Player) event.getEntity())){
//                            TazPvP.robbery.put(victim, victim.getItemInHand());
//                            victim.getItemInHand().setType(Material.AIR);
//                            victim.sendMessage(ChatColor.GRAY + "Your item has been robbed by " + p.getName());
//                            victim.sendMessage(ChatColor.GRAY + "Your item will be returned in 3 seconds");
//                            p.sendMessage(ChatColor.GRAY + "You have robbed " + victim.getName() + "'s item in hand because of your robbery perk!");
//
//                            new BukkitRunnable() {
//                                @Override
//                                public void run() {
//                                victim.getInventory().addItem(TazPvP.robbery.get(victim));
//                                victim.sendMessage(ChatColor.GRAY + "Your item has been returned!");
//                                }
//                            }.runTaskLater(TazPvP.getInstance(), 60L);
//                        }
//                    }
//                }
            //}
        } else if (event.getEntity() instanceof Player && event.getDamager() instanceof Arrow){
            Player victim = (Player) event.getEntity();
            Arrow a = (Arrow) event.getDamager();
            if (a.getShooter() instanceof Player){
                Player shooter = (Player) a.getShooter();
                if (victim.getWorld().getName().equalsIgnoreCase("arena") || victim.getWorld().getName().equalsIgnoreCase("grind")){
                    if (TazPvP.AllowBlocks) {
                        if (shooter != victim) {
                            if (!combatLog.combatLog.containsKey(victim)) {
                                victim.sendMessage(ChatColor.DARK_GREEN + "You are now in combat with " + ChatColor.GREEN + shooter.getName());
                            }
                            if (!combatLog.combatLog.containsKey(shooter)) {
                                shooter.sendMessage(ChatColor.DARK_GREEN + "You are now in combat with " + ChatColor.GREEN + victim.getName());
                            }
                            combatLog.combatLog.put(victim, 10);
                            combatLog.combatLog.put(shooter, 10);
                        }
                    }
                }
            }
        }
    }
}
