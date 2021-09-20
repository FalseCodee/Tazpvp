package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("staff.ban")){
            if(args.length < 2){
                return false;
            } else {
                Player banned = Bukkit.getPlayer(args[0]);
                String reason = StringUtils.buildString(args, 1);
                if(banned != null){
                    if(TazPvP.punishmentManager.isBanned(banned)){
                        TazPvP.punishmentManager.removeBan(player);
                        player.sendMessage(ChatColor.RED + banned.getName() + " has been unbanned.");
                        banned.sendMessage(ChatColor.RED+"You have been unbanned.");

                    } else {
                        //short ban to test
                        TazPvP.punishmentManager.initBan(banned, false, 5*1000);
                        player.sendMessage(ChatColor.RED + banned.getName() + " has been banned.");
                        banned.sendMessage(ChatColor.RED+"You have been banned for "+ChatColor.WHITE+reason);

                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                }
            }
        }

        return true;
    }
}
