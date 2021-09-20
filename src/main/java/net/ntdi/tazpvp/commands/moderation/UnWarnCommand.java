package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnWarnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("staff.unwarn") && args.length > 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null) {
                if(TazPvP.punishmentManager.hasWarns(target)){
                    if(args[1].equalsIgnoreCase("all")) {
                        player.sendMessage(ChatColor.BLUE + "Removed all warns from " + ChatColor.WHITE + target.getName());
                        TazPvP.punishmentManager.removeAllWarns(target);
                        target.sendMessage(ChatColor.DARK_AQUA + "All of your warns have been removed!");
                    } else {
                        try {
                            int index = Integer.parseInt(args[1])-1;
                            if(index >= TazPvP.punishmentManager.getWarns(target).size()){
                                player.sendMessage(ChatColor.RED + "That index is outside the size of the list.");
                                return true;
                            }
                            TazPvP.punishmentManager.removeWarn(target, index);
                            player.sendMessage(ChatColor.BLUE + "Removed 1 warn from " + ChatColor.WHITE + target.getName());
                            target.sendMessage(ChatColor.DARK_AQUA + "One of your warns has been removed!");
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.RED + target.getName() + " has no warns.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Player not found.");
            }
        }
        return true;
    }
}
