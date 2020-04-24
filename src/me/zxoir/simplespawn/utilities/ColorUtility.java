package me.zxoir.simplespawn.utilities;

import org.bukkit.ChatColor;

public class ColorUtility {

    public static String Color(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
