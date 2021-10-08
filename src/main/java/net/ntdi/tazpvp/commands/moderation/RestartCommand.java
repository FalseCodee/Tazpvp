package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Bukkit.broadcastMessage(ChatColor.WHITE + "-----------------------------------------------");
        Bukkit.broadcastMessage(ChatColor.WHITE + "");
        Bukkit.broadcastMessage(ChatColor.RED + "The server will be restarted in 30 seconds!");
        Bukkit.broadcastMessage(ChatColor.AQUA + "IP: " + ChatColor.BOLD + "tazpvp.net");
        Bukkit.broadcastMessage(ChatColor.AQUA + "DISCORD: " + ChatColor.GOLD + "" + ChatColor.BOLD + "/discord");
        Bukkit.broadcastMessage(ChatColor.AQUA + "We'll be back online shortly!");
        Bukkit.broadcastMessage(ChatColor.WHITE + "");
        Bukkit.broadcastMessage(ChatColor.WHITE + "-----------------------------------------------");

        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println("server was restarted with command by " + sender.getName());
                Bukkit.reload();
            }
        }.runTaskTimer(TazPvP.getInstance(), 600L, 0L);

        return true;
    }
}
