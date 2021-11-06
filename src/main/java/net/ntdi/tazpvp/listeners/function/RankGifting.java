package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class RankGifting implements Listener {

    public void giftPro(Player p, OfflinePlayer gifted){
        if (gifted != null){
            TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-250);
            // add checks if the player has that rank or higher
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String pexcmd = "pex user " + gifted.getName() + " group add vip";
            Bukkit.dispatchCommand(console, pexcmd);
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + "STORE " + ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " gifted" + ChatColor.YELLOW + " [VIP] " + ChatColor.GOLD + "to " + ChatColor.YELLOW + gifted.getName());
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        }else{
            p.sendMessage(ChatColor.RED + "ERROR: Player does not exist!");
        }
    }
    public void giftChampion(Player p, OfflinePlayer gifted){
        if (gifted != null){
            TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-500);
            // add checks if the player has that rank or higher
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String pexcmd = "pex user " + gifted.getName() + " group add mvp";
            Bukkit.dispatchCommand(console, pexcmd);
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + "STORE " + ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " gifted" + ChatColor.YELLOW + " [MVP] " + ChatColor.GOLD + "to " + ChatColor.YELLOW + gifted.getName());
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");


        }else{
            p.sendMessage(ChatColor.RED + "ERROR: Player does not exist!");
        }
    }
    public void giftLegend(Player p, OfflinePlayer gifted){
        if (gifted != null){
            TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-1000);
            // add checks if the player has that rank or higher
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String pexcmd = "pex user " + gifted.getName() + " group add mvp+";
            Bukkit.dispatchCommand(console, pexcmd);
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + "STORE " + ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " gifted" + ChatColor.YELLOW + " [MVP+] " + ChatColor.GOLD + "to " + ChatColor.YELLOW + gifted.getName());
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        }else{
            p.sendMessage(ChatColor.RED + "ERROR: Player does not exist!");
        }
    }
//    public void giftMythical(Player p, OfflinePlayer gifted){
//        if (gifted != null){
//            TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-1500);
//            // add checks if the player has that rank or higher
//            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
//            String pexcmd = "pex user " + gifted.getName() + " group add mythical";
//            Bukkit.dispatchCommand(console, pexcmd);
//            p.sendMessage(ChatColor.GREEN + "Successfully gifted [MYTHICAL] rank to " + gifted.getName() + ". Thank you!");
//            Bukkit.broadcastMessage("");
//            Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + p.getName() + " has just bought [MYTHICAL] for " + gifted.getName());
//            Bukkit.broadcastMessage("");
//
//        }else{
//            p.sendMessage(ChatColor.RED + "ERROR: Player does not exist!");
//        }
//    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if (TazPvP.ProGiftRank.contains(p)){
            OfflinePlayer giftee = Bukkit.getOfflinePlayer(event.getMessage());
            giftPro(p, giftee);
            TazPvP.ProGiftRank.remove(p);
            event.setCancelled(true);
        } else if (TazPvP.ChampionGiftRank.contains(p)){
            OfflinePlayer giftee = Bukkit.getOfflinePlayer(event.getMessage());
            giftChampion(p, giftee);
            TazPvP.ChampionGiftRank.remove(p);
            event.setCancelled(true);
        } else if (TazPvP.LegendGiftRank.contains(p)){
            OfflinePlayer giftee = Bukkit.getOfflinePlayer(event.getMessage());
            giftLegend(p, giftee);
            TazPvP.LegendGiftRank.remove(p);
            event.setCancelled(true);
        } //else if (TazPvP.MythicalGiftRank.contains(p)){
//            OfflinePlayer giftee = Bukkit.getOfflinePlayer(event.getMessage());
//            giftMythical(p, giftee);
//            TazPvP.MythicalGiftRank.remove(p);
//            event.setCancelled(true);
//        }
    }

}
