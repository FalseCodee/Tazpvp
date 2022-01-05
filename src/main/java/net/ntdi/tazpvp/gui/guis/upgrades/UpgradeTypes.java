package net.ntdi.tazpvp.gui.guis.upgrades;

public enum UpgradeTypes {
    SWORD("SWORD",1, EnchantInfo.SHARPNESS, EnchantInfo.KNOCKBACK, EnchantInfo.SMITE),
    PICKAXE("PICKAXE",1, EnchantInfo.EFFICIENCY, EnchantInfo.FORTUNE, EnchantInfo.SILK_TOUCH),
    ROD("FISHING ROD",-1, EnchantInfo.LURE, EnchantInfo.LOTS, EnchantInfo.LOOTING),
    BOW("BOW",-1, EnchantInfo.POWER, EnchantInfo.PUNCH, EnchantInfo.INFINITY),
    ARMOR("ARMOR", 1, EnchantInfo.PROTECTION, EnchantInfo.FEATHER_FALLING);

    final EnchantInfo[] enchantments;
    final String name;
    final int reforge;
    UpgradeTypes(String name, int reforge,EnchantInfo... enchantments) {
        this.enchantments = enchantments;
        this.reforge = reforge;
        this.name = name;
    }
}
