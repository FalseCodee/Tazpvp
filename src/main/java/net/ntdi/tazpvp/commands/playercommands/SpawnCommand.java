package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }

        if(player != null){
            if (!player.isOp()){
                player.sendMessage(ChatColor.DARK_GREEN + "You will be teleported to spawn in " + ChatColor.GREEN + "5 seconds" + ChatColor.DARK_GREEN + ", do not move.");
                Utils.teleportPlayer(player, Utils.spawnLocation, 5*1000);
            } else {
                Utils.teleportPlayer(player, Utils.spawnLocation, 5);
            }
        }
        return true;
    }
}
