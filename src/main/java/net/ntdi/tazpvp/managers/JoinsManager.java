package net.ntdi.tazpvp.managers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class JoinsManager {

    public static HashMap<UUID, Boolean> joins = new HashMap<UUID, Boolean>();

    public TazPvP plugin;

    public JoinsManager(TazPvP plugin){
        this.plugin = plugin;

    }

    public void saveJoinsFile() {
        for (OfflinePlayer p : Bukkit.getOfflinePlayers()){
            File file = new File("Tazpvp/joins.dat");

            try {
                ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));


            UUID uuid = p.getUniqueId();

                output.writeObject(joins);
                output.flush();
                output.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadJoinsFile() {
        File file = new File("Tazpvp/joins.dat");
        if (file != null) {
            try{
                ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
                Object readObject = input.readObject();
                input.close();

//            if (!(readObject instanceof HashMap)){
//                throw new IOException("Data is not a hashmap");
//            }

                joins = (HashMap<UUID, Boolean>) readObject;
                for (UUID key : joins.keySet()) {
                    joins.put(key, joins.get(key));
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
    /*
    Create add, remove, set, and get methods
     */

    public void addToList(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        joins.put(uuid, true);
    }
    public void removeFromList(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        joins.put(uuid, false);
    }
    public boolean getFromList(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        if (joins.get(uuid) != null) {
            return joins.get(uuid);
        } else {
            return false;
        }
    }
}

