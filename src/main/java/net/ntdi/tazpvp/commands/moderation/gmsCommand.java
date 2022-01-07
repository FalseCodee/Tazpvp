package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gmsCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (sender instanceof Player) {
            p = (Player) sender;
        }
        if (p.hasPermission("tazpvp.staff.gm")) {
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(ChatColor.GOLD + "Gamemode: " + ChatColor.RED + "survival");
        }
        return true;
    }
}