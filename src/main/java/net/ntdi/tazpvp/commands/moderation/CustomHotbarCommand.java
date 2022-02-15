package net.ntdi.tazpvp.commands.moderation;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomHotbarCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.isOp()) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("save")) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null) {
                                if (item.getType() == Material.WOOD_SWORD) {
                                    if (!(item.getAmount() == 1)) {
                                        return false;
                                    }
                                }
                                if (item.getType() == Material.FISHING_ROD) {
                                    if (!(item.getAmount() == 1)) {
                                        return false;
                                    }
                                }
                                if (item.getType() == Material.BOW) {
                                    if (!(item.getAmount() == 1)) {
                                        return false;
                                    }
                                }
                                if (item.getType() == Material.WOOD_PICKAXE) {
                                    if (!(item.getAmount() == 1)) {
                                        return false;
                                    }
                                }
                                if (item.getType() == Material.COOKED_BEEF) {
                                    if (!(item.getAmount() == 8)) {
                                        return false;
                                    }
                                }
                                if (item.getType() == Material.WOOD) {
                                    if (!(item.getAmount() == 16)) {
                                        return false;
                                    }
                                }
                            }
                        }
                        System.out.println("Passed all checks");
                        StringBuilder cbar = new StringBuilder();
                        for (int i = 0; i < 9; i++) {
                            ItemStack item = p.getInventory().getItem(i);
                            if (item == null) {
                                cbar.append("n");
                            } else if (item.getType() == Material.WOOD_SWORD) {
                                cbar.append("s");
                            } else if (item.getType() == Material.FISHING_ROD) {
                                cbar.append("f");
                            } else if (item.getType() == Material.BOW) {
                                cbar.append("b");
                            } else if (item.getType() == Material.WOOD_PICKAXE) {
                                cbar.append("p");
                            } else if (item.getType() == Material.COOKED_BEEF) {
                                cbar.append("c");
                            } else if (item.getType() == Material.WOOD) {
                                cbar.append("w");
                            }
                        }
                        System.out.println("cbar: " + cbar.toString());
                        TazPvP.statsManager.setCbar(p, cbar.toString());
                        p.sendMessage(ChatColor.GREEN + "Custom hotbar saved: " + TazPvP.statsManager.getCbar(p));
                    } else if (args[0].equalsIgnoreCase("show")) {
                        p.sendMessage(ChatColor.GREEN + "Custom hotbar: " + TazPvP.statsManager.getCbar(p));
                    } else if (args[0].equalsIgnoreCase("load")) {
                        p.getInventory().clear();
                        String cbar = TazPvP.statsManager.getCbar(p);
                        for (int i = 0; i < 9; i++) {
                            if (cbar.charAt(i) == 's') {
                                p.getInventory().setItem(i, new ItemStack(Material.WOOD_SWORD));
                            } else if (cbar.charAt(i) == 'f') {
                                p.getInventory().setItem(i, new ItemStack(Material.FISHING_ROD));
                            } else if (cbar.charAt(i) == 'b') {
                                p.getInventory().setItem(i, new ItemStack(Material.BOW));
                            } else if (cbar.charAt(i) == 'p') {
                                p.getInventory().setItem(i, new ItemStack(Material.WOOD_PICKAXE));
                            } else if (cbar.charAt(i) == 'c') {
                                p.getInventory().setItem(i, new ItemStack(Material.COOKED_BEEF, 8));
                            } else if (cbar.charAt(i) == 'w') {
                                p.getInventory().setItem(i, new ItemStack(Material.WOOD, 16));
                            } else if (cbar.charAt(i) == 'n') {
                                p.getInventory().setItem(i, new ItemStack(Material.AIR));
                            }
                        }
                        p.getInventory().setItem(9, new ItemStack(Material.ARROW, 10));
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
