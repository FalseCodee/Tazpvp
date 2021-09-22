package net.ntdi.tazpvp.gui.guis.upgrades;

import org.bukkit.enchantments.Enchantment;

public enum UpgradeTypes {
    SWORD("SWORD",25, EnchantInfo.SHARPNESS, EnchantInfo.KNOCKBACK, EnchantInfo.SMITE),
    PICKAXE("PICKAXE",20, EnchantInfo.EFFICIENCY, EnchantInfo.FORTUNE, EnchantInfo.SILK_TOUCH),
    ROD("FISHING ROD",-1, EnchantInfo.LURE, EnchantInfo.LOTS, EnchantInfo.LOOTING),
    BOW("BOW",-1, EnchantInfo.FLAME, EnchantInfo.PUNCH, EnchantInfo.INFINITY),
    ARMOR("ARMOR", 30, EnchantInfo.PROTECTION, EnchantInfo.FEATHER_FALLING);

    EnchantInfo[] enchantments;
    String name;
    int reforge;
    UpgradeTypes(String name, int reforge,EnchantInfo... enchantments) {
        this.enchantments = enchantments;
        this.reforge = reforge;
        this.name = name;
    }
}
