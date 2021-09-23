package net.ntdi.tazpvp.commands.functions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VaultCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

//        if(sender instanceof Player){
//            Player p = (Player) sender;
//
//            Inventory vault = Bukkit.createInventory(p, 45, "Egg Vault");
//
//            ItemStack eggg = new ItemStack(Material.EGG, 16);
//            ItemStack lgpane = new ItemStack(Material.STAINED_GLASS_PANE, 1);
//            ItemStack gpane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
//            ItemStack bpane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
//
//            eggg.addUnsafeEnchantment(Enchantment.MENDING, 100);
//
//            ItemMeta meta = eggg.getItemMeta();
//            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Explosive Egg");
//            meta.setUnbreakable(true);
//            ArrayList<String> lore = new ArrayList<>();
//            lore.add(ChatColor.RED + "Explosive Egg");
//            lore.add(ChatColor.RED + "Rightclick to throw");
//            meta.setLore(lore);
//            eggg.setItemMeta(meta);
//
//            ItemMeta meta2 = lgpane.getItemMeta();
//            meta2.setDisplayName(ChatColor.RED + "Take the EGG");
//            lgpane.setItemMeta(meta2);
//            ItemMeta meta3 = gpane.getItemMeta();
//            meta3.setDisplayName(ChatColor.RED + "Take the EGG");
//            gpane.setItemMeta(meta3);
//            ItemMeta meta4 = bpane.getItemMeta();
//            meta4.setDisplayName(ChatColor.RED + "Take the EGG");
//            bpane.setItemMeta(meta4);
//
//            for (int i = 0; i < 12; i++) {
//                vault.setItem(i, bpane);
//            }
//            for (int i = 33; i < 45; i++) {
//                vault.setItem(i, bpane);
//            }
//            for (int i = 18; i < 22; i++) {
//                vault.setItem(i, lgpane);
//            }
//            for (int i = 12; i < 15; i++) {
//                vault.setItem(i, lgpane);
//            }
//            for (int i = 30; i < 33; i++) {
//                vault.setItem(i, gpane);
//            }
//            for (int i = 23; i < 27; i++) {
//                vault.setItem(i, gpane);
//            }
//            vault.setItem(15, bpane);
//            vault.setItem(16, bpane);
//            vault.setItem(17, bpane);
//            vault.setItem(27, bpane);
//            vault.setItem(28, bpane);
//            vault.setItem(29, bpane);
//            vault.setItem(22, eggg);
//
//            p.openInventory(vault);
//
//        }else{
//            System.out.println("Player only COMMAND");
//        }

        return true;
    }
}
