package net.ntdi.tazpvp.managers;

import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class IpManager {
    public static HashMap<UUID, InetSocketAddress> ipList = new HashMap<>();

    public void storePlayerIp(Player p){
        UUID uuid = p.getUniqueId();
        InetSocketAddress ip = p.getAddress();
        ipList.remove(uuid);
        ipList.put(uuid, ip);
    }

    public void removePlayerIp(Player p){
        UUID uuid = p.getUniqueId();
        ipList.remove(uuid);
    }

    public List<UUID> findMatchingIp(Player p){
        UUID uuid = p.getUniqueId();
        InetSocketAddress ip = p.getAddress();
        List<UUID> matchers = new ArrayList<UUID>();
        for(UUID u : ipList.keySet()){
            if(ipList.get(u).equals(ip)){
                matchers.add(u);
            }
        }
        if (matchers.size() == 0){
            return null;
        } else {
            return matchers;
        }
    }
}
