package net.ntdi.tazpvp.commands.playercommands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            Material item = p.getItemInHand().getType();
            if (item == Material.WOOD_SWORD || item == Material.STONE_SWORD || item == Material.IRON_SWORD || item == Material.GOLD_SWORD || item == Material.DIAMOND_SWORD
                || item == Material.WOOD_PICKAXE || item == Material.STONE_PICKAXE || item == Material.IRON_PICKAXE || item == Material.GOLD_PICKAXE || item == Material.DIAMOND_PICKAXE
                || item == Material.LEATHER_BOOTS || item == Material.LEATHER_CHESTPLATE || item == Material.LEATHER_HELMET || item == Material.LEATHER_LEGGINGS
                || item == Material.CHAINMAIL_BOOTS || item == Material.CHAINMAIL_CHESTPLATE || item == Material.CHAINMAIL_HELMET || item == Material.CHAINMAIL_LEGGINGS
                || item == Material.IRON_BOOTS || item == Material.IRON_CHESTPLATE || item == Material.IRON_HELMET || item == Material.IRON_LEGGINGS
                || item == Material.GOLD_BOOTS || item == Material.GOLD_CHESTPLATE || item == Material.GOLD_HELMET || item == Material.GOLD_LEGGINGS
                || item == Material.DIAMOND_BOOTS || item == Material.DIAMOND_CHESTPLATE || item == Material.DIAMOND_HELMET || item == Material.DIAMOND_LEGGINGS
                || item == Material.BOW || item == Material.FISHING_ROD) {
                p.sendMessage(ChatColor.RED + "You can't trash this item!");
            } else {


                if (args.length == 0) {
                    p.getItemInHand().setType(Material.AIR);
                    p.sendMessage(ChatColor.GREEN + "You have trashed " + item.toString().toLowerCase() + "!");
                } else {
                    try{
                        int amt = Integer.parseInt(args[0]);
                        p.getItemInHand().setAmount(p.getItemInHand().getAmount()- amt);
                        p.sendMessage(ChatColor.GREEN + "You have trashed " + amt + " " + item.toString().toLowerCase() + "!");

                    }catch(NumberFormatException e){
                        p.sendMessage(ChatColor.RED + "You can only use numbers as arguments!");
                    }
                }

            }
        }

        return true;
    }
}
