package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
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
            if(args.length < 1){
                return false;
            } else if (args.length == 1){
                Player muted = Bukkit.getPlayer(args[0]);
                if(muted != null){
                    if(TazPvP.punishmentManager.isMuted(muted)){
                        player.sendMessage(ChatColor.RED + muted.getName() + " is already muted.");
                    } else {
                        //short mute to test
                        TazPvP.punishmentManager.initMute(muted, true);
                        player.sendMessage(ChatColor.RED + muted.getName() + " has been muted.");
                        muted.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                        muted.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "MUTE " + ChatColor.GRAY + "You have been muted permanently. If it was unfair, type " + ChatColor.WHITE + "/appeal");
                        muted.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                }
            }
        }

        return true;
    }
}
