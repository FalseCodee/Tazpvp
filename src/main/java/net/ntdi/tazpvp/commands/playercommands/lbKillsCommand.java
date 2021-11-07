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

    private static final HashMap<Player, Integer> unsortMap = new HashMap<>();
    public static final boolean DESC = false;

    lbDeathsCommand lbDeaths = new lbDeathsCommand();


    public HashMap<UUID, Long> cooldown = new HashMap<>();
    public int cooldownTime = 10;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TOP " + ChatColor.AQUA + "" + ChatColor.BOLD + "ONLINE" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " KILLS");
        p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        for (Player player : Bukkit.getOnlinePlayers()) {
            unsortMap.put(player, TazPvP.statsManager.getKills(player));
        }
        Map<Player, Integer> sortedMapDesc = sortByComparator(unsortMap, DESC);
        printMap(sortedMapDesc, p);
        return true;
    }

    private static Map<Player, Integer> sortByComparator(Map<Player, Integer> unsortMap, final boolean order)
    {

        List<Map.Entry<Player, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> {
            if (order) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Player, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Player, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void printMap(Map<Player, Integer> map, Player p)
    {
        int times = 1;
        for (Map.Entry<Player, Integer> entry : map.entrySet())
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
