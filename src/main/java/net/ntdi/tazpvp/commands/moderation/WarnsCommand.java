package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WarnsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }
        if(player != null && player.hasPermission("staff.warns") && args.length > 0) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null) {
                if(TazPvP.punishmentManager.hasWarns(target)) {
                    List<String> warns = TazPvP.punishmentManager.getWarns(target);
                    player.sendMessage(ChatColor.BLUE + target.getName() + " has " + ChatColor.WHITE + warns.size() + ChatColor.BLUE + " warns.");
                    for(int i = 0; i < warns.size(); i++) {
                        player.sendMessage(ChatColor.DARK_AQUA + "" + (i+1) + ": " + ChatColor.WHITE + warns.get(i));
                    }
                } else {
                    player.sendMessage(ChatColor.RED + target.getName() + " does not have any warns.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Player not found.");
            }

        }
        return true;
    }
}
