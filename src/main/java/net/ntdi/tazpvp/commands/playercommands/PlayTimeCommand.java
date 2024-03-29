package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayTimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }
        if(player != null) {
            if(args.length == 0) {
                int seconds = player.getStatistic(Statistic.PLAY_ONE_TICK)/20;
                player.sendMessage(ChatColor.GREEN + "Your playtime is " + ChatColor.WHITE + StringUtils.secondsToDDHHMMSS(seconds));
            } else {
                OfflinePlayer target = Bukkit.getPlayer(args[0]);
                if(target.isOnline()) {
                    int seconds = target.getPlayer().getStatistic(Statistic.PLAY_ONE_TICK)/20;
                    player.sendMessage(ChatColor.GREEN + target.getName() + "'s playtime is " + ChatColor.WHITE + StringUtils.secondsToDDHHMMSS(seconds));
                } else {
                    int seconds = TazPvP.statsManager.getPlaytime(target);
                    player.sendMessage(ChatColor.GREEN + target.getName() + "'s playtime is " + ChatColor.WHITE + StringUtils.secondsToDDHHMMSS(seconds));
                }
            }
        }
        return true;
    }
}
