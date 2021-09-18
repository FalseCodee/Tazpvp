//package net.ntdi.myfirstplugin.commands;
//
//import org.bukkit.ChatColor;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import java.util.HashMap;
//import java.util.Map;
//
//public class VanishCommand implements CommandExecutor {
//
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
//        Map<String, Boolean> VanishedPlayers = new HashMap<String, Boolean>();
//
//        if (sender instanceof Player){
//            Player p = (Player) sender;
//
//            if (VanishedPlayers.containsKey(p.getName())){
//                if (VanishedPlayers.get(p.getName()) == false) {
//                    p.setInvulnerable(true);
//                    p.setInvisible(true);
//                    p.setFlying(true);
//                    p.sendMessage(ChatColor.DARK_BLUE + "Vanish mode enabled");
//                    VanishedPlayers.remove(p.getName());
//                    VanishedPlayers.put(p.getName(), true);
//                } else {
//                    p.setInvulnerable(false);
//                    p.setInvisible(false);
//                    p.setFlying(false);
//                    p.sendMessage(ChatColor.BLUE + "Vanish mode disabled");
//                    VanishedPlayers.remove(p.getName());
//                    VanishedPlayers.put(p.getName(), false);
//                }
//            }
//        }
//
//        return true;
//    }
//
//}
//
