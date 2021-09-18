package net.ntdi.tazpvp.commands;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.managers.CurrencyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CurrencyCommand implements CommandExecutor {

    public TazPvP plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("op")) {
            if (args.length < 2) {
                return false;
            }
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);

            if (p == null) {
                return false;
            }
            if (args.length == 2) {
                CurrencyManager manager = new CurrencyManager(plugin);
                if (args[0].equalsIgnoreCase("get")) {
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Their balance is " + manager.getPlayerCurrency(p) + "$");
                }
            } else if (args.length == 3) {
                int amount = Integer.parseInt(args[2]);
                CurrencyManager manager = new CurrencyManager(plugin);

                if (args[0].equalsIgnoreCase("add")) {
                    manager.addCurrencyToPlayer(p, amount);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully added " + args[2] + "$ to " + p.getName() + "'s balance.");
                    int Afterbal = manager.getPlayerCurrency(p);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Their balance is now " + Afterbal + "$");
                } else if (args[0].equalsIgnoreCase("remove")) {
                    manager.removeCurrencyFromPlayer(p, amount);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully removed " + args[2] + "$ to " + p.getName() + "'s balance.");
                    int Afterbal = manager.getPlayerCurrency(p);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Their balance is now " + Afterbal + "$");
                } else if (args[0].equalsIgnoreCase("set")) {
                    manager.setCurrencyOfPlayer(p, amount);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully set " + p.getName() + "'s balance to " + args[2] + "$");
                    int Afterbal = manager.getPlayerCurrency(p);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Their balance is now " + Afterbal + "$");
                }
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
        }
        return true;

    }
}
