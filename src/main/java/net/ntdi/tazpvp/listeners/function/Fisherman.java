package net.ntdi.tazpvp.listeners.function;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Fisherman {

    public Fisherman(Player p){
        ItemStack inhand = p.getItemInHand();

        int amount = p.getItemInHand().getAmount();

        if (Objects.equals(inhand, new ItemStack(Material.COAL_ORE, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 0.5);
        } else if (Objects.equals(inhand, new ItemStack(Material.IRON_ORE, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 2);
        } else if (Objects.equals(inhand, new ItemStack(Material.GOLD_ORE, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 4);
        } else if (Objects.equals(inhand, new ItemStack(Material.LAPIS_ORE, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 5);
        } else if (Objects.equals(inhand, new ItemStack(Material.DIAMOND_ORE, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 8);
        }else if (Objects.equals(inhand, new ItemStack(Material.EMERALD_ORE, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 10);
        } else if (Objects.equals(inhand, new ItemStack(Material.COAL, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 2);
        } else if (Objects.equals(inhand, new ItemStack(Material.IRON_INGOT, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 3);
        } else if (Objects.equals(inhand, new ItemStack(Material.GOLD_INGOT, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 4);
//        } else if (Objects.equals(inhand, new ItemStack(Material.LAPIS_ORE, amount))) {
//            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
//            TazPvP.statsManager.addMoney(p, amount * 5);
        } else if (Objects.equals(inhand, new ItemStack(Material.DIAMOND, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 12);
        }else if (Objects.equals(inhand, new ItemStack(Material.EMERALD, amount))) {
            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
            TazPvP.statsManager.addMoney(p, amount * 15);
        }
    }
}
