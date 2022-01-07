package net.ntdi.tazpvp.commands.playercommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(sender instanceof  Player){
            player = (Player) sender;
        }
        if (!player.isOp()){
            player.sendMessage(ChatColor.DARK_GREEN + "You will be teleported to spawn in " + ChatColor.GREEN + "5 seconds" + ChatColor.DARK_GREEN + ", do not move.");
            player.teleport(new Location(Bukkit.getWorld("spawn"), 0.5, 50, 0.5, 180, 0));
        } else {

        }
        return true;
    }
}
