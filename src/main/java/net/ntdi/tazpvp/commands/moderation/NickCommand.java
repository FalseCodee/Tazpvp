package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class NickCommand implements CommandExecutor {

    public static final HashMap<Player, String> oldNames = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission("tazpvp.nick")) {
                if (strings.length == 1) {
                    String nick = strings[0];
                    if (nick.length() < 16) {
                        p.setDisplayName(nick);
                        p.setPlayerListName(nick);
                        p.sendMessage(ChatColor.GOLD + "Your nickname has been set to " + ChatColor.YELLOW + nick);
                    } else {
                        p.sendMessage(ChatColor.RED + "Your nickname is too long!");
                    }
                } else if (strings.length == 2) {
                    Player target = p.getServer().getPlayer(strings[0]);
                    String nick = strings[1];
                    if (target != null) {
                        if (nick.length() < 16) {
                            target.setDisplayName(nick);
                            target.setPlayerListName(nick);
                            target.sendMessage(ChatColor.GOLD + "Your nickname has been set to " + ChatColor.YELLOW + nick);
                            p.sendMessage(ChatColor.GOLD + "You have set " + ChatColor.YELLOW + target.getName() + ChatColor.GOLD + "'s nickname to " + ChatColor.YELLOW + nick);
                        } else {
                            p.sendMessage(ChatColor.RED + "Your nickname is too long!");
                        }
                    }
                } else if (strings.length == 0) {
                    p.sendMessage("nick removd");
                    p.setDisplayName(p.getName());
                }
            }
        }

        return true;
    }
}
