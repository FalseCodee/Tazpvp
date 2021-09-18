package net.ntdi.tazpvp;

// import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingUse;

import net.ntdi.tazpvp.commands.*;
import net.ntdi.tazpvp.listeners.DeathListener;
import net.ntdi.tazpvp.listeners.GuiListener;
import net.ntdi.tazpvp.listeners.WelcomeListener;
import net.ntdi.tazpvp.managers.CurrencyManager;
import net.ntdi.tazpvp.managers.DeathsManager;
import net.ntdi.tazpvp.managers.JoinsManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class TazPvP extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Tazpvp Logic is now ONLINE");

        CurrencyManager currencyManager = new CurrencyManager(this);
        try {
            currencyManager.loadCurrencyFile();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        DeathsManager deathsManager = new DeathsManager(this);
        try {
            deathsManager.loadDeathsFile();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        JoinsManager joinsManager = new JoinsManager(this);
        try {
            joinsManager.loadJoinsFile();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        // Manger Register
        registerManagers();

        // Command Initializer
        registerCommands();

        // Event Register
        registerListeners();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("Tazpvp LOGIC is now OFFLINE");

        CurrencyManager currencyManager = new CurrencyManager(this);
        try {
            currencyManager.saveCurrencyFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeathsManager deathsManager = new DeathsManager(this);
        try {
            deathsManager.saveDeathsFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JoinsManager joinsManager = new JoinsManager(this);
        try {
            joinsManager.saveJoinsFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerManagers() {

    }
    public void registerCommands() {
        getCommand("sendword").setExecutor((new SendWordCommand()));
        getCommand("sendmessage").setExecutor(new SendMessageCommand());
        getCommand("vault").setExecutor((new VaultCommand()));
        getCommand("currency").setExecutor((new CurrenyCommand()));
    }
    public void registerListeners() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new WelcomeListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
    }

}
