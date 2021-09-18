package net.ntdi.tazpvp.commands;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.managers.CurrencyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CurrenyCommand implements CommandExecutor {

    public TazPvP plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("op")) {
            if (args.length == 0) {
                return false;
            }
            if (args.length == 1) {
                return false;
            }
            if (args.length == 2) {
                OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
                CurrencyManager manager = new CurrencyManager(plugin);
                if (args[0].equalsIgnoreCase("get")) {
                    if (p != null) {
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Their balance is " + manager.getPlayerCurrency(p) + "$");
                    } else {
                        return false;
                    }
                }
            } else if (args.length == 3) {
                OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
                int amount = Integer.parseInt(args[2]);
                CurrencyManager manager = new CurrencyManager(plugin);

                if (args[0].equalsIgnoreCase("add")) {
                    if (p != null) {
                        manager.addCurrencyToPlayer(p, amount);
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully added " + args[2] + "$ to " + p.getName() + "'s balance.");
                        int Afterbal = manager.getPlayerCurrency(p);
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Their balance is now " + Afterbal + "$");
                    } else {
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (p != null) {
                        manager.removeCurrencyFromPlayer(p, amount);
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully removed " + args[2] + "$ to " + p.getName() + "'s balance.");
                        int Afterbal = manager.getPlayerCurrency(p);
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Their balance is now " + Afterbal + "$");
                    } else {
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (p != null) {
                        manager.setCurrencyOfPlayer(p, amount);
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully set " + p.getName() + "'s balance to " + args[2] + "$");
                        int Afterbal = manager.getPlayerCurrency(p);
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Their balance is now " + Afterbal + "$");
                    } else {
                        return false;
                    }
                    return true;
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
        }

        return true;
    }
}
