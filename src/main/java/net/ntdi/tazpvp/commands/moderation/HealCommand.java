package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("tazpvp.staff.heal")) {
                player.setHealth(player.getMaxHealth());
                player.sendMessage("heeled");
            }
        }

        return true;
    }
}
