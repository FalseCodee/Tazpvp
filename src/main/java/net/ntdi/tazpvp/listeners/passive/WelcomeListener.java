package net.ntdi.tazpvp.listeners.passive;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.commands.moderation.BanCommand;
import net.ntdi.tazpvp.listeners.function.DeathListener;
import net.ntdi.tazpvp.managers.ArmorManager;
import net.ntdi.tazpvp.managers.Duels.DuelManager;
import net.ntdi.tazpvp.utils.PlayerUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;
import java.util.UUID;

import static net.ntdi.tazpvp.utils.PlayerUtils.delayChangeGamemode;

public class WelcomeListener implements Listener {

    public TazPvP plugin;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event){

        Player p = event.getPlayer();
        p.sendMessage(ChatColor.AQUA + "");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', TazPvP.configFile.getString("branding.wMSG.line1")));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', TazPvP.configFile.getString("branding.wMSG.line2")));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', TazPvP.configFile.getString("branding.wMSG.line3")));
        p.sendMessage(ChatColor.AQUA + "");

        TazPvP.ipmanager.storePlayerIp(p);

        if (p.getLevel() != TazPvP.statsManager.getLevel(p)){
            p.setLevel(TazPvP.statsManager.getLevel(p));
        }

        World wrld = Bukkit.getWorld(TazPvP.configFile.getString("spawn.world"));

        Location loc = new Location(wrld, 0.5, 51, 0.5, 180, 0);

        p.teleport(loc);

        p.setMetadata("canDamage", new FixedMetadataValue(TazPvP.getInstance(), true));
        p.setMetadata("dueling", new FixedMetadataValue(TazPvP.getInstance(), false));

        if (!TazPvP.statsManager.statsFile.contains(p.getUniqueId().toString()+".playtime")) {
            TazPvP.statsManager.setPlaytime(p, p.getStatistic(Statistic.PLAY_ONE_TICK)/20);
        }

//        AssistManager.assists.put(p.getUniqueId(), new ArrayList<UUID>());
        updateTab();

        for(Scoreboard sb : TazPvP.statsManager.scoreboards.values()) {
            TazPvP.statsManager.getTeam(p, sb).addEntry(p.getName());
        }
        TazPvP.statsManager.initScoreboard(p);
        if(!TazPvP.staffManager.hiddenToggled(p)){
            TazPvP.statsManager.setGroup(p, TazPvP.permissions.getPrimaryGroup(p));
        } else {
            p.sendMessage(ChatColor.DARK_AQUA+ "You are currently hidden!");
        }


        for (Player Onlineplayer : Bukkit.getOnlinePlayers()) {
            TazPvP.getInstance().setNametag(Onlineplayer, p);
        }

        if (TazPvP.punishmentManager.isBanned(p)) {
            p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            p.sendMessage(ChatColor.RED + ""+ ChatColor.BOLD + " BAN" + ChatColor.RED + " You are currently banned.");
            p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        }

        if (!TazPvP.punishmentManager.isBanned(p)){
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String pexcmd = "pex user " + p.getName() + " group remove banned";
            Bukkit.dispatchCommand(console, pexcmd);
        }

        boolean hasPlayed = p.hasPlayedBefore();

        if (hasPlayed) {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + p.getName());
            for (Player Onp : Bukkit.getOnlinePlayers()) {
                if (Onp.hasPermission("tazpvp.ban")) {
                    List<UUID> matchers = TazPvP.ipmanager.findMatchingIp(p);
                    if (matchers != null) {
                        String str = matchers.toString();
                        System.out.println(str);
                        for (UUID uuid : matchers) {
                            if (uuid != p.getUniqueId()) {
                                Onp.sendMessage(ChatColor.RED + "Player " + p.getName() + " has been see on " + Bukkit.getOfflinePlayer(uuid).getName() + ".");
                            }
                        }
                    }
                }
            }
        } else {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "+" + ChatColor.GRAY + "] " + p.getName());
            Player player = event.getPlayer();
            PlayerUtils.equipStarter(p);
        }

        p.spigot().setCollidesWithEntities(true);

//        ProtocolManager protocolManager = TazPvP.getProtocolManager();
//
//        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
//        PacketContainer packet2 = protocolManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
//        PacketContainer packet3 = protocolManager.createPacket(PacketType.Play.Server.NAMED_ENTITY_SPAWN);
//        PacketContainer packet4 = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
//
//        packet.getPlayerInfoAction().write(4, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
//        packet.getPlayerInfoAction().write(3, EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME);
//        packet2.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
//        packet3.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
//
//
//        try {
//            protocolManager.sendServerPacket(p, packet);
//            protocolManager.sendServerPacket(p, packet2);
//            protocolManager.sendServerPacket(p, packet3);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        delayChangeGamemode(p, GameMode.ADVENTURE);
    }

    public String getCombatee(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("combat");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asString();
        }
        return "";
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        for(Scoreboard sb : TazPvP.statsManager.scoreboards.values()) {
            TazPvP.statsManager.getTeam(p, sb).removeEntry(p.getName());
        }
        ArmorManager.setPlayerContents(p, false);
        p.setGameMode(GameMode.ADVENTURE);

        if (TazPvP.duelManager.isDueling(p)){
            new DuelManager().endDuel(p, Bukkit.getPlayer(TazPvP.duelManager.getOpponent(p)));
        }

