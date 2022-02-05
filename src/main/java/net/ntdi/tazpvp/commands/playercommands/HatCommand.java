package net.ntdi.tazpvp.commands.playercommands;

import org.bukkit.ChatColor;
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
                p.getItemInHand().setAmount(0);
                p.getItemInHand().setType(null);
                p.getInventory().setHeldItemSlot(0);
                p.getInventory().getItemInHand().setType(null);
                p.sendMessage(ChatColor.GREEN + "You have equipped your item in your hand as your hat.");
            }
        }
        return true;
    }
}
