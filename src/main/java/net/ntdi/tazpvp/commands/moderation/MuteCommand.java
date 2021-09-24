package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("staff.mute")){
            if(args.length < 2){
                return false;
            } else {
                Player muted = Bukkit.getPlayer(args[0]);
                String reason = StringUtils.buildString(args, 1);
                if(muted != null){
                    if(TazPvP.punishmentManager.isMuted(muted)){
                        TazPvP.punishmentManager.removeMute(muted);
                        player.sendMessage(ChatColor.RED + muted.getName() + " has been unmuted.");
                        muted.sendMessage(ChatColor.RED+"You have been unmuted.");

                    } else {
                        //short mute to test
                        TazPvP.punishmentManager.initMute(muted, false, 30*60*1000);
                        player.sendMessage(ChatColor.RED + muted.getName() + " has been muted.");
                        muted.sendMessage(ChatColor.RED+"You have been muted for "+ChatColor.WHITE+reason);

                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                }
            }
        }

        return true;
    }
}
