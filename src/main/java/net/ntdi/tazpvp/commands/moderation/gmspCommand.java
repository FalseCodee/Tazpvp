package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gmspCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (sender instanceof Player) {
            p = (Player) sender;
        }
        if (p != null && p.hasPermission("tazpvp.staff.gmsp")) {
            if(args.length > 0) {
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(ChatColor.GOLD + "Gamemode: " + ChatColor.RED + "spectator");
            } else if (args.length == 1) {
                if (p.hasPermission("tazpvp.gm.others")) {
                    Player targetP = Bukkit.getServer().getPlayer(args[0]);
                    targetP.setGameMode(GameMode.SPECTATOR);
                    targetP.sendMessage(ChatColor.GOLD + "Gamemode: " + ChatColor.RED + "spectator");
                }
            }
        }
        return true;
    }
}
