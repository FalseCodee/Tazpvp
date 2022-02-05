package net.ntdi.tazpvp.commands.playercommands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.getItemInHand() != null) {
                p.getInventory().setHelmet(p.getItemInHand());
                p.getItemInHand().setType(Material.AIR);
                p.sendMessage(ChatColor.GREEN + "You have equipped your item in your hand as your hat.");
            }
        }
        return true;
    }
}
