package net.ntdi.tazpvp.managers;

import net.milkbowl.vault.chat.Chat;
import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DuelManager {

    public static boolean map1 = false;
    public static Location map1one = new Location(Bukkit.getWorld("duel"), -24.5, 30, -200.5);
    public static Location map1two = new Location(Bukkit.getWorld("duel"), 24.5, 30, 200.5);

    public static boolean map2 = false;
    public static Location map2one = new Location(Bukkit.getWorld("duel"), -24.5, 30, -100.5);
    public static Location map2two = new Location(Bukkit.getWorld("duel"), 24.5, 30, -100.5);

    public static boolean map3 = false;
    public static Location map3one = new Location(Bukkit.getWorld("duel"), -24.5, 30, 0.5);
    public static Location map3two = new Location(Bukkit.getWorld("duel"), 24.5, 30, 0.5);

    public static boolean map4 = false;
    public static Location map4one = new Location(Bukkit.getWorld("duel"), -24.5, 30, 100.5);
    public static Location map4two = new Location(Bukkit.getWorld("duel"), 24.5, 30, 100.5);
    // Create Array List
    public static ArrayList<String> availableMaps = new ArrayList<String>();

    public static void addMap(String map){
        // adds to arraylist
        availableMaps.add(map);
    }

    public static void removeMap(String map){
        // removes from arraylist
        availableMaps.remove(map);
    }

    public static void initMaps(){
        //Initializes all maps
        availableMaps.add("map1");
        availableMaps.add("map2");
        availableMaps.add("map3");
        availableMaps.add("map4");
    }

    public static void startDuel(Player player1, Player player2) {
        // checks if there is a map
        if (availableMaps.isEmpty()) {
            player1.sendMessage(ChatColor.RED + "There are no maps available! Try again later.");
            player2.sendMessage(ChatColor.RED + "There are no maps available! Try again later.");
        // runs if there is availbe map
        } else {
            Random randomGenerator = new Random();
            // Generates new random number
            int index = randomGenerator.nextInt(availableMaps.size());
            String mapName = availableMaps.get(index); //gets the map correlcated to the number
            if (mapName.equals("map1")) {
                map1 = true;
                removeMap("map1");
                player1.setMetadata("map", new FixedMetadataValue(TazPvP.getInstance(), "map1"));
                player2.setMetadata("map", new FixedMetadataValue(TazPvP.getInstance(), "map1"));
                player1.setMetadata("dueling", new FixedMetadataValue(TazPvP.getInstance(), true));
                player2.setMetadata("dueling", new FixedMetadataValue(TazPvP.getInstance(), true));
                player1.setMetadata("opponent", new FixedMetadataValue(TazPvP.getInstance(), player2.getName()));
                player2.setMetadata("opponent", new FixedMetadataValue(TazPvP.getInstance(), player1.getName()));

                ArmorManager.storeAndClearInventory(player1);
                ArmorManager.storeAndClearInventory(player2);
                System.out.println("Stored inventory of " + player1.getName());
                System.out.println("Stored inventory of " + player2.getName());

                new DuelManager().equipKit(player1);
                new DuelManager().equipKit(player2);


                player1.teleport(map1one);
                player2.teleport(map1two);

            } else if (mapName.equals("map2")) {
                map2 = true;
                removeMap("map2");

            } else if (mapName.equals("map3")) {
                map3 = true;
                removeMap("map3");

            } else if (mapName.equals("map4")) {
                map4 = true;
                removeMap("map4");
            }
        }
    }
    public static void endDuel(Player player1, Player player2) {

    }
    public static void cancelDuel(Player player1, Player player2) {

    }
    public static void acceptDuel(Player player1, Player player2) {

    }
    public static void declineDuel(Player player1, Player player2) {

    }

    public void equipKit(Player player) {
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        ItemStack fishingrod = new ItemStack(Material.FISHING_ROD);
        ItemStack bow = new ItemStack(Material.BOW);
        sword.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 5);
        ItemStack butter = new ItemStack(Material.GOLD_INGOT, 3);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 10);
        ItemStack arrow = new ItemStack(Material.ARROW);


        PlayerInventory inv = player.getInventory();
        inv.setHelmet(helmet);
        inv.setChestplate(chestplate);
        inv.setLeggings(leggings);
        inv.setBoots(boots);
        inv.addItem(sword);
        inv.addItem(fishingrod);
        inv.addItem(bow);
        inv.addItem(gapple);
        inv.addItem(butter);
        inv.addItem(steak);
        inv.setItem(32, arrow);
    }

    public boolean isDueling(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("dueling");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }

    public String whatMap(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("map");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asString();
        }
        return "";
    }

    public String getOpponent(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("opponent");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asString();
        }
        return "";
    }
}