//        TazPvP.assistmanager.removeAssist(p);

        TazPvP.statsManager.setPlaytime(p, p.getStatistic(Statistic.PLAY_ONE_TICK)/20);

        updateTab();

        p.setMetadata("canDamage", new FixedMetadataValue(TazPvP.getInstance(), true));
        p.setMetadata("dueling", new FixedMetadataValue(TazPvP.getInstance(), false));

        TazPvP.Spectating.remove(p);

        if(combatLog.combatLog.containsKey(p.getUniqueId())){
            Player killer = Bukkit.getPlayer(getCombatee(p));
            if (killer != null) {
                if (killer.isOnline()) {
                    killer.getPlayer().sendMessage(ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + "" + p.getName() + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 5 Experience");
                }
                TazPvP.statsManager.addMoney(killer, 7);
                TazPvP.statsManager.addExp(killer, 8);
                if (TazPvP.statsManager.getMoney(p) > 25) {
                    TazPvP.statsManager.addMoney(p, -25);
                }
                DeathListener.rsInv(p);
                DeathListener.healPlr(p);
            }
        }

        TazPvP.statsManager.scoreboards.remove(p.getUniqueId());
        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + p.getName());
        if(BanCommand.bannedRunnables.containsKey(p.getUniqueId())) {
            BanCommand.bannedRunnables.get(p.getUniqueId()).cancel();
            BanCommand.bannedRunnables.remove(p.getUniqueId());
        }
        p.spigot().setCollidesWithEntities(true);
    }

    @EventHandler
    public void onPlayerWorldChange(PlayerChangedWorldEvent event){
        if (event.getPlayer().getWorld().getName().equals("arena") && !event.getFrom().getName().equals("grind")){
            World world = event.getPlayer().getWorld();
            Player p = event.getPlayer();

            if (DuelManager.spectating.contains(p)){
                return;
            }

            if (TazPvP.invunerable.contains(p)) {
                PlayerUtils.hidePlayer(p);
                p.sendMessage(ChatColor.RED + "Changed world, adding invunerability due to being vanished");
            }

            p.setMetadata("fallDamage", new FixedMetadataValue(TazPvP.getInstance(), false));

            int min = 1;
            int max = 6;

            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);

            if (random_int == 1) {
                Location location = new Location(world, 0, 30, 0);
                p.teleport(location);
            } else if (random_int == 2) {
                Location location = new Location(world, -15, 30, -10);
                p.teleport(location);
            } else if (random_int == 3) {
                Location location = new Location(world, 13, 30, -28);
                p.teleport(location);
            } else if (random_int == 4) {
                Location location = new Location(world, 18, 30, -2);
                p.teleport(location);
            } else if (random_int == 5) {
                Location location = new Location(world, 17, 30, 33);
                p.teleport(location);
            } else {
                Location location = new Location(world, -30, 30, 16);
                p.teleport(location);
            }

            delayChangeGamemode(p, GameMode.SURVIVAL);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.setMetadata("fallDamage", new FixedMetadataValue(TazPvP.getInstance(), true));
                }
            }.runTaskLater(TazPvP.getInstance(), 20 * 20);
//        } else if (event.getFrom().getName().equals("grind")) {
//            World world = event.getPlayer().getWorld();
//            Player p = event.getPlayer();
//            if (!isRespawning(p)) {
//                p.teleport(new Location(Bukkit.getWorld("arena"), -36.5, 30, 3.5));
//            }
        } else if (event.getPlayer().getWorld().getName().equals("duel")) {
            if (!TazPvP.duelManager.isDueling(event.getPlayer())) {
                delayChangeGamemode(event.getPlayer(), GameMode.SPECTATOR);
            }
        }
    }

    public void updateTab(){
        for(Player player : Bukkit.getOnlinePlayers()) {
            TazPvP.sendTablistHeaderAndFooter(player,
                    ChatColor.translateAlternateColorCodes('&', TazPvP.configFile.getString("branding.tablist.header")) + "\n" + ChatColor.DARK_GRAY +"                                        ",
                    ChatColor.DARK_GRAY +"                                        \n"
                            +ChatColor.translateAlternateColorCodes('&', TazPvP.configFile.getString("branding.tablist.footer")) + "\n"
                            +ChatColor.AQUA+Bukkit.getOnlinePlayers().size() + ChatColor.GRAY+"/" + ChatColor.DARK_AQUA + "75");
        }
    }

    public boolean isFallDamageImmune(Block b){
        List<MetadataValue> metaDataValues = b.getMetadata("fallDamage");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }

    public boolean isRespawning(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("respawning");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }
}
