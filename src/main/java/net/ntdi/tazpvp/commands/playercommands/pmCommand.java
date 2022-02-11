package net.ntdi.tazpvp.commands.playercommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pmCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 3) {
                Player target = p.getServer().getPlayer(args[0]);
                target.sendMessage((args[0]) + "sent you: " + (args[1]));
            }
        }
        return false;
    }
}
