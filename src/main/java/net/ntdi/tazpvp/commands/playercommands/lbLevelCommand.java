package net.ntdi.tazpvp.commands.playercommands;

import net.bytebuddy.matcher.HasSuperClassMatcher;
import net.ntdi.tazpvp.TazPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class lbLevelCommand implements CommandExecutor {

    private static HashMap<OfflinePlayer, Integer> hashMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        p.sendMessage(ChatColor.GREEN + "----------");
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            hashMap.put(player, TazPvP.statsManager.getLevel(player));
        }
        List list = new ArrayList(hashMap.values());
        Collections.sort(list);
        for(int i=0; i<10; i++) {
            p.sendMessage(list.toString() + "\n");
        }

        return true;
    }
}
