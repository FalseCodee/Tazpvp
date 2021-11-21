package net.ntdi.tazpvp.commands.playercommands;

import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class lbDeathsCommand implements CommandExecutor {

    //private static final HashMap<UUID, Integer> unsortMap = new HashMap<>();
    public static final boolean DESC = false;

    public HashMap<UUID, Long> cooldown = new HashMap<>();
    public int cooldownTime = 10;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TOP " + ChatColor.AQUA + "" + ChatColor.BOLD + "ONLINE" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " DEATHS");
        p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        HashMap<UUID, Integer> unsortMap = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            unsortMap.put(player.getUniqueId(), TazPvP.statsManager.getDeaths(player));
        }
        Map<UUID, Integer> sortedMapDesc = sortByComparator(unsortMap, DESC);
        printMap(sortedMapDesc, p);
        return true;
    }

    private static Map<UUID, Integer> sortByComparator(Map<UUID, Integer> unsortMap, final boolean order)
    {

        List<Map.Entry<UUID, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> {
            if (order) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<UUID, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<UUID, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void printMap(Map<UUID, Integer> map, Player p)
    {
        int times = 1;
        for (Map.Entry<UUID, Integer> entry : map.entrySet())
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
                p.sendMessage(ChatColor.GOLD + "#" + times + " " + Bukkit.getOfflinePlayer(entry.getKey()).getName() + ": " + ChatColor.GREEN + entry.getValue());
                times++;
            }
        }
    }
}
