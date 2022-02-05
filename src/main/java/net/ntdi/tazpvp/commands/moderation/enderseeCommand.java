package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class enderseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("tazpvp.invsee")){
            if(args.length == 1) {
                Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
                Inventory targetInv = targetPlayer.getEnderChest();
                player.openInventory(targetInv);
            }
        }
        return false;
    }
}
