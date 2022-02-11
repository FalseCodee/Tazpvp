package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("tazpvp.tp")) {
                if (args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        p.teleport(target);
                        p.sendMessage(ChatColor.GREEN + "Teleported to " + target.getName());
                    } else if (Bukkit.getWorld(args[0]) != null) {
                        World w = Bukkit.getWorld(args[0]);
                        p.teleport(w.getSpawnLocation());
                    }
                }  else if (args.length == 2) {
                    if (Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[1]) != null) {
                        Player Header = Bukkit.getPlayer(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        Header.teleport(target);
                        p.sendMessage(ChatColor.GREEN + "Teleported " + Header.getName() + " to " + target.getName());
                    }
                } else if (args.length == 3) {
                    int x = Integer.parseInt(args[0]);
                    int y = Integer.parseInt(args[1]);
                    int z = Integer.parseInt(args[2]);
                    p.teleport(new Location(p.getWorld(), x, y, z));
                    p.sendMessage(ChatColor.GREEN + "Teleported you to " + x + " " + y + " " + z);
                } else if (args.length == 4) {
                    World w = Bukkit.getWorld(args[0]);
                    int x = Integer.parseInt(args[1]);
                    int y = Integer.parseInt(args[2]);
                    int z = Integer.parseInt(args[3]);
                    p.teleport(new Location(w, x, y, z));
                    p.sendMessage(ChatColor.GREEN + "Teleported you to " + w.getName() + " " + x + " " + y + " " + z);
                } else if (args.length == 5) {
                    Player target = Bukkit.getPlayer(args[0]);
                    World w = Bukkit.getWorld(args[1]);
                    int x = Integer.parseInt(args[2]);
                    int y = Integer.parseInt(args[3]);
                    int z = Integer.parseInt(args[4]);
                    target.teleport(new Location(w, x, y, z));
                    p.sendMessage(ChatColor.GREEN + "Teleported " + target.getName() + " to " + w.getName() + " " + x + " " + y + " " + z);
                }
            }
        }
        return true;
    }
}
