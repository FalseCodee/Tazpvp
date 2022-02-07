package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 2) {
                Player target = p.getServer().getPlayer(args[0]);
                if (target != null) {
                    int amount = Integer.parseInt(args[1]);
                    if (amount > 0) {
                        if (TazPvP.statsManager.getMoney(p) >= amount) {
                            TazPvP.statsManager.addMoney(p, -amount);
                            p.sendMessage(ChatColor.GREEN + "You have paid " + target.getName() + " " + ChatColor.GOLD + amount + "$.");
                            TazPvP.statsManager.addMoney(target, amount);
                            target.sendMessage(ChatColor.GREEN + "You have received " + ChatColor.GOLD + amount + "$" + ChatColor.GREEN + " from " + p.getName() + ".");
                        } else {
                            p.sendMessage(ChatColor.RED + "You don't have enough money.");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "You can't pay negative amounts.");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Player not found.");
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
