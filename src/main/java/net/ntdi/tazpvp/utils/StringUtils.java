package net.ntdi.tazpvp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class StringUtils {
    public static String buildString(String[] args, int start) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = start; i < args.length; i++) {
            stringBuilder.append(args[i]).append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.toString().length()-1);
    }
    public static String dateToString(long unix) {
        Date date = new Date(unix);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        format.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        return format.format(date);
    }
    public static String secondsToDDHHMMSS(long seconds) {
        return String.format("%02dd %02dh %02dm %02ds", seconds / 86400, (seconds / 3600 % 24), (seconds / 60) % 60, seconds % 60);
    }

    public static String secondsToHHMMSS(long seconds) {
        return String.format("%02dh %02dm %02ds", seconds / 3600, (seconds / 60) % 60, seconds % 60);
    }
}
