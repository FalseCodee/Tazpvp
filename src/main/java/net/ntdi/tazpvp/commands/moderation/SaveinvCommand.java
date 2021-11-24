package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.managers.ArmorManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveinvCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("op")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ArmorManager.storeAndClearInventory(player);
                player.sendMessage(ChatColor.GREEN + "Your inventory has been saved.");

            }
        }
        return true;
    }
}
