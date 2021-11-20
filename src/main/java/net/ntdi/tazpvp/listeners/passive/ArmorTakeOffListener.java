package net.ntdi.tazpvp.listeners.passive;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ArmorTakeOffListener implements Listener {

    @EventHandler
    public void onArmorTakeOff(InventoryClickEvent event) {
        if (!event.getWhoClicked().hasPermission("staff.armor")) {
            if (event.getClick().isLeftClick() || event.getClick().isRightClick() || event.getClick().isShiftClick() || event.getClick().isKeyboardClick()) {
                if (event.getSlot() == 100 || event.getSlot() == 101 || event.getSlot() == 102 || event.getSlot() == 103) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
