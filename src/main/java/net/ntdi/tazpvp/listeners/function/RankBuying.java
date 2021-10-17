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

public class RankBuying implements Listener {

    public void buyPro(Player p){
        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-250);
        // add checks if the player has that rank or higher
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String pexcmd = "pex user " + p.getName() + " group add pro";
        Bukkit.dispatchCommand(console, pexcmd);
        p.sendMessage(ChatColor.GREEN + "Successfully bought [PRO] rank to. Thank you!");

    }
    public void buyChampion(Player p){
        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-500);
        // add checks if the player has that rank or higher
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String pexcmd = "pex user " + p.getName() + " group add champion";
        Bukkit.dispatchCommand(console, pexcmd);
        p.sendMessage(ChatColor.GREEN + "Successfully bought [CHAMPION] rank to. Thank you!");
    }
    public void buyLegend(Player p){
        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-1000);
        // add checks if the player has that rank or higher
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String pexcmd = "pex user " + p.getName() + " group add legend";
        Bukkit.dispatchCommand(console, pexcmd);
        p.sendMessage(ChatColor.GREEN + "Successfully bought [LEGEND] rank. Thank you!");
    }
    public void buyMythical(Player p){
        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-1500);
        // add checks if the player has that rank or higher
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String pexcmd = "pex user " + p.getName() + " group add mythical";
        Bukkit.dispatchCommand(console, pexcmd);
        p.sendMessage(ChatColor.GREEN + "Successfully bought [MYTHICAL]. Thank you!");
    }

}
