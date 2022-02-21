package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.listeners.passive.combatLog;
import net.ntdi.tazpvp.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor, Listener {
    public final List<Player> vanishList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player vanisher = null;
        if(sender instanceof Player) {
            vanisher = (Player) sender;
        }

        if (vanisher.getWorld().getName().equalsIgnoreCase("duel")) {
            vanisher.sendMessage(ChatColor.RED + "You cannot vanish while in a duel.");
            return true;
        } else if(vanisher != null && vanisher.hasPermission("staff.vanish")) {
            if(!vanishList.contains(vanisher)) {
                if (combatLog.combatLog.containsKey(vanisher.getUniqueId())) {
                    vanisher.sendMessage(ChatColor.RED + "You cannot vanish while in combat.");
                } else {
                    vanishList.add(vanisher);
                    vanisher.setGameMode(GameMode.SPECTATOR);
                    PlayerUtils.hidePlayer(vanisher);
                    for (Player p : Bukkit.getOnlinePlayers()){
                        if (p.hasPermission("staff.vanish")){
                            p.sendMessage(ChatColor.GREEN + vanisher.getName() + " is now vanished.");
                        }
                    }
                    Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + vanisher.getName());
                    TazPvP.invunerable.add(vanisher);
                }
            } else {
                vanisher.teleport(new Location(Bukkit.getWorld("spawn"), 0.5, 50, 0.5, 180, 0));
                vanishList.remove(vanisher);
                vanisher.setGameMode(GameMode.ADVENTURE);
                PlayerUtils.showPlayer(vanisher);
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (p.hasPermission("staff.vanish")){
                        p.sendMessage(ChatColor.RED + vanisher.getName() + " is no longer vanished.");
                    }
                }
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + vanisher.getName());

                TazPvP.invunerable.remove(vanisher);


            }
        } else {
            return false;
        }
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        for(Player vanisher : vanishList)
            PlayerUtils.hidePlayer(vanisher);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        vanishList.remove(event.getPlayer());
    }

    @EventHandler
    public void intereact(PlayerInteractEvent event){
        if (vanishList.contains(event.getPlayer())){
            event.setCancelled(true);
        }
    }
}
