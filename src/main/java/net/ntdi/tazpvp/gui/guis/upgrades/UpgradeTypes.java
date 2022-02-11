package net.ntdi.tazpvp.gui.guis.upgrades;

public enum UpgradeTypes {
    SWORD("SWORD",1250, EnchantInfo.SHARPNESS, EnchantInfo.KNOCKBACK, EnchantInfo.UNBREAKING),
    PICKAXE("PICKAXE",350, EnchantInfo.EFFICIENCY, EnchantInfo.FORTUNE, EnchantInfo.SILK_TOUCH),
    ROD("FISHING ROD",-1, EnchantInfo.LURE, EnchantInfo.LOTS, EnchantInfo.LOOTING),
    BOW("BOW",-1, EnchantInfo.POWER, EnchantInfo.PUNCH, EnchantInfo.UNBREAKING),
    ARMOR("ARMOR", 1500, EnchantInfo.PROTECTION, EnchantInfo.FEATHER_FALLING, EnchantInfo.UNBREAKING);

    final EnchantInfo[] enchantments;
    final String name;
    final int reforge;
    UpgradeTypes(String name, int reforge,EnchantInfo... enchantments) {
        this.enchantments = enchantments;
        this.reforge = reforge;
        this.name = name;
    }
}
