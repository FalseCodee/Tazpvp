//package net.ntdi.tazpvp.commands.moderation;

//public class SetSpawnCommand implements CommandExecutor {
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        Player player = null;
//        if(sender instanceof Player){
//            player = (Player) sender;
            //       }
//
//        if(player != null && player.hasPermission("staff.setspawn")){
//            Location location = player.getLocation();
//
//            TazPvP.configFile.set("spawn.x", location.getX());
//            TazPvP.configFile.set("spawn.y", location.getY());
//            TazPvP.configFile.set("spawn.z", location.getZ());
//            TazPvP.configFile.set("spawn.pitch", location.getPitch());
//            TazPvP.configFile.set("spawn.yaw", location.getYaw());
//            TazPvP.configFile.set("spawn.world", location.getWorld().getName());
//            TazPvP.getInstance().saveConfig();
//        }
//        return true;
//    }
//}
