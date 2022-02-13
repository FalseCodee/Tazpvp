package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AssistManager {
    public static HashMap<UUID, ArrayList<UUID>> assists = new HashMap<>();

    public void clearAssists(Player p){
        UUID uuid = p.getUniqueId();
        assists.remove(uuid);
    }

    public void addAssist(Player p, Player assister){
        UUID uuid = p.getUniqueId();
        UUID assisterUUID = assister.getUniqueId();
        ArrayList<UUID> pplinlist = assists.get(uuid);
        if (!pplinlist.contains(assisterUUID)){ //line 25
            assists.get(uuid).add(assisterUUID);
        }
    }

    public void removeAssist(Player assister){
        for (UUID uuid : assists.keySet()){
            ArrayList<UUID> assisters = assists.get(uuid);
            if(assisters.contains(assister.getUniqueId())){
                assisters.remove(assister.getUniqueId());
            }
        }
    }

    public void rewardAssisters(Player p, Integer money, Integer exp){
        UUID uuid = p.getUniqueId();
        if(assists.containsKey(uuid)){
            ArrayList<UUID> assisters = assists.get(uuid);
            for(UUID assister : assisters){
                Player plr = Bukkit.getPlayer(assister);
                if(plr.isOnline()){
                    plr.getPlayer().sendMessage("Recieved assisted reward");
                }
                if (TazPvP.statsManager.getRebirths(plr) < 1){
                    TazPvP.statsManager.addMoney(plr, money+3);
                    TazPvP.statsManager.addExp(plr, exp+3);
                } else {
                    TazPvP.statsManager.addMoney(plr, money);
                    TazPvP.statsManager.addExp(plr, exp);
                }
            }
        }
    }

    public ArrayList getAssistList(Player p){
        UUID uuid = p.getUniqueId();
        if(assists.containsKey(uuid)){
            return assists.get(uuid);
        }
        return null;
    }
}
