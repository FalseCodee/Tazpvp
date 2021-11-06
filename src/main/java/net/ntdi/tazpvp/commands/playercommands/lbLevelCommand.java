package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class lbLevelCommand implements CommandExecutor {

    private static final HashMap<OfflinePlayer, Integer> unsortMap = new HashMap<>();
    public static final boolean DESC = false;
    public static int times = 1;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        p.sendMessage(ChatColor.GREEN + "----------");
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            unsortMap.put(player, TazPvP.statsManager.getLevel(player));
        }

        Map<OfflinePlayer, Integer> sortedMapDesc = sortByComparator(unsortMap, DESC);
        printMap(sortedMapDesc, p);
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
        for (Map.Entry<OfflinePlayer, Integer> entry : map.entrySet())
        {
            if (times >= 11){
                return;
            } else {
                p.sendMessage(ChatColor.GOLD + "#" + times + " " +  entry.getKey().getName() + " " + entry.getValue());
                times++;
            }


        }
    }
}
