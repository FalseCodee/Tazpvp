package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class EnchantCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(player != null && player.hasPermission("staff.enchant") && args.length > 0 && args.length < 3) {
            //args[]0
            Enchantment ench = Enchantment.getByName(args[0].toUpperCase());
            if (ench != null) {
                PlayerInventory inv = player.getInventory();
                if (inv.getItemInHand() != null) {

                    try{
                        Integer.parseInt(args[1]);
                        inv.getItemInHand().addUnsafeEnchantment(ench, Integer.parseInt(args[1]));

                    }catch(NumberFormatException e){

                        player.sendMessage(ChatColor.RED + "Your second arg should be a integer");
                        return false;
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "Hold something other than air!");
                    return false;
                }
            } else {
                player.sendMessage(ChatColor.RED + "Enchant not found!");
                return false;
            }

        }
        return true;
    }

}
