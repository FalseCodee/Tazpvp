package net.ntdi.tazpvp.commands.functions;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BountyCommand implements CommandExecutor {
    public static final HashMap<UUID, Integer> bounties = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && args.length > 2) {
            if(Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                if(args[1].equalsIgnoreCase("add")) {
                    try {
                        int bounty = Integer.parseInt(args[2]);
                        if(TazPvP.statsManager.getMoney(player) < bounty) {
                            player.sendMessage(ChatColor.RED + "Insufficient funds.");
                            return true;
                        }
                        TazPvP.statsManager.addMoney(player, -bounty);
                        if(bounties.get(target.getUniqueId()) != null) {
                            bounties.put(target.getUniqueId(), bounties.get(target.getUniqueId()) + bounty);
                        } else {
                            bounties.put(target.getUniqueId(), bounty);
                        }
                        Bukkit.broadcastMessage(player.getDisplayName() + ChatColor.YELLOW + " has set a bounty on " + ChatColor.WHITE + target.getDisplayName() + ChatColor.YELLOW + " for " + ChatColor.WHITE + "$" + bounty);
                    } catch (NumberFormatException exception) {
                        return false;
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Player not found.");
            }
        } else {
            return false;
        }
        return true;
    }
}
