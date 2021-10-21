package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ExpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage("" + TazPvP.statsManager.getExp((OfflinePlayer) sender));
        sender.sendMessage("" + TazPvP.statsManager.getExpLeft((OfflinePlayer) sender));

        return true;
    }
}
