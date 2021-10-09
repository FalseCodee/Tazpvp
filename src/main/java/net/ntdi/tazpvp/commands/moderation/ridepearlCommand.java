package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.ntdi.tazpvp.items.*;

public class ridepearlCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if (p.hasPermission("op")) {
            ItemManager.givePlayerItem(p, Items.RIDEPEARL, 1);
        }

        return true;
    }
}
