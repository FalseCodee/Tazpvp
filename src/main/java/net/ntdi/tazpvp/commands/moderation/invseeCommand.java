package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class invseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(player != null && player.hasPermission("tazpvp.invsee")){
            if(args.length == 1) {
                Inventory gui = Bukkit.createInventory(player, 27, "INVSEE");
                Player target = Bukkit.getPlayer(args[0]);
                gui.setContents(target.getInventory().getContents());
                player.openInventory(gui);
            }
        }
        return false;
    }
}
