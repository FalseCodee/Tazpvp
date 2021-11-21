package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giveItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.isOp()) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("hammer")) {
                    ItemManager.givePlayerItem(p, Items.HAMMER, 1);
                }
            }
        }

        return true;
    }
}
