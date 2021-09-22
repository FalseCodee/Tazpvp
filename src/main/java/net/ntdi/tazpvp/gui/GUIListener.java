package net.ntdi.tazpvp.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        GUI gui = GUIManager.getGUI((Player) e.getWhoClicked());
        if(gui != null) {
            gui.onInventoryClick(e, gui);
            if(gui.buttons.get(e.getRawSlot()) != null) {
                gui.buttons.get(e.getRawSlot()).execute(e);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        GUI gui = GUIManager.getGUI((Player) e.getPlayer());
        if(gui != null) {
            gui.onInventoryClose(e, gui);
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e){
        GUI gui = GUIManager.getGUI((Player) e.getPlayer());
        if(gui != null) {
            gui.onInventoryOpen(e, gui);
        }
    }
}
