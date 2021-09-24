package net.ntdi.tazpvp;

// import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingUse;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import net.ntdi.tazpvp.commands.*;
import net.ntdi.tazpvp.commands.functions.*;
import net.ntdi.tazpvp.commands.moderation.*;
import net.ntdi.tazpvp.commands.playercommands.*;

import net.ntdi.tazpvp.gui.GUIListener;
import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.listeners.function.*;
import net.ntdi.tazpvp.listeners.items.*;
import net.ntdi.tazpvp.listeners.passive.*;

import net.ntdi.tazpvp.managers.*;

import net.ntdi.tazpvp.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class TazPvP extends JavaPlugin {

    public static StatsManager statsManager;
    public static PunishmentManager punishmentManager;
    public static StaffManager staffManager;
    public static AchievementsManager achievementsManager;

    public static Permission permissions;
    public static Chat chat;

    static ProtocolManager protocolManager;

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
        ItemManager.init();

        if(getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
            RegisteredServiceProvider<Chat> rsp1 = getServer().getServicesManager().getRegistration(Chat.class);
            if(rsp != null) {
                permissions = rsp.getProvider();
            }
            if(rsp1 != null) {
                chat = rsp1.getProvider();
            }
        } else {
            System.out.println("Vault not found!");
        }

        protocolManager = ProtocolLibrary.getProtocolManager();
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
        getCommand("achievement").setExecutor(new AchievementGuiCommand());
        getCommand("bounties").setExecutor(new BountyCommand());
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
        getServer().getPluginManager().registerEvents(new PlayerFishEvent(), this);
        getServer().getPluginManager().registerEvents(new NPCClickEvent(), this);
        getServer().getPluginManager().registerEvents(new CrateListener(), this);

    }

    public void initConfig(){
        configFile.options().copyDefaults(true);
        this.saveConfig();
    }

    public void initScoreboard(Player player) {

                    Scoreboard sb = statsManager.scoreboards.get(player.getUniqueId());
                    Objective objective = sb.getObjective("sb");
                    objective.unregister();
                    objective = sb.registerNewObjective("sb", "dummy");
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    objective.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&b-&3=&b- &3tazpvp &b-&3=&b-"));
                    Score blank = objective.getScore("");
                    blank.setScore(10);
                    Score level = objective.getScore(ChatColor.DARK_AQUA + "Level  " + ChatColor.GRAY + TazPvP.statsManager.getLevel(player));
                    level.setScore(9);
                    Score money = objective.getScore(ChatColor.DARK_AQUA + "Money  " + ChatColor.GRAY +  "$"+ TazPvP.statsManager.getMoney(player));
                    money.setScore(8);
                    Score points = objective.getScore(ChatColor.DARK_AQUA + "Points  " + ChatColor.GRAY + TazPvP.statsManager.getPoints(player));
                    points.setScore(7);
                    Score credits = objective.getScore(ChatColor.AQUA + "Credits  " + ChatColor.GRAY + TazPvP.statsManager.getCredits(player));
                    credits.setScore(6);
                    Score blank1 = objective.getScore("");
                    blank1.setScore(5);
                    Score streak = objective.getScore(ChatColor.RED + "Streak  " + ChatColor.GRAY + TazPvP.statsManager.getStreak(player));
                    streak.setScore(4);
                    Score kills = objective.getScore(ChatColor.RED + "Kills  " + ChatColor.GRAY + TazPvP.statsManager.getKills(player));
                    kills.setScore(3);
                    Score deaths = objective.getScore(ChatColor.RED + "Deaths  " + ChatColor.GRAY + TazPvP.statsManager.getDeaths(player));
                    deaths.setScore(2);
                    Score kdr = objective.getScore(ChatColor.RED + "KDR  " + ChatColor.GRAY + ((TazPvP.statsManager.getDeaths(player) > 0) ?  MathUtils.round((float) TazPvP.statsManager.getKills(player) / TazPvP.statsManager.getDeaths(player), 2) : TazPvP.statsManager.getKills(player)));
                    kdr.setScore(1);
                    for(Player player1 : Bukkit.getOnlinePlayers()) {
                        statsManager.getTeam(player1, sb).addEntry(player1.getName());
                    }
                    if(permissions.getPrimaryGroup(player).equals("default")) {
                        player.setPlayerListName(ChatColor.GRAY + player.getDisplayName());
                    } else {
                        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&',chat.getGroupPrefix((String) null, permissions.getPrimaryGroup(player)) + player.getDisplayName()));
                    }
                    player.setScoreboard(sb);
                }
    public static void sendTablistHeaderAndFooter(Player player, String header, String footer) {
        PacketContainer container = new PacketContainer(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        container.getChatComponents()
                .write(0, WrappedChatComponent.fromText(header))
                .write(1, WrappedChatComponent.fromText(footer));
        try{
            protocolManager.sendServerPacket(player, container);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static TazPvP getInstance(){
        return instance;
    }
}
