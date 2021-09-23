package net.ntdi.tazpvp.items.items;

import net.ntdi.tazpvp.items.ClickableItem;
import net.ntdi.tazpvp.items.Items;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class GrapplingHook extends ClickableItem {

    public GrapplingHook() {
        super(Items.GRAPPLING_HOOK);
    }

    public void onReel(Player player, Location location){
        pullEntityToLocation(player, location);
    }

    //https://github.com/snowgears/Grappling-Hook/blob/master/src/main/java/com/snowgears/grapplinghook/GrapplingListener.java#L413
    private void pullEntityToLocation(final Entity e, Location loc){
        Location entityLoc = e.getLocation();

        entityLoc.setY(entityLoc.getY()+0.5);
        e.teleport(entityLoc);

        double g = -0.08;
        double t = loc.distance(entityLoc);
        double v_x = (1.0+0.07*t) * (loc.getX()-entityLoc.getX())/t;
        double v_y = (1.0+0.03*t) * (loc.getY()-entityLoc.getY())/t -0.5*g*t;
        double v_z = (1.0+0.07*t) * (loc.getZ()-entityLoc.getZ())/t;

        Vector v = e.getVelocity();
        v.setX(v_x);
        v.setY(v_y);
        v.setZ(v_z);
        e.setVelocity(v);
    }
}
