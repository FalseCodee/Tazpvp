package net.ntdi.tazpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RulesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage(ChatColor.GRAY + "1. Do not threaten to Dox, DDoS, Swat or IP grab players.");
            p.sendMessage(ChatColor.GRAY + "2. Do not post personal information about others unless given consent.");
            p.sendMessage(ChatColor.GRAY + "3. Do not encourage suicide or threaten to harm others IRL.");
            p.sendMessage(ChatColor.GRAY + "4. Do not post malicious or self advertising links in chat.");
            p.sendMessage(ChatColor.GRAY + "5. Do not advertise any server other than ones on the network.");
            p.sendMessage(ChatColor.GRAY + "6. Do not spam or repeatedly send the same message in chat.");
            p.sendMessage(ChatColor.GRAY + "7. Usage of excessive toxicity, racial slurs, and hate speech is prohibited.");
            p.sendMessage(ChatColor.GRAY + "8. Do not disrespect, impersonate, harass, nor attack staff members in chat.");
            p.sendMessage(ChatColor.GRAY + "9. Do not attempt to evade punishments such as mutes.");
            p.sendMessage(ChatColor.GRAY + "10. Do not post sexual or pornographic content.");
            p.sendMessage(ChatColor.GRAY + "11. Use all channels only for the purpose they were created.");

        }else{
            System.out.println("Only players can execute /rules");
        }
        return true;
    }
}
