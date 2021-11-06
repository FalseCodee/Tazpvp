package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIAchievement extends GUI {

    public GUIAchievement(Player player) {
        super(player, 54, "Achievement");
        init();
        player.openInventory(inventory);
    }

    ItemStack item = createItem(Material.COAL_BLOCK, 1,"This is Coal");

    public void init() {
        //initialize the buttons
//        items[10] = createItem(Material.SIGN, 1, "Kill people or smt");
//        items[11] = createItem(Material.MINECART, 1, "Ach");
//        items[12] = createItem(Material.MINECART, 1, "Ach");
//        items[13] = createItem(Material.MINECART, 1, "Ach");
//        items[14] = createItem(Material.MINECART, 1, "Ach");
//        items[15] = createItem(Material.MINECART, 1, "Ach");
//        items[16] = createItem(Material.MINECART, 1, "Ach");
        int displacement = 10;
        int i = 0;
        for(Achievements ach : TazPvP.achievementsManager.achievements) {

            if(TazPvP.achievementsManager.playerCompletedAchievement(ach, player)) {
                items[i+displacement] = createItem(Material.STORAGE_MINECART, 1, ChatColor.RED + ach.name,
                        ChatColor.WHITE + ach.description + "\n"
                                + ChatColor.GRAY + "Reward: " + ach.getStringRewards()[0]
                                + ChatColor.RED+"\nCompleted!\n");
            } else {
                items[i+displacement] = createItem(Material.MINECART, 1, ChatColor.RED + ach.name,
                        ChatColor.WHITE + ach.description + "\n"
                                + ChatColor.GRAY + "Reward: " + ach.getStringRewards()[0]
                                + ChatColor.RED+"\nIncomplete!\n");
            }
            if((i+1) % 7 == 0) {
                displacement += 2;
            }
            i++;
        }

        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
