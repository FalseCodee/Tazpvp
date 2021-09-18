package net.ntdi.tazpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if (sender instanceof Player){
            Player p = (Player) sender;

            p.getLocation().getWorld().setTime(18000);
            p.sendMessage(ChatColor.LIGHT_PURPLE + "It is now night");
        }

        return true;
    }

}

