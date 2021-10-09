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

        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        Bukkit.broadcastMessage(ChatColor.WHITE + "");
        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "The server will be restarted in 30 seconds!");
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "IP: " + ChatColor.YELLOW + "" + ChatColor.GOLD +  "tazpvp.net");
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "DISCORD: " + ChatColor.YELLOW +  "/discord");
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "We'll be back online shortly!");
        Bukkit.broadcastMessage(ChatColor.WHITE + "");
        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

//        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
//        event.getPlayer().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  LEVEL UP " + ChatColor.DARK_AQUA + "Combat Lvl. " + ChatColor.AQUA + TazPvP.statsManager.getLevel(event.getPlayer()));
//        event.getPlayer().sendMessage("");
//        event.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "  REWARDS");
//        event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.BLUE + "1 Point");
//        event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.GOLD + "100 Coins");
//        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println("server was restarted with command by " + sender.getName());
                Bukkit.reload();
            }
        }.runTaskLater(TazPvP.getInstance(), 600L);

        return true;
    }
}
