package net.ntdi.tazpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class StarterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            Player target = Bukkit.getPlayer(args[0]);
            if(args.length == 1){
                if(target != null) {
                    // Argument is an online player
                    ItemStack armor1 = new ItemStack(Material.LEATHER_BOOTS);
                    ItemStack armor2 = new ItemStack(Material.LEATHER_HELMET);
                    ItemStack armor3 = new ItemStack(Material.LEATHER_CHESTPLATE);
                    ItemStack armor4 = new ItemStack(Material.LEATHER_LEGGINGS);
                    ItemStack sword = new ItemStack(Material.WOOD_SWORD);
                    ItemStack pickaxe = new ItemStack(Material.WOOD_PICKAXE);
                    ItemStack fishingrod = new ItemStack(Material.FISHING_ROD);
                    ItemStack bow = new ItemStack(Material.BOW);
                    ItemStack steak = new ItemStack(Material.COOKED_BEEF, 10);
                    ItemStack arrow = new ItemStack(Material.ARROW, 15);

                    ItemMeta meta1 = armor1.getItemMeta();
                    meta1.spigot().setUnbreakable(true);
                    armor1.setItemMeta(meta1);

                    ItemMeta meta2 = armor2.getItemMeta();
                    meta2.spigot().setUnbreakable(true);
                    armor2.setItemMeta(meta2);

                    ItemMeta meta3 = armor3.getItemMeta();
                    meta3.spigot().setUnbreakable(true);
                    armor3.setItemMeta(meta3);

                    ItemMeta meta4= armor4.getItemMeta();
                    meta4.spigot().setUnbreakable(true);
                    armor4.setItemMeta(meta4);

                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.spigot().setUnbreakable(true);
                    sword.setItemMeta(swordMeta);

                    ItemMeta pickaxeMeta = sword.getItemMeta();
                    pickaxeMeta.spigot().setUnbreakable(true);
                    pickaxe.setItemMeta(pickaxeMeta);

                    ItemMeta fishingMeta = fishingrod.getItemMeta();
                    fishingMeta.spigot().setUnbreakable(true);
                    fishingrod.setItemMeta(fishingMeta);

                    ItemMeta bowMeta = bow.getItemMeta();
                    bowMeta.spigot().setUnbreakable(true);
                    bow.setItemMeta(bowMeta);


                    PlayerInventory inv = target.getInventory();
                    inv.setHelmet(armor2);
                    inv.setChestplate(armor3);
                    inv.setLeggings(armor4);
                    inv.setBoots(armor1);
                    inv.addItem(sword);
                    inv.addItem(fishingrod);
                    inv.addItem(bow);
                    inv.addItem(pickaxe);
                    inv.addItem(steak);
                    inv.addItem(arrow);
                } else {
                    ItemStack armor1 = new ItemStack(Material.LEATHER_BOOTS);
                    ItemStack armor2 = new ItemStack(Material.LEATHER_HELMET);
                    ItemStack armor3 = new ItemStack(Material.LEATHER_CHESTPLATE);
                    ItemStack armor4 = new ItemStack(Material.LEATHER_LEGGINGS);
                    ItemStack sword = new ItemStack(Material.WOOD_SWORD);
                    ItemStack pickaxe = new ItemStack(Material.WOOD_PICKAXE);
                    ItemStack fishingrod = new ItemStack(Material.FISHING_ROD);
                    ItemStack bow = new ItemStack(Material.BOW);
                    ItemStack steak = new ItemStack(Material.COOKED_BEEF, 10);
                    ItemStack arrow = new ItemStack(Material.ARROW, 15);

                    ItemMeta meta1 = armor1.getItemMeta();
                    meta1.spigot().setUnbreakable(true);
                    armor1.setItemMeta(meta1);

                    ItemMeta meta2 = armor2.getItemMeta();
                    meta2.spigot().setUnbreakable(true);
                    armor2.setItemMeta(meta2);

                    ItemMeta meta3 = armor3.getItemMeta();
                    meta3.spigot().setUnbreakable(true);
                    armor3.setItemMeta(meta3);

                    ItemMeta meta4= armor4.getItemMeta();
                    meta4.spigot().setUnbreakable(true);
                    armor4.setItemMeta(meta4);

                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.spigot().setUnbreakable(true);
                    sword.setItemMeta(swordMeta);

                    ItemMeta pickaxeMeta = sword.getItemMeta();
                    pickaxeMeta.spigot().setUnbreakable(true);
                    pickaxe.setItemMeta(pickaxeMeta);

                    ItemMeta fishingMeta = fishingrod.getItemMeta();
                    fishingMeta.spigot().setUnbreakable(true);
                    fishingrod.setItemMeta(fishingMeta);

                    ItemMeta bowMeta = bow.getItemMeta();
                    bowMeta.spigot().setUnbreakable(true);
                    bow.setItemMeta(bowMeta);


                    PlayerInventory inv = p.getInventory();
                    inv.setHelmet(armor2);
                    inv.setChestplate(armor3);
                    inv.setLeggings(armor4);
                    inv.setBoots(armor1);
                    inv.addItem(sword);
                    inv.addItem(fishingrod);
                    inv.addItem(bow);
                    inv.addItem(pickaxe);
                    inv.addItem(steak);
                    inv.addItem(arrow);
                }

            }else{
                ItemStack armor1 = new ItemStack(Material.LEATHER_BOOTS);
                ItemStack armor2 = new ItemStack(Material.LEATHER_HELMET);
                ItemStack armor3 = new ItemStack(Material.LEATHER_CHESTPLATE);
                ItemStack armor4 = new ItemStack(Material.LEATHER_LEGGINGS);
                ItemStack sword = new ItemStack(Material.WOOD_SWORD);
                ItemStack pickaxe = new ItemStack(Material.WOOD_PICKAXE);
                ItemStack fishingrod = new ItemStack(Material.FISHING_ROD);
                ItemStack bow = new ItemStack(Material.BOW);
                ItemStack steak = new ItemStack(Material.COOKED_BEEF, 10);
                ItemStack arrow = new ItemStack(Material.ARROW, 15);

                ItemMeta meta1 = armor1.getItemMeta();
                meta1.spigot().setUnbreakable(true);
                armor1.setItemMeta(meta1);

                ItemMeta meta2 = armor2.getItemMeta();
                meta2.spigot().setUnbreakable(true);
                armor2.setItemMeta(meta2);

                ItemMeta meta3 = armor3.getItemMeta();
                meta3.spigot().setUnbreakable(true);
                armor3.setItemMeta(meta3);

                ItemMeta meta4= armor4.getItemMeta();
                meta4.spigot().setUnbreakable(true);
                armor4.setItemMeta(meta4);

                ItemMeta swordMeta = sword.getItemMeta();
                swordMeta.spigot().setUnbreakable(true);
                sword.setItemMeta(swordMeta);

                ItemMeta pickaxeMeta = sword.getItemMeta();
                pickaxeMeta.spigot().setUnbreakable(true);
                pickaxe.setItemMeta(pickaxeMeta);

                ItemMeta fishingMeta = fishingrod.getItemMeta();
                fishingMeta.spigot().setUnbreakable(true);
                fishingrod.setItemMeta(fishingMeta);

                ItemMeta bowMeta = bow.getItemMeta();
                bowMeta.spigot().setUnbreakable(true);
                bow.setItemMeta(bowMeta);


                PlayerInventory inv = p.getInventory();
                inv.setHelmet(armor2);
                inv.setChestplate(armor3);
                inv.setLeggings(armor4);
                inv.setBoots(armor1);
                inv.addItem(sword);
                inv.addItem(fishingrod);
                inv.addItem(bow);
                inv.addItem(pickaxe);
                inv.addItem(steak);
                inv.addItem(arrow);
            }


        }else{
            System.out.println("Only players can execute /sendword");
        }
        return true;
    }
}
