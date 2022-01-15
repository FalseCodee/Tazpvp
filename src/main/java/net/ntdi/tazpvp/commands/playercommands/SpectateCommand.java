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

import static net.ntdi.tazpvp.listeners.passive.combatLog.combatLog;

public class SpectateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (TazPvP.duelManager.isDueling(target)) {
                        if (!TazPvP.duelManager.isDueling(player)) {
                            if (player != target) {
                                if (!combatLog.containsKey(player)) {

                                } else {
                                    player.sendMessage(ChatColor.RED + "You cannot spectate a player while in combat.");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "You cannot spectate yourself!");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You are already in a duel!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "That player is not dueling!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
