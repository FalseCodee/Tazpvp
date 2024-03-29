package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnmuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("staff.mute")){
            if(args.length < 1){
                return false;
            } else {
                Player muted = Bukkit.getPlayer(args[0]);
                if(muted != null){
                    if(TazPvP.punishmentManager.isMuted(muted)){
                        TazPvP.punishmentManager.removeMute(muted);
                        player.sendMessage(ChatColor.RED + muted.getName() + " has been unmuted.");
                        muted.sendMessage(ChatColor.RED+"You have been unmuted.");
                    } else {
                        player.sendMessage(ChatColor.RED + muted.getName() + " is not muted.");

                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                }
            }
        }

        return true;
    }
}
