package net.ntdi.tazpvp.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("tazpvp.kit")) {
                if (strings.length == 0) {
                    kit(player);
                    player.sendMessage(ChatColor.GREEN + "You have been given a kit!");
                } if (strings.length >= 1) {
                    if (Bukkit.getPlayer(strings[0]) != null) {
                        Player target = Bukkit.getPlayer(strings[0]);
                        kit(target);
                        player.sendMessage(ChatColor.GREEN + "You have given " + target.getName() + " a kit!");
                        target.sendMessage(ChatColor.GREEN + "You have been given a kit by " + player.getName());
                    }
                }
            }
        }
        return true;
    }

    public static void kit(Player player) {
        player.getInventory().clear();

        ItemStack armor1 = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack armor2 = new ItemStack(Material.LEATHER_HELMET);
        armor2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        armor2.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        ItemStack armor3 = new ItemStack(Material.LEATHER_CHESTPLATE);
        armor3.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        armor3.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        ItemStack armor4 = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        ItemStack pickaxe = new ItemStack(Material.WOOD_PICKAXE);
        ItemStack fishingrod = new ItemStack(Material.FISHING_ROD);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 8);
        ItemStack blocks = new ItemStack(Material.WOOD, 16);
        ItemStack arrow = new ItemStack(Material.ARROW, 10);

        ItemMeta meta1 = armor1.getItemMeta();
        meta1.spigot().setUnbreakable(false);
        armor1.setItemMeta(meta1);

        ItemMeta meta2 = armor2.getItemMeta();
        meta2.spigot().setUnbreakable(false);
        armor2.setItemMeta(meta2);

        ItemMeta meta3 = armor3.getItemMeta();
        meta3.spigot().setUnbreakable(false);
        armor3.setItemMeta(meta3);

        ItemMeta meta4 = armor4.getItemMeta();
        meta4.spigot().setUnbreakable(false);
        armor4.setItemMeta(meta4);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.spigot().setUnbreakable(false);
        sword.setItemMeta(swordMeta);

        ItemMeta pickaxeMeta = pickaxe.getItemMeta();
        pickaxeMeta.spigot().setUnbreakable(false);
        pickaxe.setItemMeta(pickaxeMeta);

        ItemMeta fishingMeta = fishingrod.getItemMeta();
        fishingMeta.spigot().setUnbreakable(false);
        fishingrod.setItemMeta(fishingMeta);

        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.spigot().setUnbreakable(false);
        bow.setItemMeta(bowMeta);


        PlayerInventory inv = player.getInventory();
        inv.setHelmet(armor2);
        inv.setChestplate(armor3);
        inv.setLeggings(armor4);
        inv.setBoots(armor1);
        inv.addItem(sword);
        inv.addItem(fishingrod);
        inv.addItem(bow);
        inv.addItem(pickaxe);
        inv.addItem(steak);
        inv.addItem(blocks);
        inv.setItem(9, arrow);
    }
}
