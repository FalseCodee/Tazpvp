package net.ntdi.tazpvp.commands.moderation;

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
                            }
                            assert item != null;
                            if (item.getType() == Material.WOOD_SWORD) {
                                cbar.append("s");
                            }
                            if (item.getType() == Material.FISHING_ROD) {
                                cbar.append("f");
                            }
                            if (item.getType() == Material.BOW) {
                                cbar.append("b");
                            }
                            if (item.getType() == Material.WOOD_PICKAXE) {
                                cbar.append("p");
                            }
                            if (item.getType() == Material.COOKED_BEEF) {
                                cbar.append("b");
                            }
                            if (item.getType() == Material.WOOD) {
                                cbar.append("w");
                            }
                        }
                        System.out.println("cbar: " + cbar.toString());
                    } else if (args[0].equalsIgnoreCase("show")) {

                    }
                }
            }
        }
        return true;
    }
}
