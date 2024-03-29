package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.gui.guis.GUIShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopGuiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if(sender instanceof  Player){
            player = (Player) sender;
        }
        if (player != null && player.hasPermission("op")){
            new GUIShop(player);
        }

        return true;
    }
}
