package me.zxoir.simplespawn.commands;

import me.zxoir.simplespawn.SimpleSpawn;
import me.zxoir.simplespawn.utilities.DataFile;
import me.zxoir.simplespawn.utilities.TeleportationUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.zxoir.simplespawn.utilities.ColorUtility.Color;

public class Spawn implements CommandExecutor {
    public final SimpleSpawn plugin;
    private DataFile dataFile;

    public Spawn(SimpleSpawn plugin) {
        this.plugin = plugin;
        this.dataFile = plugin.getDataFile();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player) {
            /* If the sender is a player */
            Player player = (Player) sender;

            if (this.dataFile.getConfig().getConfigurationSection("Spawn") != null)
                /* If there is a saved spawn */
                new TeleportationUtility(this.plugin, player);

            else
                /* If there is not a saved spawn */
                player.sendMessage(Color("&cThere is no set spawn, please contact an Admin to set one."));

        } else
            /* If the sender is not a player */
            sender.sendMessage("Only players can execute this command.");

        return true;

    }

}
