package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class RankBuying implements Listener {

    public void buyPro(Player p){
        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-250);
        // add checks if the player has that rank or higher
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String pexcmd = "pex user " + p.getName() + " group add vip";
        Bukkit.dispatchCommand(console, pexcmd);
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + "STORE " + ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " just purchased" + ChatColor.YELLOW + " [VIP] " + ChatColor.GOLD + "in the store!");
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    }
    public void buyChampion(Player p){
        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-500);
        // add checks if the player has that rank or higher
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String pexcmd = "pex user " + p.getName() + " group add mvp";
        Bukkit.dispatchCommand(console, pexcmd);
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + "STORE " + ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " just purchased" + ChatColor.YELLOW + " [MVP] " + ChatColor.GOLD + "in the store!");
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    }
    public void buyLegend(Player p){
        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-1000);
        // add checks if the player has that rank or higher
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String pexcmd = "pex user " + p.getName() + " group add mvp+";
        Bukkit.dispatchCommand(console, pexcmd);
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + "STORE " + ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " just purchased" + ChatColor.YELLOW + " [MVP+] " + ChatColor.GOLD + "in the store!");
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    }
//    public void buyMythical(Player p){
//        TazPvP.statsManager.setCredits(p, TazPvP.statsManager.getCredits(p)-1500);
//        // add checks if the player has that rank or higher
//        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
//        String pexcmd = "pex user " + p.getName() + " group add mythical";
//        Bukkit.dispatchCommand(console, pexcmd);
//        p.sendMessage(ChatColor.GREEN + "Successfully bought [MYTHICAL]. Thank you!");
//        Bukkit.broadcastMessage("");
//        Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + p.getName() + " has just bought [MYTHICAL] rank!");
//        Bukkit.broadcastMessage("");
//    }
}
