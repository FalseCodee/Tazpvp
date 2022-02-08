package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnounceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(!(args.length == 0)) {
                if(sender.hasPermission("staff.announce")){
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY+" ["+ChatColor.RED+"TAZPVP"+ChatColor.DARK_GRAY+"] " + ChatColor.GREEN + StringUtils.buildString(args, 0));
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.playSound(pl.getLocation(), Sound.LEVEL_UP, 1, 1);
                    }
                }
            } else {
                return false;
            }
        } else {
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY+" ["+ChatColor.RED+"TAZPVP"+ChatColor.DARK_GRAY+"] " + ChatColor.GREEN + StringUtils.buildString(args, 0));
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.playSound(pl.getLocation(), Sound.LEVEL_UP, 1, 1);
            }
        }
        return true;
    }
}
