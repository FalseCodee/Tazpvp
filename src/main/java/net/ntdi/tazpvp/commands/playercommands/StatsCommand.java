package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }
        if(player != null) {
            if(args.length == 0) {
                double kdr = TazPvP.statsManager.getKills(player) / (double) TazPvP.statsManager.getDeaths(player);
                player.sendMessage(ChatColor.GREEN + "Your stats are: ");
                player.sendMessage("");
                player.sendMessage(ChatColor.GREEN + "Kills: " + TazPvP.statsManager.getKills(player));
                player.sendMessage(ChatColor.GREEN + "Deaths: " + TazPvP.statsManager.getDeaths(player));
                player.sendMessage(ChatColor.GREEN + "KDR: " + kdr);
                player.sendMessage(ChatColor.GREEN + "Smacks: " + TazPvP.statsManager.getSmacks(player));
                player.sendMessage("");
                player.sendMessage(ChatColor.GREEN + "Level: " + TazPvP.statsManager.getLevel(player));
                player.sendMessage(ChatColor.GREEN + "Points: " + TazPvP.statsManager.getPoints(player));
                player.sendMessage(ChatColor.GREEN + "Money: " + TazPvP.statsManager.getMoney(player));
                player.sendMessage(ChatColor.GREEN + "Credits: " + TazPvP.statsManager.getCredits(player));
                player.sendMessage("");
                player.sendMessage(ChatColor.GREEN + "Banned: " + TazPvP.punishmentManager.isBanned(player));
                player.sendMessage(ChatColor.GREEN + "Muted: " + TazPvP.punishmentManager.isMuted(player));
                player.sendMessage(ChatColor.GREEN + "Warns: " + TazPvP.punishmentManager.getWarns(player));

            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    int seconds = target.getStatistic(Statistic.PLAY_ONE_TICK)/20;
                    double kdr = TazPvP.statsManager.getKills(target) / (double) TazPvP.statsManager.getDeaths(target);
                    player.sendMessage(ChatColor.GREEN + target.getName() + "'s stats are: ");
                    player.sendMessage("");
                    player.sendMessage(ChatColor.GREEN + "Kills: " + TazPvP.statsManager.getKills(target));
                    player.sendMessage(ChatColor.GREEN + "Deaths: " + TazPvP.statsManager.getDeaths(target));
                    player.sendMessage(ChatColor.GREEN + "KDR: " + kdr);
                    player.sendMessage(ChatColor.GREEN + "Smacks: " + TazPvP.statsManager.getSmacks(target));
                    player.sendMessage("");
                    player.sendMessage(ChatColor.GREEN + "Level: " + TazPvP.statsManager.getLevel(target));
                    player.sendMessage(ChatColor.GREEN + "Points: " + TazPvP.statsManager.getPoints(target));
                    player.sendMessage(ChatColor.GREEN + "Money: " + TazPvP.statsManager.getMoney(target));
                    player.sendMessage(ChatColor.GREEN + "Credits: " + TazPvP.statsManager.getCredits(target));
                    player.sendMessage("");
                    player.sendMessage(ChatColor.GREEN + "Banned: " + TazPvP.punishmentManager.isBanned(target));
                    player.sendMessage(ChatColor.GREEN + "Muted: " + TazPvP.punishmentManager.isMuted(target));
                    player.sendMessage(ChatColor.GREEN + "Warns: " + TazPvP.punishmentManager.getWarns(target));
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                }
            }
        }
        return true;
    }
}
