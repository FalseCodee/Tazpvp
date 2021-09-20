package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class EnchantCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(player != null && player.hasPermission("staff.enchant") && args.length > 0 && args.length < 3) {
            //args[]0

            if (Enchantment.getByName(args[0]) != null) {
                PlayerInventory inv = player.getInventory();
                if (inv.getItemInHand() != null) {
                    ItemStack heldItem = new ItemStack(inv.getItemInHand());

                    try{
                        Integer.parseInt(args[1]);

                    }catch(NumberFormatException e){

                        player.sendMessage(ChatColor.RED + "Your second arg should be a integer");
                        return false;
                    }

                    heldItem.addUnsafeEnchantment(Enchantment.getByName(args[0]), Integer.parseInt(args[1]));

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
