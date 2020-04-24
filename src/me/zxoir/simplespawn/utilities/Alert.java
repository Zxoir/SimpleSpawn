package me.zxoir.simplespawn.utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.zxoir.simplespawn.utilities.ColorUtility.Color;

public class Alert {

    public Alert(String s) {
        Bukkit.getConsoleSender().sendMessage("\n--------------------------\n" +
                ">> SimpleSpawn encountered an error!\n" +
                ">> How to Fix: " + s +
                "\n--------------------------");

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.isOp())
                online.sendMessage(Color("&8&m--------------------------\n" +
                        "&7>> &9SimpleSpawn encountered an error!\n" +
                        "&7>> &9How to Fix: &a" + s +
                        "\n&8&m--------------------------"));

        }

    }

}
