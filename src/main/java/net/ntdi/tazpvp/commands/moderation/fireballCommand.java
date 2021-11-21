package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fireballCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if (p.hasPermission("op")) {
            ItemManager.givePlayerItem(p, Items.FIREBALL, 16);
        }




        return true;
    }
}
