package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class AltsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("tazpvp.ban")) {
                if (args.length == 1) {
                    String name = args[0];
                    Player target = Bukkit.getPlayer(name);
                    UUID uuidd = null;
                    if (target != null) {
                        List<UUID> matches = TazPvP.ipmanager.findMatchingIp(target);
                        if (matches != null) {
                            p.sendMessage(ChatColor.GOLD + target.getName() + "Has been seen on: ");
                            for (UUID uuid : matches) {
                                p.sendMessage(ChatColor.YELLOW + Bukkit.getOfflinePlayer(uuid).getName());
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "No matches found");
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
