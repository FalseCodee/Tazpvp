package net.ntdi.tazpvp.gui.guis;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.achievements.Achievements;
import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;

public class GUIAchievement extends GUI {

    public GUIAchievement(Player player) {
        super(player, 45, ChatColor.BLUE + "" + ChatColor.BOLD + "ACHIEVEMENTS");
        init(0);
        player.openInventory(inventory);
    }



    public void init(int page) {
        int displacement = 10;

        Arrays.fill(items, null);

        for(int i = (page * 21); i < Math.min(21 + (page*21), TazPvP.achievementsManager.achievements.size()); i++) {
            Achievements ach = TazPvP.achievementsManager.achievements.get(i);

            if(TazPvP.achievementsManager.playerCompletedAchievement(ach, player)) {
                items[i+displacement] = createItem(Material.STORAGE_MINECART, 1, ChatColor.RED + ach.name,
                        ChatColor.WHITE + ach.description + "\n"
                                + ChatColor.GRAY + "Reward: " + ach.getStringRewards()[0]
                                + "\n" + ChatColor.GREEN + "Completed!\n");
            } else {
                items[i+displacement] = createItem(Material.MINECART, 1, ChatColor.RED + ach.name,
                        ChatColor.WHITE + ach.description + "\n"
                                + ChatColor.GRAY + "Reward: " + ach.getStringRewards()[0]
                                + "\n" + ChatColor.RED+"Incomplete!\n");
            }
            if((i+1) % 7 == 0) {
                displacement += 2;
            }
        }

        setButtons(44, createItem(Material.ARROW,1, "Next Page"), event -> {
            if((page + 1) * 21 < TazPvP.achievementsManager.achievements.size()) init(page + 1);
        });

        setButtons(36, createItem(Material.ARROW,1, "Previous Page"), event -> {
            if((page - 1) * 21 >= 0) init(page - 1);
        });
        update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, GUI gui) {
        if(gui != this) return;
        e.setCancelled(true);
    }
}
