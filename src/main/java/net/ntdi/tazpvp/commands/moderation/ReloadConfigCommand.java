package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender.hasPermission("tazpvp.reloadconfig")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;

                TazPvP.getInstance().reloadConfig();
                p.sendMessage(ChatColor.GREEN + "Config reloaded!");
            }
        }

        return true;
    }
}
