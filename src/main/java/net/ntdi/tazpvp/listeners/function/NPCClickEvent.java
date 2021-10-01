package net.ntdi.tazpvp.listeners.function;

import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.ntdi.tazpvp.gui.guis.GUIAchievement;
import net.ntdi.tazpvp.gui.guis.GUIShop;
import net.ntdi.tazpvp.gui.guis.upgrades.GUIMainScreen;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCClickEvent implements Listener {

    @EventHandler
    public void OnNPCLClick(NPCLeftClickEvent event) {
        if(event.getNPC().getId() == 7) { //SHOP
            new GUIShop(event.getClicker());
        } else if(event.getNPC().getId() == 15) { //ACHIEVEMENTS
            new GUIAchievement(event.getClicker());
        } else if(event.getNPC().getId() == 19) { //RANKS

        } else if(event.getNPC().getId() == 32) { //UPGRADES
            new GUIMainScreen(event.getClicker());
        } else if (event.getNPC().getId() == 36) { //FISHERMAN

        }
    }
    @EventHandler
    public void OnNPCRClick(NPCRightClickEvent event) {
        if(event.getNPC().getId() == 7) { //SHOP

        } else if(event.getNPC().getId() == 15) { //ACHIEVEMENTS
            new GUIAchievement(event.getClicker());
        } else if(event.getNPC().getId() == 19) { //RANKS

        } else if(event.getNPC().getId() == 32) { //UPGRADES
            new GUIMainScreen(event.getClicker());
        } else if (event.getNPC().getId() == 36) { //FISHERMAN

        }
    }
}
