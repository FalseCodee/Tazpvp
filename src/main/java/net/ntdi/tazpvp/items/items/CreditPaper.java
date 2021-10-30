package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.TazPvP;
import net.ntdi.tazpvp.items.ConsumableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CreditPaper extends ConsumableItem {

    public CreditPaper() {
        super(Items.CREDITPAPER, 0);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack) {
        if(super.execute(p, itemStack)) {
            return true;
        }

        p.sendMessage(ChatColor.RED + "YOOOOOOOOOOOOOOOOOO CLAIMNIN 50 CWEDITS!!!!!!!!! SO COOOOOOOOOOOLLLLLLLLLLLLLLLLLL");
        TazPvP.statsManager.addCredits(p, 50);

        return false;
    }
}
