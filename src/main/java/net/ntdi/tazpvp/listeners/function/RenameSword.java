package net.ntdi.tazpvp.listeners.function;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class RenameSword {

    public void renameSword(Player p, String rename){

        ItemStack sword = getSword(p);

        if (sword != null){
            ItemMeta swordMeta = sword.getItemMeta();
            swordMeta.setDisplayName(rename);
            swordMeta.setLore(Collections.singletonList(ChatColor.ITALIC + "" + ChatColor.GRAY + "RENAMED"));
            sword.setItemMeta(swordMeta);
        } else {
            p.sendMessage(ChatColor.RED + "Rename failed, contact mom- I mean Ntdi");
        }

    }

    public ItemStack getSword(Player player) {
        for(int i = 0; i < player.getInventory().getSize(); i++) {
            if(player.getInventory().getItem(i) == null) continue;
            Material m = player.getInventory().getItem(i).getType();
            if(m == Material.WOOD_SWORD || m == Material.STONE_SWORD ||m == Material.IRON_SWORD ||m == Material.GOLD_SWORD ||m == Material.DIAMOND_SWORD) {
                return player.getInventory().getItem(i);
            }
        }
        return null;
    }

}
