package net.ntdi.tazpvp.gui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GUIManager {
    public static final HashMap<UUID, GUI> guiHashMap = new HashMap<>();

    public static GUI getGUI(Player player) {
        return getGUI(player.getUniqueId());
    }
    public static GUI getGUI(UUID uuid) {
        return guiHashMap.get(uuid);
    }
    public static void addGUI(GUI gui) {
        guiHashMap.put(gui.player.getUniqueId(), gui);
    }
}
