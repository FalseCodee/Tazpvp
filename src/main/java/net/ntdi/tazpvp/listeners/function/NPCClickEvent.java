package net.ntdi.tazpvp.listeners.function;

import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.ntdi.tazpvp.gui.GUIManager;
import net.ntdi.tazpvp.gui.guis.GUIAchievement;
import net.ntdi.tazpvp.gui.guis.GUICosmetics;
import net.ntdi.tazpvp.gui.guis.GUIShop;
import net.ntdi.tazpvp.gui.guis.rankstore.GUIMainRankScreen;
import net.ntdi.tazpvp.gui.guis.rankstore.GUIRankMenu;
import net.ntdi.tazpvp.gui.guis.upgrades.GUIMainScreen;
import net.ntdi.tazpvp.listeners.function.Fisherman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCClickEvent implements Listener {

    @EventHandler
    public void OnNPCLClick(NPCLeftClickEvent event) {
        if(GUIManager.getGUI(event.getClicker()) != null) {
            return;
        }
        if(event.getNPC().getId() == 7) { //SHOP
            new GUIShop(event.getClicker());
        } else if(event.getNPC().getId() == 15) { //ACHIEVEMENTS
            new GUIAchievement(event.getClicker());
        } else if(event.getNPC().getId() == 19) { //RANKS
            new GUIMainRankScreen(event.getClicker());
        } else if(event.getNPC().getId() == 32) { //UPGRADES
            new GUIMainScreen(event.getClicker());
        } else if (event.getNPC().getId() == 36) { //FISHERMAN
            new Fisherman(event.getClicker());
        }
    }
    @EventHandler
    public void OnNPCRClick(NPCRightClickEvent event) {
        if(GUIManager.getGUI(event.getClicker()) != null) {
            return;
        }
        if(event.getNPC().getId() == 7) { //SHOP
            new GUIShop(event.getClicker());
        } else if(event.getNPC().getId() == 15) { //ACHIEVEMENTS
            new GUIAchievement(event.getClicker());
        } else if(event.getNPC().getId() == 19) { //RANKS
            new GUIMainRankScreen(event.getClicker());
        } else if(event.getNPC().getId() == 32) { //UPGRADES
            new GUIMainScreen(event.getClicker());
        } else if (event.getNPC().getId() == 36) { //FISHERMAN
            new Fisherman(event.getClicker());
        }
    }
}
