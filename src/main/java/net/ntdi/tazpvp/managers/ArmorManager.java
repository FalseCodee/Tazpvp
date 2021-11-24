package net.ntdi.tazpvp.managers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArmorManager {

    static Map<UUID, ItemStack[]> items = new HashMap<UUID, ItemStack[]>();
    static Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();

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
        } else {
            //player.getInventory().clear();
        }

        if(armorContents != null){
            player.getInventory().setArmorContents(armorContents);
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
}
