package net.ntdi.tazpvp.managers;

import net.milkbowl.vault.chat.Chat;
import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
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

    // rownox make kit here
    public void equipKit(Player player) {

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
