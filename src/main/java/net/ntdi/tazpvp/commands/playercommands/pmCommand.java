package net.ntdi.tazpvp.commands.playercommands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pmCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 2) {
                Player target = p.getServer().getPlayer(args[0]);
                target.sendMessage( ChatColor.GOLD + "From " + ChatColor.RED + p + ": "  + ChatColor.GRAY + (args[1]));
                p.sendMessage(ChatColor.GOLD + "To " + ChatColor.RED + (args[0]) + ": " + ChatColor.GRAY + (args[1]));
                target.playSound(target.getLocation(), Sound.CLICK, 1, 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
