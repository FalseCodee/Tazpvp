package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("tazpvp.staff.listworld")) {
                for (String world : p.getServer().getWorlds().toString().split(", ")) {
                    p.sendMessage(world);
                }
            }
        }
        return true;
    }
}
