package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class ArmorManager {

    static Map<UUID, ItemStack[]> items = new HashMap<UUID, ItemStack[]>();
    static Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();
    final static String outputFilePath = TazPvP.getInstance().getDataFolder().getAbsolutePath() + "/inv.properties";
    final static String outputFilePath2 = TazPvP.getInstance().getDataFolder().getAbsolutePath() + "/inv.properties";

    public static void storeAndClearInventory(Player player){
        UUID uuid = player.getUniqueId();

        ItemStack[] cont = player.getInventory().getContents();
        ItemStack[] armcont = player.getInventory().getArmorContents();

        items.put(uuid, cont);
        armor.put(uuid, armcont);

        player.getInventory().clear();

        remArmor(player);
    }

    public static void restoreInventory(Player player){
        UUID uuid = player.getUniqueId();

        ItemStack[] contents = items.get(uuid);
        ItemStack[] armorContents = armor.get(uuid);

        if(contents != null){
            player.getInventory().setContents(contents);
            System.out.println("Restored inventory");
        } else {
            //player.getInventory().clear();
        }

        if(armorContents != null){
            player.getInventory().setArmorContents(armorContents);
            System.out.println("Restored inventory");
        } else {
            //remArmor(player);
        }
    }

    public static void remArmor(Player player){
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public static void saveinv() throws IOException {
        File file = new File(outputFilePath);
        Properties properties = new Properties();
        properties.putAll(items);

        properties.store(new FileOutputStream(outputFilePath), null);

    }

    public static void savearmor() throws IOException {
        File file = new File(outputFilePath2);
        Properties properties = new Properties();
        properties.putAll(armor);

        properties.store(new FileOutputStream(outputFilePath2), null);

    }

//    public static void loadinv() throws IOException {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(outputFilePath));
//        items.putAll(properties);
//    }
}
