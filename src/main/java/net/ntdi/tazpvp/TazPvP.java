package net.ntdi.tazpvp;

// import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingUse;

import net.ntdi.tazpvp.commands.*;
import net.ntdi.tazpvp.commands.functions.*;
import net.ntdi.tazpvp.commands.moderation.*;
import net.ntdi.tazpvp.commands.playercommands.*;

import net.ntdi.tazpvp.listeners.*;
import net.ntdi.tazpvp.listeners.passive.*;

import net.ntdi.tazpvp.managers.CurrencyManager;
import net.ntdi.tazpvp.managers.DeathsManager;
import net.ntdi.tazpvp.managers.JoinsManager;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class TazPvP extends JavaPlugin implements Listener {

    public static CurrencyManager currencyManager;
    public static DeathsManager deathsManager;
    public static JoinsManager joinsManager;

    public static TazPvP instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Tazpvp Logic is now ONLINE");

        instance = this;

        currencyManager = new CurrencyManager(this);
        currencyManager.loadCurrencyFile();

        deathsManager = new DeathsManager(this);
        deathsManager.loadDeathsFile();

        joinsManager = new JoinsManager(this);
        joinsManager.loadJoinsFile();

        // Manager Register
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

        currencyManager.saveCurrencyFile();

        deathsManager.saveDeathsFile();

        joinsManager.saveJoinsFile();


    }

    public void registerManagers() {

    }
    public void registerCommands() {
        getCommand("sendword").setExecutor((new SendWordCommand()));
        getCommand("sendmessage").setExecutor(new SendMessageCommand());
        getCommand("vault").setExecutor((new VaultCommand()));
        getCommand("currency").setExecutor((new CurrencyCommand()));
        getCommand("starter").setExecutor(new StarterCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("announce").setExecutor(new AnnounceCommand());
        getCommand("apply").setExecutor(new ApplyCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("appeal").setExecutor(new AppealCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("mutechat").setExecutor(new MuteChatCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new WelcomeListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new CommandCancelerListener(), this);
        getServer().getPluginManager().registerEvents(new ChatSpamListener(), this);

    }

    public static TazPvP getInstance(){
        return instance;
    }
}
