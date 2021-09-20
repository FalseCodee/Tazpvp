package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("op") && args.length > 1){
            Player warned = Bukkit.getPlayer(args[0]);
            if(warned != null) {
                warned.sendMessage(ChatColor.RED + "You have been warned for " + ChatColor.WHITE + StringUtils.buildString(args, 1));
                TazPvP.punishmentManager.addWarn(warned, StringUtils.buildString(args, 1));
                player.sendMessage(ChatColor.DARK_AQUA + "Success!" + warned.getName() + " now has " + ChatColor.WHITE + TazPvP.punishmentManager.getWarns(warned).size() + ChatColor.DARK_AQUA + " warns.");
            } else {
                player.sendMessage(ChatColor.RED + "Player not found.");
            }
        } else {
            return false;
        }
        return true;
    }
}
