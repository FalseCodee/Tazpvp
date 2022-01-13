package net.ntdi.tazpvp.managers.Duels;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.managers.ArmorManager;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.stream.Collectors;

public class DuelManager {

    public ArrayList<DuelMap> totalMaps = new ArrayList<>();
    public ArrayList<DuelMap> availableMaps = new ArrayList<>();

    public DuelManager() {
        availableMaps.add(new DuelMap("map1",
                new Location(Bukkit.getWorld("duel"), -24.5, 30, -200.5),
                new Location(Bukkit.getWorld("duel"), 24.5, 30, 200.5)));
        availableMaps.add(new DuelMap("map2",
                new Location(Bukkit.getWorld("duel"), -24.5, 30, -100.5),
                new Location(Bukkit.getWorld("duel"), 24.5, 30, -100.5)));
        availableMaps.add(new DuelMap("map3",
                new Location(Bukkit.getWorld("duel"), -24.5, 30, 0.5),
                new Location(Bukkit.getWorld("duel"), 24.5, 30, 0.5)));
        availableMaps.add(new DuelMap("map4",
                new Location(Bukkit.getWorld("duel"), -24.5, 30, 100.5),
                new Location(Bukkit.getWorld("duel"), 24.5, 30, 100.5)));

        totalMaps = new ArrayList<>(availableMaps);
    }

    public void addMap(String map){
        // adds to arraylist
        availableMaps.add(totalMaps
                .stream()
                .filter(duelMap -> duelMap.identifier.equals(map))
                .findFirst()
                .orElse(null));
    }

    public void removeMap(String map){
        // removes from arraylist
        availableMaps = (ArrayList<DuelMap>) availableMaps
                .stream()
                .filter(duelMap -> !duelMap.identifier.equals(map))
                .collect(Collectors.toList());
    }

    public void startDuel(Player player1, Player player2) {

        // checks if there is a map
        if (availableMaps.isEmpty()) {
            player1.sendMessage(ChatColor.RED + "There are no maps available! Try again later.");
            player2.sendMessage(ChatColor.RED + "There are no maps available! Try again later.");
        // runs if there is available map
        } else {
            Random randomGenerator = new Random();
            // Generates new random number
            int index = randomGenerator.nextInt(availableMaps.size());
            DuelMap map = availableMaps.get(index);
            removeMap(map.identifier);
            duelLogic(player1, player2, map);
        }
    }

    public void duelLogic(Player player1, Player player2, DuelMap map) {


        player1.setMetadata("map", new FixedMetadataValue(TazPvP.getInstance(), map.identifier));
        player2.setMetadata("map", new FixedMetadataValue(TazPvP.getInstance(), map.identifier));
        player1.setMetadata("dueling", new FixedMetadataValue(TazPvP.getInstance(), true));
        player2.setMetadata("dueling", new FixedMetadataValue(TazPvP.getInstance(), true));
        player1.setMetadata("opponent", new FixedMetadataValue(TazPvP.getInstance(), player2.getName()));
        player2.setMetadata("opponent", new FixedMetadataValue(TazPvP.getInstance(), player1.getName()));

        ArmorManager.storeAndClearInventory(player1);
        ArmorManager.storeAndClearInventory(player2);
        System.out.println("Stored inventory of " + player1.getName() + System.currentTimeMillis());
        System.out.println("Stored inventory of " + player2.getName() + System.currentTimeMillis());

        equipKit(player1);
        equipKit(player2);

        player1.teleport(map.player1Spawn);
        player2.teleport(map.player2Spawn);

        player1.sendMessage(ChatColor.YELLOW + "Opponent: " + ChatColor.GREEN + player2.getName());
        player2.sendMessage(ChatColor.YELLOW + "Opponent: " + ChatColor.GREEN + player1.getName());
        sendBoth(ChatColor.YELLOW + "Map: " + ChatColor.GREEN + map, player1, player2);
        sendBoth(ChatColor.GREEN + "Fight!", player1, player2);
    }

    public void endDuel(Player looser, Player winner) {
        // checks if the player is in a duel
        looser.setMetadata("dueling", new FixedMetadataValue(TazPvP.getInstance(), false));
        winner.setMetadata("dueling", new FixedMetadataValue(TazPvP.getInstance(), false));

        winner.getInventory().clear();
        looser.getInventory().clear();

        sendBoth(ChatColor.GREEN + winner.getName() + ChatColor.YELLOW + " won the duel!" + ChatColor.RED + looser.getName() + " has lost.", looser, winner);

        new BukkitRunnable() {
            @Override
            public void run() {
                restorePlayer(looser);
                restorePlayer(winner);

                addMap(whatMap(winner));

            }
        }.runTaskLater(TazPvP.getInstance(), 20 * 5);

    }

    public void restorePlayer (Player p) {
        p.teleport(new Location(Bukkit.getWorld("spawn"), 0.5, 50, 0.5, 180, 0));
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(20);
        p.setFoodLevel(20);
        ArmorManager.remArmor(p);
        ArmorManager.restoreInventory(p);
        System.out.println("reloaded inventory of " + p.getName() + System.currentTimeMillis());
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
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
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 5);
        ItemStack butter = new ItemStack(Material.GOLD_INGOT, 3);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 10);
        ItemStack arrow = new ItemStack(Material.ARROW);

        ItemMeta butterMeta = butter.getItemMeta();
        butterMeta.spigot().setUnbreakable(false);
        butter.setItemMeta(butterMeta);
        butterMeta.setDisplayName(ChatColor.WHITE + "Butter");


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

    public void sendBoth(String message, Player player1, Player player2) {
        player1.sendMessage(message);
        player2.sendMessage(message);
    }
}
