package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SSGui extends GUI {

    public SSGui(Player player) {
        super(player, 9, ChatColor.BLUE + "" + ChatColor.BOLD + "SHOP");
        setItems();
        player.openInventory(inventory);

    }
    public void addSSitem(int slot, ItemStack item, String name, String lore) {
        setButtons(slot, createItem(item, name, lore), event -> {
            event.setCancelled(true);

        });
    }

    ItemStack flower = new ItemStack(Material.RED_ROSE, 1, (short) 1);

    @SuppressWarnings("deprecation")
    public void setItems() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items[i] = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), ChatColor.BLACK + "");
        }
        addSSitem(4, new ItemStack(Material.BEDROCK, 1), ChatColor.RED + "YOU ARE BEING SS'D", ChatColor.GRAY + "Join the discord and join the voice call.");
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
