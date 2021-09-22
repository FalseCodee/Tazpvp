package net.ntdi.tazpvp.gui.guis.upgrades;

import net.ntdi.tazpvp.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GUIMainScreen extends GUI {
    public GUIMainScreen(Player player) {
        super(player, 27, ChatColor.RED + "Upgrade Menu");
        init();
        player.openInventory(inventory);
    }
    public void init() {
        //initialize the buttons

    }
}
