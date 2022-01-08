package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender.hasPermission("tazpvp.staff.tpall")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                for (Player p : player.getServer().getOnlinePlayers()) {
                    if (p != player) {
                        p.teleport(player.getLocation());
                    }
                }
            }
        }

        return true;
    }
}
