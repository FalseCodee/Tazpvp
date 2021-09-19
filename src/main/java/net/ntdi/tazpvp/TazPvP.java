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

import net.ntdi.tazpvp.managers.StatsManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TazPvP extends JavaPlugin implements Listener {

    public static CurrencyManager currencyManager;
    public static DeathsManager deathsManager;
    public static JoinsManager joinsManager;
    public static StatsManager statsManager;

    public static FileConfiguration configFile;

    public static TazPvP instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Tazpvp Logic is now ONLINE");

        configFile = this.getConfig();
        initConfig();

        instance = this;

        statsManager = new StatsManager();

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

        this.saveConfig();

        currencyManager.saveCurrencyFile();

        deathsManager.saveDeathsFile();

        joinsManager.saveJoinsFile();

        statsManager.saveStats();
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
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("points").setExecutor(new PointsCommand());
        getCommand("levels").setExecutor(new LevelCommand());
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new WelcomeListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new CommandCancelerListener(), this);
        getServer().getPluginManager().registerEvents(new ChatSpamListener(), this);

    }

    public void initConfig(){
        configFile.options().copyDefaults(true);
        this.saveConfig();
    }

    public static TazPvP getInstance(){
        return instance;
    }
}
