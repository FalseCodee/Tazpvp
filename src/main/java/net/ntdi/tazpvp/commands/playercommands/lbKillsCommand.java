package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.ntdi.tazpvp.commands.playercommands.lbDeathsCommand;

import java.util.*;

public class lbKillsCommand implements CommandExecutor {

    private static final HashMap<OfflinePlayer, Integer> unsortMap = new HashMap<>();
    public static final boolean DESC = false;

    lbDeathsCommand lbDeaths = new lbDeathsCommand();



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        if(lbDeaths.cooldown.containsKey(p.getUniqueId())){
            long secondsLeft = lbDeaths.cooldown.get(p.getUniqueId())-System.currentTimeMillis();
            if(secondsLeft>0) {
                p.sendMessage(ChatColor.RED + "You must wait " + secondsLeft/1000 + " seconds before using this command again.");
            } else {
                lbDeaths.cooldown.remove(p.getUniqueId());
                p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TOP " + ChatColor.AQUA + "" + ChatColor.BOLD + "KILLS");
                p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                    unsortMap.put(player, TazPvP.statsManager.getKills(player));
                }
                Map<OfflinePlayer, Integer> sortedMapDesc = sortByComparator(unsortMap, DESC);
                printMap(sortedMapDesc, p);
            }
        }
        lbDeaths.cooldown.put(p.getUniqueId(), System.currentTimeMillis() + (lbDeaths.cooldownTime * 1000L));
        return true;
    }

    private static Map<OfflinePlayer, Integer> sortByComparator(Map<OfflinePlayer, Integer> unsortMap, final boolean order)
    {

        List<Map.Entry<OfflinePlayer, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> {
            if (order) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<OfflinePlayer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<OfflinePlayer, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void printMap(Map<OfflinePlayer, Integer> map, Player p)
    {
        int times = 1;
        for (Map.Entry<OfflinePlayer, Integer> entry : map.entrySet())
        {
            if (times >= 11){
                return;
            } else {
//                if (entry.getKey().getPlayer().hasPermission("rank.mvpp")){
//                    p.sendMessage(ChatColor.GOLD + "#" + times + " " + ChatColor.BLUE +  entry.getKey().getName() + ": " + ChatColor.GREEN + entry.getValue());
//                } else if (entry.getKey().getPlayer().hasPermission("rank.mvp")) {
//                    p.sendMessage(ChatColor.GOLD + "#" + times + " " + ChatColor.GOLD + entry.getKey().getName() + ": " + ChatColor.GREEN + entry.getValue());
//                } else if (entry.getKey().getPlayer().hasPermission("rank.vip")) {
//                    p.sendMessage(ChatColor.GOLD + "#" + times + " " + ChatColor.RED + entry.getKey().getName() + ": " + ChatColor.GREEN + entry.getValue());
//                } else  {
//                    p.sendMessage(ChatColor.GOLD + "#" + times + " " + ChatColor.GRAY + entry.getKey().getName() + ": " + ChatColor.GREEN + entry.getValue());
//                }
                p.sendMessage(ChatColor.GOLD + "#" + times + " " + ChatColor.GRAY + entry.getKey().getName() + ": " + ChatColor.GREEN + entry.getValue());
                times++;
            }
        }
    }
}
