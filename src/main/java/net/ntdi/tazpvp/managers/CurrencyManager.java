package net.ntdi.tazpvp.managers;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CurrencyManager {

    public static HashMap<UUID, Integer> currency = new HashMap<UUID, Integer>();

    public TazPvP plugin;

    public CurrencyManager(TazPvP plugin){
        this.plugin = plugin;

    }

    public void saveCurrencyFile() throws FileNotFoundException, IOException {
        for (OfflinePlayer p : Bukkit.getOfflinePlayers()){
            File file = new File("Tazpvp/currency.dat");
            ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

            UUID uuid = p.getUniqueId();

            if (currency.get(uuid) != null) {
                currency.put(uuid, currency.get(uuid));
            }

            try {
                output.writeObject(currency);
                output.flush();
                output.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadCurrencyFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("Tazpvp/currency.dat");
        if (file != null) {
            ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
            Object readObject = input.readObject();
            input.close();

            if (!(readObject instanceof HashMap)){
                throw new IOException("Data is not a hashmap");
            }
            currency = (HashMap<UUID, Integer>) readObject;
            for (UUID key : currency.keySet()) {
                currency.put(key, currency.get(key));
            }
        }

    }
    /*
    Create add, remove, set, and get methods
     */

    public void addCurrencyToPlayer(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();
        if (currency.get(uuid) != null) {
            currency.put(uuid, currency.get(uuid) + amount);
        } else {
            currency.put(uuid, amount);
        }
    }
    public void removeCurrencyFromPlayer(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();
        if (currency.get(uuid) != null) {
            currency.put(uuid, currency.get(uuid) - amount);
        }
    }

    public void setCurrencyOfPlayer(OfflinePlayer p, int amount) {
        UUID uuid = p.getUniqueId();
        currency.put(uuid, amount);
    }
    public int getPlayerCurrency(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        if (currency.get(uuid) != null) {
            return currency.get(uuid);
        } else {
            return 0;
        }
    }
}

