package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.ChatColor;
import org.bukkit.World;
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
                String[] worldNames = new String[p.getServer().getWorlds().size()];
                int i = 0;
                for (World w : p.getServer().getWorlds()) {
                    worldNames[i] = w.getName();
                    i++;
                }
                p.sendMessage(ChatColor.GOLD + "Worlds:");
                for (String s : worldNames) {
                    p.sendMessage(ChatColor.YELLOW + s);
                }
            }
        }
        return true;
    }
}
