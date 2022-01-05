package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("map")) {
                    if (player.hasPermission("tazpvp.spectate")) {
                        if (player.getGameMode() != GameMode.SPECTATOR) {
                            player.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(ChatColor.GOLD + "You are now spectating the map. Type the command to go back.");
                            player.teleport(new Location(Bukkit.getWorld("arena"), 0.5, 30, 0.5));
                            TazPvP.Spectating.add(player);
                        } else if (player.getGameMode() == GameMode.SPECTATOR) {
                            TazPvP.Spectating.remove(player);
                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(ChatColor.GOLD + "You have stopped spectating");
                            player.teleport(new Location(Bukkit.getWorld("arena"), 0.5, 50, 0.5, 0, -180));
                        }
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
