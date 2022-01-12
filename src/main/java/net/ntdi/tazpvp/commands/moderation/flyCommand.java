package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
        }
        if(p != null && p.hasPermission("tazpvp.staff.fly")) {
            if (args.length == 0) {
                if(p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.sendMessage(ChatColor.GOLD + "Flying: " + ChatColor.RED + "disabled");
                } else {
                    p.setAllowFlight(true);
                    p.sendMessage(ChatColor.GOLD + "Flying: " + ChatColor.RED + "enabled");
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
