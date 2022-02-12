package net.ntdi.tazpvp.listeners.function;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class RCLICKBanListener implements Listener {

    @EventHandler
    public void onRclick(PlayerInteractEntityEvent e) {
        if (e.getPlayer().hasPermission("tazpvp.staff.banclick")) {
            if (e.getRightClicked().getType().equals(EntityType.PLAYER) && e.getPlayer().isSneaking()) {
                Player p = (Player) e.getPlayer();
                Player target = (Player) e.getRightClicked();

                if (target instanceof Player) {
                    if (!target.hasPermission("staff.banbypass")){
                        TextComponent banmsg = new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + "Click Here to confirm the ban.");
                        banmsg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ban " + target.getName()));
                        banmsg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent(ChatColor.GREEN + "CLICK TO CONFIRM BAN")}));

                        p.sendMessage("");
                        p.spigot().sendMessage(banmsg);
                        p.sendMessage("");
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    }
                }
            }
        }
    }

//    public void doBan(Player p, Player target){
//        if (!target.hasPermission("staff.banbpyass")) {
//            if(TazPvP.punishmentManager.isBanned(target)){
//                p.sendMessage(ChatColor.RED + "Player is already banned");
//            } else {
//                if (target.isOnline()) {
//                    target.getPlayer().sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
//                    target.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BAN" + ChatColor.GRAY + " You've been banned for "+ChatColor.WHITE+"Unfair Advantage");
//                    target.getPlayer().sendMessage(ChatColor.GRAY + "Do not log out if you wish to be unbanned. Join the discord and create a ticket.");
//                    target.getPlayer().sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
//
//                    new BukkitRunnable() {
//
//                        @Override
//                        public void run() {
//                            TazPvP.getInstance().initScoreboard(target.getPlayer());
//                        }
//                    }.runTaskLater(TazPvP.getInstance(), 20L);
//
//                }
//                TazPvP.punishmentManager.initBan(target, true, 60*60*1000);
//
//                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
//                String pexcmd = "pex user " + target.getName() + " group add banned";
//                Bukkit.dispatchCommand(console, pexcmd);
//
//                for (Player player : Bukkit.getOnlinePlayers()){
//                    if (!Objects.equals(p.getName(), target.getName())){
//                        player.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
//                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BAN " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " has been punished for " + ChatColor.WHITE + "Unfair Advantage");
//                        player.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
//                    }
//                }
//
//
//
////                        BukkitRunnable runnable = getBanRunnable(banned);
////                        runnable.runTaskTimer(TazPvP.getInstance(), 0L, 1200L);
////                        bannedRunnables.put(banned.getUniqueId(), runnable);
//
//                JSONObject obj = new JSONObject();
//                JSONArray embed = new JSONArray();
//                JSONObject embedObj = new JSONObject();
//                embed.add(embedObj);
//
//                embedObj.put("title", target.getName());
//                embedObj.put("color", 16077890);
//
//                JSONArray fields = new JSONArray();
//                JSONObject fieldBanned = new JSONObject();
//                fieldBanned.put("name", "Ban Info");
//                fieldBanned.put("value", "**Banned Account:** " + target.getName()+"\n**Reason:** " + "Unfair Advantage");
//                JSONObject fieldOther = new JSONObject();
//                fieldOther.put("name", "Other Info");
//                fieldOther.put("value", "**Banner:** " + p.getName()+"\n**Time:** " + StringUtils.dateToString(System.currentTimeMillis()));
//                fields.add(fieldBanned);
//                fields.add(fieldOther);
//                embedObj.put("fields", fields);
//                obj.put("embeds", embed);
//                obj.put("username", "Banner");
//                obj.put("avatar_url", "");
//                try {
//                    PostHelper.postRequest(TazPvP.configFile.getString("webhooks.bans"), obj.toString());
//                } catch (IOException err) {
//                    err.printStackTrace();
//                    p.sendMessage(ChatColor.RED + "Webhook failed, please contact an admin");
//                }
//
//            }
//        }
//    }
}
