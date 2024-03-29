package net.ntdi.tazpvp;

// import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingUse;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import net.ntdi.tazpvp.commands.SendMessageCommand;
import net.ntdi.tazpvp.commands.SendWordCommand;
import net.ntdi.tazpvp.commands.functions.VaultCommand;
import net.ntdi.tazpvp.commands.moderation.*;
import net.ntdi.tazpvp.commands.playercommands.*;
import net.ntdi.tazpvp.gui.GUIListener;
import net.ntdi.tazpvp.items.ItemManager;
import net.ntdi.tazpvp.items.items.LevFeather;
import net.ntdi.tazpvp.listeners.function.*;
import net.ntdi.tazpvp.listeners.items.PlayerFishEvent;
import net.ntdi.tazpvp.listeners.items.SnowballHitEvent;
import net.ntdi.tazpvp.listeners.passive.*;
import net.ntdi.tazpvp.managers.*;
import net.ntdi.tazpvp.managers.Duels.DuelManager;
import net.ntdi.tazpvp.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public final class TazPvP extends JavaPlugin {

    public static StatsManager statsManager;
    public static PunishmentManager punishmentManager;
    public static StaffManager staffManager;
    public static AchievementsManager achievementsManager;
    public static PerkManager perkManager;
    public static ZombieLogic zombieLogic;
    public static DuelManager duelManager;
    public static IpManager ipmanager;
    public static AssistManager assistmanager;

    public static Permission permissions;
    public static Chat chat;

    static ProtocolManager protocolManager;

    public static FileConfiguration configFile;
    public static File helpFile;
    public static File ruleFile;

    public static final ArrayList<Material> blocks = new ArrayList<>();
    public static Boolean AllowBlocks = true;

    public static final ArrayList<Player> invunerable = new ArrayList<>();

    public static final HashMap<Player, ItemStack> robbery = new HashMap<>();
    public static final HashMap<Player, Boolean> inInvseeGui = new HashMap<>();
    //public static HashMap<Player, Integer> heartGained = new HashMap<>();

    public static final ArrayList<Player> newPm = new ArrayList<>();

    public static final ArrayList<Player> voteYes = new ArrayList<>();
    public static final ArrayList<Player> voteNo = new ArrayList<>();
    public static Boolean VoteOn = false;
    public static Player votedOut;


    public static final ArrayList<Player> renamingSword = new ArrayList<>();

    public static final ArrayList<Player> ProGiftRank = new ArrayList<>();
    public static final ArrayList<Player> ChampionGiftRank = new ArrayList<>();
    public static final ArrayList<Player> LegendGiftRank = new ArrayList<>();
    public static final ArrayList<Player> MythicalGiftRank = new ArrayList<>();
    public static final ArrayList<Player> RefundItem = new ArrayList<>();
    public static final ArrayList<Player> Spectating = new ArrayList<>();


    // public static HashMap<UUID, Integer> banTime = new HashMap<>();

    public static TazPvP instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        System.out.println("Tazpvp Logic is now ONLINE");

        World world = Bukkit.getWorld("grind");
        for(Entity e : world.getEntities()) {
            if (e instanceof Zombie || e instanceof Skeleton){
                e.remove();
                System.out.println("refreshing mobs");
            }
        }

        new ZombieLogic().initZombies(4, 200);
        new ZombieLogic().initSkelton(3);
        new ZombieLogic().initOpSkeleton(2);
        new ZombieLogic().initBaby(1);

        configFile = this.getConfig();
        initConfig();

        TazPvP.AllowBlocks = true;

        statsManager = new StatsManager();
        punishmentManager = new PunishmentManager();
        staffManager = new StaffManager();
        achievementsManager = new AchievementsManager();
        perkManager = new PerkManager();
        ipmanager = new IpManager();
        assistmanager = new AssistManager();

        duelManager = new DuelManager();
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
        // Command Initializer
        registerCommands();

        // Event Register
        registerListeners();

        //getServer().getScheduler().scheduleSyncRepeatingTask(this, combatLog::tick, 20L, 20L);

        new BukkitRunnable() {
            @Override
            public void run() {
                combatLog.tick();
            }
        }.runTaskTimerAsynchronously(this, 20L, 20L);


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
        blocks.add(Material.COAL_ORE);
        blocks.add(Material.IRON_ORE);
        blocks.add(Material.LAPIS_ORE);
        blocks.add(Material.GOLD_ORE);
        blocks.add(Material.DIAMOND_ORE);
        blocks.add(Material.EMERALD_ORE);

        new ChatAlert().Text(this);
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public void load() {
        helpFile = new File(getDataFolder() + "/help.txt");
        ruleFile = new File(getDataFolder() + "/rules.txt");
    }

    public void initVoteKick(Player votekicked){
        TazPvP.votedOut = votekicked;
        TazPvP.VoteOn = true;
        TextComponent VoteYes = new TextComponent(ChatColor.GREEN + " 〡YES");
        VoteYes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/voteyes"));
        TextComponent Voteno = new TextComponent(ChatColor.RED + " 〡NO");
        Voteno.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/voteno"));
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.AQUA + " " + votekicked.getName() + ChatColor.DARK_AQUA + " is being vote-kicked. Click an option below to vote.");
            p.spigot().sendMessage(VoteYes);
            p.spigot().sendMessage(Voteno);

        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (TazPvP.voteYes.size() >= TazPvP.voteNo.size()){
                    Bukkit.broadcastMessage(votekicked.getName() + " has been kicked!");
                    votekicked.kickPlayer("You have been voted out! L");

                } else {
                    Bukkit.broadcastMessage(votekicked.getName() + " wasnt voted out, not enough votes!");
                }
                TazPvP.VoteOn = false;
                TazPvP.votedOut = null;
                TazPvP.voteYes.clear();
                TazPvP.voteNo.clear();
            }
        }.runTaskLater(this, 1200L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("Tazpvp LOGIC is now OFFLINE");

        for (Player p : duelManager.playersFighting) {
            if (ArmorManager.getArmor().containsKey(p.getUniqueId())) {
                ArmorManager.setPlayerContents(p, false);
                //System.out.println("Player " + p.getName() + " has been given their stuff back due to server going brrr during their duel");
            }
        }

        this.saveConfig();

        statsManager.saveStats();
        punishmentManager.savePunishments();
        staffManager.saveStaffFile();
        achievementsManager.saveAchievements();
        perkManager.saveStats();

        TazPvP.VoteOn = false;
        TazPvP.votedOut = null;
        TazPvP.voteYes.clear();
        TazPvP.voteNo.clear();
        TazPvP.renamingSword.clear();

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
        getCommand("shop").setExecutor(new ShopGuiCommand());
        getCommand("hologramz").setExecutor(new HologramsCommand());
        getCommand("tazload").setExecutor(new RestartCommand());
        getCommand("ridepearl").setExecutor(new ridepearlCommand());
        getCommand("firegun").setExecutor(new fireballCommand());
        getCommand("credits").setExecutor(new CreditCommand());
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("votekick").setExecutor(new VotekickCommand());
        getCommand("voteyes").setExecutor(new VoteyesCommand());
        getCommand("voteno").setExecutor(new VotenoCommand());
        //getCommand("lblevel").setExecutor(new lbLevelCommand());
        getCommand("unban").setExecutor(new unbanCommand());
        getCommand("fakeop").setExecutor(new FakeOpCommand());
        getCommand("expL").setExecutor(new ExpCommand());
        getCommand("spawnzombie").setExecutor(new SpawnZombieCommand());
        getCommand("help2").setExecutor(new Help2Command());
        getCommand("unmute").setExecutor(new UnmuteCommand());
        //getCommand("lbd").setExecutor(new lbDeathsCommand());
        //getCommand("lbk").setExecutor(new lbKillsCommand());
        //getCommand("lbm").setExecutor(new lbMoneyCommand());
        getCommand("ridehead").setExecutor(new HeadCommand());
        getCommand("rideme").setExecutor(new RideMeCommand());
        getCommand("giveitem").setExecutor(new giveItem());
        getCommand("trash").setExecutor(new TrashCommand());
        getCommand("saveinv").setExecutor(new SaveinvCommand());
        getCommand("loadinv").setExecutor(new LoadinvCommand());
        getCommand("ac").setExecutor(new ACCommand());
        getCommand("initperks").setExecutor(new initperks());
        getCommand("gmc").setExecutor(new gmcCommand());
        getCommand("gms").setExecutor(new gmsCommand());
        getCommand("gmsp").setExecutor(new gmspCommand());
        getCommand("fly").setExecutor(new flyCommand());
        getCommand("ad").setExecutor(new AdCommand());
        getCommand("nick").setExecutor(new NickCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("tpall").setExecutor(new TpAllCommand());
        getCommand("duel").setExecutor(new DuelCommand());
        getCommand("duelaccept").setExecutor(new DuelAcceptCommand());
        getCommand("kit").setExecutor(new KitCommand());
        getCommand("reloadconfig").setExecutor(new ReloadConfigCommand());
        getCommand("invsee").setExecutor(new invseeCommand());
        getCommand("ec").setExecutor(new enderseeCommand());
        getCommand("spectate").setExecutor(new duelSpectateCommand());
        getCommand("alts").setExecutor(new AltsCommand());
        getCommand("hat").setExecutor(new HatCommand());
        getCommand("pay").setExecutor(new PayCommand());
        getCommand("tp").setExecutor(new TpCommand());
        getCommand("listworld").setExecutor(new ListWorldCommand());
        getCommand("kills").setExecutor(new KillsCommand());
        getCommand("deaths").setExecutor(new DeathsCommand());
        getCommand("pm").setExecutor(new pmCommand());
        getCommand("customhotbar").setExecutor(new CustomHotbarCommand());
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
        getServer().getPluginManager().registerEvents(new SnowballHitEvent(), this);
        getServer().getPluginManager().registerEvents(new MobDropListener(), this);
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new TillingListener(), this);
        getServer().getPluginManager().registerEvents(new RenameSword(), this);
        getServer().getPluginManager().registerEvents(new RankGifting(), this);
        getServer().getPluginManager().registerEvents(new AntiSpamListener(), this);
        getServer().getPluginManager().registerEvents(new FallDamageListener(), this);
        getServer().getPluginManager().registerEvents(new ZombieLogic(), this);
        getServer().getPluginManager().registerEvents(new ParkourListener(), this);
        getServer().getPluginManager().registerEvents(new LevFeather(), this);
        getServer().getPluginManager().registerEvents(new ShootListener(), this);
        getServer().getPluginManager().registerEvents(new WeatherListener(), this);
        getServer().getPluginManager().registerEvents(new RefundManager(), this);
        getServer().getPluginManager().registerEvents(new ArmorTakeOffListener(), this);
        getServer().getPluginManager().registerEvents(new BowListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        getServer().getPluginManager().registerEvents(new CraftListener(), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(), this);
        getServer().getPluginManager().registerEvents(new SpawnCommand(), this);
        getServer().getPluginManager().registerEvents(new WorldGuard(), this);
        getServer().getPluginManager().registerEvents(new DuelManager(), this);
        getServer().getPluginManager().registerEvents(new RCLICKBanListener(), this);
        getServer().getPluginManager().registerEvents(new invseeCommand(), this);
    }

    public void initConfig(){
        configFile.options().copyDefaults(true);
        this.saveConfig();
    }



    public void initScoreboard(Player player) {
        if(!statsManager.scoreboards.containsKey(player.getUniqueId())) {
            statsManager.initScoreboard(player);
        }
        if (TazPvP.punishmentManager.isBanned(player)) {
            Scoreboard sb = statsManager.scoreboards.get(player.getUniqueId());
            Objective objective = sb.getObjective("sb");
            objective.unregister();
            objective = sb.registerNewObjective("sb", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', configFile.getString("branding.sb.name")));
            if (sb.getObjective("showhealth") == null) {
                Objective h = sb.registerNewObjective("showhealth", Criterias.HEALTH);
                h.setDisplaySlot(DisplaySlot.BELOW_NAME);
                h.setDisplayName(ChatColor.RED + "❤");
            }
            Score blank = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "--------------- ");
            blank.setScore(8);
            Score gaming = objective.getScore(" ");
            gaming.setScore(7);
            Score blank222 = objective.getScore(ChatColor.RED + "" + ChatColor.BOLD + "BANNED");
            blank222.setScore(6);
            Score blank2222 = objective.getScore(ChatColor.GRAY +  "﹂/appeal");
            blank2222.setScore(5);
            Score eeee = objective.getScore("");
            eeee.setScore(4);
            //Score tttt = objective.getScore(ChatColor.RED + "Time Left: " + ChatColor.WHITE + (((TazPvP.punishmentManager.getBanDuration(player) - (System.currentTimeMillis()-TazPvP.punishmentManager.getBanTime(player))) / 60000) + 1) + "m");
            //tttt.setScore(4);
            Score blank2 = objective.getScore("  ");
            blank2.setScore(3);
            Score blank4 = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "---------------");
            blank4.setScore(2);
            Score blank5 = objective.getScore(ChatColor.translateAlternateColorCodes('&', configFile.getString("branding.sb.footer")));
            blank5.setScore(1);
            for(Player player1 : Bukkit.getOnlinePlayers()) {
                statsManager.getTeam(player1, sb).addEntry(player1.getName());
            }
            if(permissions.getPrimaryGroup(player).equals("default")) {
                player.setPlayerListName(ChatColor.GRAY + player.getDisplayName());
            } else {
                player.setPlayerListName(ChatColor.translateAlternateColorCodes('&',chat.getGroupPrefix((String) null, permissions.getPrimaryGroup(player)) + player.getDisplayName()));
            }
            player.setScoreboard(sb);
        } else {

            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            Double d = (TazPvP.statsManager.getExpLeft(player));

            Scoreboard sb = statsManager.scoreboards.get(player.getUniqueId());
            Objective objective = sb.getObjective("sb");
            objective.unregister();
            objective = sb.registerNewObjective("sb", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', configFile.getString("branding.sb.name")));
            if (sb.getObjective("showhealth") == null) {
                Objective h = sb.registerNewObjective("showhealth", Criterias.HEALTH);
                h.setDisplaySlot(DisplaySlot.BELOW_NAME);
                h.setDisplayName(ChatColor.RED + "❤");
            }
            Score blank = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "--------------- ");
            blank.setScore(14);
            Score blank1 = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "PLAYER");
            blank1.setScore(13);
            Score level = objective.getScore(ChatColor.DARK_AQUA + "〡 Level  " + ChatColor.GRAY + TazPvP.statsManager.getLevel(player));
            level.setScore(12);
            Score money = objective.getScore(ChatColor.DARK_AQUA + "〡 Money  " + ChatColor.GRAY +  "$"+ TazPvP.statsManager.getMoney(player));
            money.setScore(11);
            Score points = objective.getScore(ChatColor.DARK_AQUA + "〡 Points  " + ChatColor.GRAY + TazPvP.statsManager.getPoints(player));
            points.setScore(10);
            Score exp = objective.getScore(ChatColor.DARK_AQUA + "〡 EXP  " + ChatColor.GRAY + "" + TazPvP.statsManager.getExp(player) + ChatColor.DARK_GRAY + "/" + df.format(d));
            //Score credits = objective.getScore(ChatColor.AQUA + "▷ Credits  " + ChatColor.GRAY + TazPvP.statsManager.getCredits(player));
            exp.setScore(9);
            Score blank2 = objective.getScore("");
            blank2.setScore(8);
            Score blank3 = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "STATS");
            blank3.setScore(7);
            Score streak = objective.getScore(ChatColor.RED + "〡 Streak  " + ChatColor.GRAY + TazPvP.statsManager.getStreak(player));
            streak.setScore(6);
            Score kills = objective.getScore(ChatColor.RED + "〡 Kills  " + ChatColor.GRAY + TazPvP.statsManager.getKills(player));
            kills.setScore(5);
            Score deaths = objective.getScore(ChatColor.RED + "〡 Deaths  " + ChatColor.GRAY + TazPvP.statsManager.getDeaths(player));
            deaths.setScore(4);
            Score kdr = objective.getScore(ChatColor.RED + "〡 KDR  " + ChatColor.GRAY + ((TazPvP.statsManager.getDeaths(player) > 0) ?  MathUtils.round((float) TazPvP.statsManager.getKills(player) / TazPvP.statsManager.getDeaths(player), 2) : TazPvP.statsManager.getKills(player)));
            kdr.setScore(3);
            Score blank4 = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "---------------");
            blank4.setScore(2);
            Score blank5 = objective.getScore(ChatColor.GRAY + "〡 tazpvp.net");
            blank5.setScore(1);
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

    }

    public void setNametag(Player player1, Player player2) {
        Scoreboard scoreboard = player1.getScoreboard();
        if (scoreboard.getTeam("TeamName") != null) {
            scoreboard.getTeam("TeamName").unregister();
        }
        Team team = scoreboard.registerNewTeam("TeamName");
        //team.setPrefix(ChatColor.translateAlternateColorCodes('&',TazPvP.chat.getGroupPrefix((String) null, TazPvP.permissions.getPrimaryGroup(player2)) + player2.getDisplayName()));
        scoreboard.getTeam("TeamName").addPlayer(player2);
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
