package net.ntdi.tazpvp.listeners.function;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class RecolorArmor {

    public void recolorArmor(Player p, Color colorType){
        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();

        LeatherArmorMeta helmMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmMeta.setColor(colorType);
        helmet.setItemMeta(helmMeta);

        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplateMeta.setColor(colorType);
        chestplate.setItemMeta(chestplateMeta);



    }

}
