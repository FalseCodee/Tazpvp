package net.ntdi.tazpvp.listeners.function;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatAlert {
    int num = 1;
    public void Text(Plugin plugin){
        new BukkitRunnable(){
            @Override
            public void run() {
                if (num > 5){
                    num = 1;
                }
                Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                switch(num){
                    case 1:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "You can join our community by typing " + ChatColor.DARK_AQUA + "/discord");
                        break;
                    case 2:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "You can check out our great deals by typing " + ChatColor.DARK_AQUA + "/buy");
                        break;
                    case 3:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "Want to apply for a staff position? type " + ChatColor.DARK_AQUA + "/apply");
                        break;
                    case 4:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "Want to support us? get our advertisement with " + ChatColor.DARK_AQUA + "/ad");
                        break;
                    case 5:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "Think they're hacking? Report them with " + ChatColor.DARK_AQUA + "/report");
                        break;
                }
                Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

                num++;
            }
        }.runTaskTimer(plugin, 20*4*60, 20*4*60);
    }
}

