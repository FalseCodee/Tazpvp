package net.ntdi.tazpvp;

// import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingUse;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.ntdi.tazpvp.commands.*;
import net.ntdi.tazpvp.commands.functions.*;
import net.ntdi.tazpvp.commands.moderation.*;
import net.ntdi.tazpvp.commands.playercommands.*;

import net.ntdi.tazpvp.gui.GUIListener;
import net.ntdi.tazpvp.listeners.*;
import net.ntdi.tazpvp.listeners.function.*;
import net.ntdi.tazpvp.listeners.passive.*;

import net.ntdi.tazpvp.managers.*;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class TazPvP extends JavaPlugin {

    public static StatsManager statsManager;
    public static PunishmentManager punishmentManager;
    public static StaffManager staffManager;
    public static AchievementsManager achievementsManager;

    public static Permission permissions;

    public static FileConfiguration configFile;
    public static File helpFile;
    public static File ruleFile;

    public static TazPvP instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Tazpvp Logic is now ONLINE");

        configFile = this.getConfig();
        initConfig();

        instance = this;

        statsManager = new StatsManager();
        punishmentManager = new PunishmentManager();
        staffManager = new StaffManager();
        achievementsManager = new AchievementsManager();

        if(getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
            if(rsp != null) {
                permissions = rsp.getProvider();
            }
        } else {
            System.out.println("Vault not found!");
        }
        // Manager Register
        registerManagers();

        // Command Initializer
        registerCommands();

        // Event Register
        registerListeners();
        load();
        try {
            if(helpFile.createNewFile()){
                System.out.println("Creating help file");
            }
            if(ruleFile.createNewFile()) {
                System.out.println("Creating rules file");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load() {
        helpFile = new File(getDataFolder() + "/help.txt");
        ruleFile = new File(getDataFolder() + "/rules.txt");
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("Tazpvp LOGIC is now OFFLINE");

        this.saveConfig();

        statsManager.saveStats();
        punishmentManager.savePunishments();
        staffManager.saveStaffFile();
        achievementsManager.saveAchievements();
    }

    public void registerManagers() {

    }
    public void registerCommands() {
        getCommand("sendword").setExecutor((new SendWordCommand()));
        getCommand("sendmessage").setExecutor(new SendMessageCommand());
        getCommand("vault").setExecutor((new VaultCommand()));
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
        getCommand("money").setExecutor(new MoneyCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("report").setExecutor(new ReportCommand());
        getCommand("warn").setExecutor(new WarnCommand());
        getCommand("warns").setExecutor(new WarnsCommand());
        getCommand("unwarn").setExecutor(new UnWarnCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("enchant").setExecutor(new EnchantCommand());
        getCommand("playtime").setExecutor(new PlayTimeCommand());
        getCommand("hide").setExecutor(new HideCommand());
        getCommand("dailyrewards").setExecutor(new RedeemCommand());
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new WelcomeListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new CommandCancelerListener(), this);
        getServer().getPluginManager().registerEvents(new ChatSpamListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new VanishCommand(), this);
        getServer().getPluginManager().registerEvents(new GUIListener(), this);

    }

    public void initConfig(){
        configFile.options().copyDefaults(true);
        this.saveConfig();
    }

    public static TazPvP getInstance(){
        return instance;
    }
}
