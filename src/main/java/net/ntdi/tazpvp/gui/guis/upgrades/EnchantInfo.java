package net.ntdi.tazpvp.gui.guis.upgrades;

import org.bukkit.enchantments.Enchantment;

public enum EnchantInfo {
    SHARPNESS("Sharpness", "Increases damage dealt.", 350, 1, Enchantment.DAMAGE_ALL),
    KNOCKBACK("Knockback", "Increases distance pushed.", 100, 1, Enchantment.KNOCKBACK),
    SMITE("Unbreaking", "Increases tool durability.", 220, 3, Enchantment.DURABILITY),

    EFFICIENCY("Efficiency", "Increases mining speed.", 80, 5, Enchantment.DIG_SPEED),
    FORTUNE("Fortune", "Doubles mined ores.", 200, 1, Enchantment.LOOT_BONUS_BLOCKS),
    SILK_TOUCH("Silk Touch", "Refines mined ores.", 130, 1, Enchantment.SILK_TOUCH),

    LURE("Lure", "Increases fishing speed.", 3, 3, Enchantment.LURE),
    LOTS("Luck of the Sea", "Increases fish rarity.", 2, 3, Enchantment.LUCK),
    LOOTING("Looting", "Instantly put fish in inventory.", 6, 1, Enchantment.LOOT_BONUS_MOBS),

    POWER("Power", "Powerful arrows.", 1300, 1, Enchantment.ARROW_DAMAGE),
    PUNCH("Punch", "More knockback.", 200, 1, Enchantment.ARROW_KNOCKBACK),
    UNBREAKING("Unbreaking", "Increases tool durability.", 350, 3, Enchantment.DURABILITY),

    PROTECTION("Protection", "Decreases damage taken.", 300, 1, Enchantment.PROTECTION_ENVIRONMENTAL),
    FEATHER_FALLING("Feather Falling", "Decreases fall damage.", 50, 3, Enchantment.PROTECTION_FALL);

    public final String name;
    public final String description;
    public final int cost;
    public final int maxLevel;
    public final Enchantment ench;
    EnchantInfo(String name, String description, int cost, int maxLevel, Enchantment ench){
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.maxLevel = maxLevel;
        this.ench = ench;
    }


}
