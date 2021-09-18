package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import java.io.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class DeathsManager {

    public static  HashMap<UUID, Integer> deaths = new HashMap<UUID, Integer>();

    public TazPvP plugin;

    public DeathsManager(TazPvP plugin){
        this.plugin = plugin;

    }

    public void saveDeathsFile() throws FileNotFoundException, IOException {
        for (OfflinePlayer p : Bukkit.getOfflinePlayers()){
            File file = new File("Tazpvp/deaths.dat");
            ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

            UUID uuid = p.getUniqueId();

            try {
                output.writeObject(deaths);
                output.flush();
                output.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadDeathsFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("Tazpvp/deaths.dat");
        if (file != null) {
            ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
            Object readObject = input.readObject();
            input.close();

            if (!(readObject instanceof HashMap)){
                throw new IOException("Data is not a hashmap");
            }
            deaths = (HashMap<UUID, Integer>) readObject;
            for (UUID key : deaths.keySet()) {
                deaths.put(key, deaths.get(key));
            }
        }

    }
    /*
    Create add, remove, set, and get methods
     */

    public void addDeathsToPlayer(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();
        if (deaths.get(uuid) != null) {
            deaths.put(uuid, deaths.get(uuid) + amount);
        } else {
            deaths.put(uuid, amount);
        }
    }

    public void removeDeathsFromPlayer(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();
        if (deaths.get(uuid) != null) {
            deaths.put(uuid, deaths.get(uuid) - amount);
        }
    }

    public void setDeathsOfPlayer(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();
        deaths.put(uuid, amount);
    }

    public int getPlayerDeaths(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        if (deaths.get(uuid) != null) {
            return deaths.get(uuid);
        } else {
            return 0;
        }
    }
}

