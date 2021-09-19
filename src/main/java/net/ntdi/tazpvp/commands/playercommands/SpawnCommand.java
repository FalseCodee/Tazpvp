package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.utils.Utils;
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
            //TODO: Test this method
            Utils.teleportPlayer(player, Utils.spawnLocation, 5*1000);
        }
        return false;
    }
}
