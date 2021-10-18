package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FakeOpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Bukkit.broadcastMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[" + sender.getName() + ": Opped " + args[0] + "]");

        return true;
    }
}
