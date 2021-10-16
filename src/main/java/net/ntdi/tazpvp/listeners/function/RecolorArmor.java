package net.ntdi.tazpvp.listeners.function;

import org.bukkit.DyeColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import sun.jvm.hotspot.oops.Metadata;

public class RecolorArmor {

    public void recolorArmor(Player p, DyeColor dyeType){
        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();

        DyeColor dye = dyeType;
        helmet.setData(new MaterialData(dye.getData()));
        chestplate.setData(new MaterialData(dye.getData()));

    }

}
