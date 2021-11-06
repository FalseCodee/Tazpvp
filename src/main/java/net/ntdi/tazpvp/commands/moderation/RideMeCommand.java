package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RideMeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            return false;
        } else if (args.length == 1) {
            Player p = (Player) sender;
            Player target = p.getServer().getPlayer(args[0]);

            if (target == null) {
                return false;
            } else{
                p.setPassenger(target);
            }
        } else if (args.length >= 2) {
            return false;
        } else {
            return false;
        }

        return true;
    }
}
