package net.ntdi.tazpvp.utils;

public class StringUtils {
    public static String buildString(String[] args, int start) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = start; i < args.length; i++) {
            stringBuilder.append(args[i]).append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.toString().length()-1);
    }
}
