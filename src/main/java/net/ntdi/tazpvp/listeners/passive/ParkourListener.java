package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ParkourListener implements Listener {
/*    @EventHandler
    public void onCheckPoint(PlayerInteractEvent event){
        if (event.getPlayer().getWorld().getName().equals("parkour")){
            if (event.getAction().equals(Action.PHYSICAL)){
                if (event.getClickedBlock().getType() == Material.STONE_PLATE){
                    System.out.println(event.getClickedBlock().getLocation() + " " + event.getClickedBlock().getType());
                    if (new Location(Bukkit.getWorld("parkour"), 0.5, 30, -39.5).distanceSquared(event.getPlayer().getLocation()) < 1.5 && TazPvP.statsManager.getCheckpoints(event.getPlayer()) < 1){
                        TazPvP.statsManager.setCheckpoints(event.getPlayer(), 1);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Checkpoint set");
                    } else if (new Location(Bukkit.getWorld("parkour"), 0.5, 30, -66.5).distanceSquared(event.getPlayer().getLocation()) < 1.5 && TazPvP.statsManager.getCheckpoints(event.getPlayer()) < 2){
                        TazPvP.statsManager.setCheckpoints(event.getPlayer(), 2);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Checkpoint set");
                    } else if (new Location(Bukkit.getWorld("parkour"), 0.5, 30, -92.5).distanceSquared(event.getPlayer().getLocation()) < 1.5 && TazPvP.statsManager.getCheckpoints(event.getPlayer()) < 3) {
                        TazPvP.statsManager.setCheckpoints(event.getPlayer(), 3);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Checkpoint set");
                    } else if (new Location(Bukkit.getWorld("parkour"), 0.5, 30, -123.5).distanceSquared(event.getPlayer().getLocation()) < 1.5 && TazPvP.statsManager.getCheckpoints(event.getPlayer()) < 4) {
                        TazPvP.statsManager.setCheckpoints(event.getPlayer(), 4);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Checkpoint set");
                    } else if (new Location(Bukkit.getWorld("parkour"), 0.5, 30, -146.5).distanceSquared(event.getPlayer().getLocation()) < 1.5 && TazPvP.statsManager.getCheckpoints(event.getPlayer()) < 5) {
                        TazPvP.statsManager.setCheckpoints(event.getPlayer(), 5);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Checkpoint set");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onParkourWorldEnter(PlayerChangedWorldEvent event){
        if (event.getPlayer().getWorld().getName().equals("parkour")){
            Player p = event.getPlayer();
            if (TazPvP.statsManager.getCheckpoints(p) == 1){
                p.teleport(new Location(p.getWorld(), 0.5, 30, -39.5, -179, 0));
            } else if (TazPvP.statsManager.getCheckpoints(p) == 2){
                p.teleport(new Location(p.getWorld(), 0.5, 30, -66.5, -179, 0));
            } else if (TazPvP.statsManager.getCheckpoints(p) == 3){
                p.teleport(new Location(p.getWorld(), 0.5, 30, -92.5, -179, 0));
            } else if (TazPvP.statsManager.getCheckpoints(p) == 4){
                p.teleport(new Location(p.getWorld(), 0.5, 30, -123.5, -179, 0));
            } else if (TazPvP.statsManager.getCheckpoints(p) == 5){
                p.teleport(new Location(p.getWorld(), 0.5, 30, -146.5, -179, 0));
            }
        }
    }*/

//    @EventHandler
//    public void onPoopy(PlayerMoveEvent event){
//        if (event.getPlayer().getWorld().getName().equals("parkour") && event.getPlayer().getGameMode() == GameMode.SURVIVAL){
//            if (event.getPlayer().getLocation().getY() <= 26){
//                Player p = event.getPlayer();
//                    p.teleport(new Location(p.getWorld(), 0.5, 30, 1, -179, 0));
//                  if (TazPvP.statsManager.getCheckpoints(p) == 0){
//                    p.teleport(new Location(p.getWorld(), 0.5, 30, 1, -179, 0));
//                }
//                else if (TazPvP.statsManager.getCheckpoints(p) == 1){
//                    p.teleport(new Location(p.getWorld(), 0.5, 30, -39.5, -179, 0));
//                } else if (TazPvP.statsManager.getCheckpoints(p) == 2){
//                    p.teleport(new Location(p.getWorld(), 0.5, 30, -66.5, -179, 0));
//                } else if (TazPvP.statsManager.getCheckpoints(p) == 3){
//                    p.teleport(new Location(p.getWorld(), 0.5, 30, -92.5, -179, 0));
//                } else if (TazPvP.statsManager.getCheckpoints(p) == 4){
//                    p.teleport(new Location(p.getWorld(), 0.5, 30, -123.5, -179, 0));
//                } else if (TazPvP.statsManager.getCheckpoints(p) == 5){
//                    p.teleport(new Location(p.getWorld(), 0.5, 30, -146.5, -179, 0));
//                }
//            }
//        }
//    }
}