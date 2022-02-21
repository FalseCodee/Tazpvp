package net.ntdi.tazpvp.gui.guis.upgrades;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIUpgradeMenu extends GUI {
    final UpgradeTypes type;
    public GUIUpgradeMenu(Player player, UpgradeTypes type) {
        super(player, 27, ChatColor.BLUE + "" + ChatColor.BOLD + type.name());
        this.type = type;
        init();
        player.openInventory(inventory);
    }
    @SuppressWarnings("deprecation")
    public void init() {
        ItemStack target = updateTarget();

        if(target != null) {
            ItemStack button = new ItemStack(Material.ENCHANTED_BOOK, 1, DyeColor.BLUE.getData());
            //button.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            button.getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
            ItemStack anvil = new ItemStack(Material.ANVIL, 1);
            //button.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            button.getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
            int slot = (type == UpgradeTypes.SWORD || type == UpgradeTypes.PICKAXE) ? 10 : 11 ;
            for(EnchantInfo enchantment : type.enchantments) {
                setButtons(slot, createItem(button, ChatColor.WHITE + enchantment.name +ChatColor.BLUE +" +1",
                                ChatColor.BLUE + enchantment.description+"\n"
                                        + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + enchantment.cost + "\n"
                                        + ChatColor.GOLD + "Max lvl: " + ChatColor.GRAY + (enchantment.maxLevel + ((TazPvP.statsManager.getRebirths(player) > 0) ? 1 : 0))),
                        event -> {
                            event.setCancelled(true);
                            if(TazPvP.statsManager.getMoney(player) >= enchantment.cost) {
                                ItemStack finalTarget = updateTarget();
                                if(finalTarget.getEnchantmentLevel(enchantment.ench) < enchantment.maxLevel + ((TazPvP.statsManager.getRebirths(player) > 0) ? 1 : 0)) {
                                    TazPvP.statsManager.addMoney(player, -enchantment.cost);
                                    finalTarget.addUnsafeEnchantment(enchantment.ench, finalTarget.getEnchantmentLevel(enchantment.ench)+1);
                                    if(type == UpgradeTypes.ARMOR && enchantment != EnchantInfo.FEATHER_FALLING) {
                                        ItemStack leggings = player.getInventory().getLeggings();
                                        leggings.addEnchantment(enchantment.ench, leggings.getEnchantmentLevel(enchantment.ench)+1);
                                        player.getInventory().setLeggings(leggings);
                                    }
                                    player.sendMessage(ChatColor.DARK_PURPLE+"You upgraded " + ChatColor.LIGHT_PURPLE + enchantment.name + ChatColor.DARK_PURPLE + " on your tool for " + ChatColor.LIGHT_PURPLE + "$" + enchantment.cost);
                                    player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                                } else {
                                    player.sendMessage(ChatColor.RED + "You already have the max level!");
                                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "Insufficient funds.");
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                            }
                        });
                slot += 2;
            }
            if(type == UpgradeTypes.SWORD || type == UpgradeTypes.PICKAXE/* || type == UpgradeTypes.ARMOR*/) {
            setButtons(slot, createItem(anvil, ChatColor.GREEN + "Reforge " + type.name,
                            ChatColor.BLUE + "Upgrade " +type.name + " material"+"\n"
                                    + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + type.reforge + "\n"
                                    + ChatColor.RED + "WARNING: RESETS ENCHANTMENTS"),
                    event -> {
                    if(TazPvP.statsManager.getMoney(player) >= type.reforge){
                        ItemStack finalTarget = updateTarget();
                        if(type == UpgradeTypes.SWORD) {
                            if(finalTarget.getType() == Material.WOOD_SWORD) {
                                player.getInventory().remove(finalTarget);
                                TazPvP.statsManager.addMoney(player, -type.reforge);
                                ItemStack item = new ItemStack(Material.STONE_SWORD);
                                ItemMeta meta = item.getItemMeta();
                                meta.spigot().setUnbreakable(true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                            } else {
                                player.sendMessage(ChatColor.RED + "You already have the max level!");
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                                return;
                            }
                        } else {
                            if(finalTarget.getType() == Material.WOOD_PICKAXE) {
                                player.getInventory().remove(finalTarget);
                                TazPvP.statsManager.addMoney(player, -type.reforge);
                                ItemStack item = new ItemStack(Material.STONE_PICKAXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.spigot().setUnbreakable(true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                            } else if (finalTarget.getType() == Material.STONE_PICKAXE) {
                                player.getInventory().remove(finalTarget);
                                TazPvP.statsManager.addMoney(player, -type.reforge);
                                ItemStack item = new ItemStack(Material.IRON_PICKAXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.spigot().setUnbreakable(true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                            } else {
                                player.sendMessage(ChatColor.RED + "You already have the max level!");
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                                return;
                            }
                        }
                        player.sendMessage(ChatColor.DARK_PURPLE+"You reforged your " + ChatColor.LIGHT_PURPLE + type.name + ChatColor.DARK_PURPLE + " for " + ChatColor.LIGHT_PURPLE + "$" + type.reforge);
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                    } else {
                        player.sendMessage(ChatColor.RED + "Insufficient funds.");
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                    });
            }
        } else {
            player.sendMessage(ChatColor.RED + "Please make sure you have this item in your inventory.");
            Bukkit.getScheduler().runTask(TazPvP.getInstance(), player::closeInventory);
        }
        update();
    }

    public ItemStack updateTarget(){
        ItemStack target = null;
        switch (type){
            case SWORD:
                target = getSword();
                break;
            case PICKAXE:
                target = getPickaxe();
                break;
            case ROD:
                target = getRod();
                break;
            case BOW:
                target = getBow();
                break;
            case ARMOR:
                target = getArmor();
                break;
        }
        return target;
    }


    public ItemStack getSword() {
        for(int i = 0; i < player.getInventory().getSize(); i++) {
            if(player.getInventory().getItem(i) == null) continue;
            Material m = player.getInventory().getItem(i).getType();
            if(m == Material.WOOD_SWORD || m == Material.STONE_SWORD ||m == Material.IRON_SWORD ||m == Material.GOLD_SWORD ||m == Material.DIAMOND_SWORD) {
                return player.getInventory().getItem(i);
            }
        }
        return null;
    }
    public ItemStack getPickaxe() {
        for(int i = 0; i < player.getInventory().getSize(); i++) {
            if(player.getInventory().getItem(i) == null) continue;
            Material m = player.getInventory().getItem(i).getType();
            if(m == Material.WOOD_PICKAXE || m == Material.STONE_PICKAXE ||m == Material.IRON_PICKAXE ||m == Material.GOLD_PICKAXE ||m == Material.DIAMOND_PICKAXE) {
                return player.getInventory().getItem(i);
            }
        }
        return null;
    }
    public ItemStack getRod() {
        for(int i = 0; i < player.getInventory().getSize(); i++) {
            if(player.getInventory().getItem(i) == null) continue;
            if(player.getInventory().getItem(i).getItemMeta().hasDisplayName() &&player.getInventory().getItem(i).getItemMeta().getDisplayName().equals(Items.GRAPPLING_HOOK.display)) continue;
            Material m = player.getInventory().getItem(i).getType();
            if(m == Material.FISHING_ROD ) {
                return player.getInventory().getItem(i);
            }
        }
        return null;
    }
    public ItemStack getBow() {
        for(int i = 0; i < player.getInventory().getSize(); i++) {
            if(player.getInventory().getItem(i) == null) continue;
            Material m = player.getInventory().getItem(i).getType();
            if(m == Material.BOW) {
                return player.getInventory().getItem(i);
            }
        }
        return null;
    }
    public ItemStack getArmor() {
        for(int i = 0; i < player.getInventory().getArmorContents().length; i++) {
            if(player.getInventory().getArmorContents()[i] == null) continue;
            Material m = player.getInventory().getArmorContents()[i].getType();
            if(m == Material.LEATHER_BOOTS || m == Material.CHAINMAIL_BOOTS || m == Material.IRON_BOOTS || m == Material.GOLD_BOOTS || m == Material.DIAMOND_BOOTS) {
                return player.getInventory().getArmorContents()[i];
            }
        }
        return null;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}