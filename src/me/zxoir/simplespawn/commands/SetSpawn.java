package me.zxoir.simplespawn.commands;

import me.zxoir.simplespawn.SimpleSpawn;
import me.zxoir.simplespawn.utilities.DataFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.zxoir.simplespawn.utilities.ColorUtility.Color;

public class SetSpawn implements CommandExecutor {
    public SimpleSpawn plugin;
    private DataFile dataFile;

    public SetSpawn(SimpleSpawn plugin) {
        this.plugin = plugin;
        this.dataFile = plugin.getDataFile();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            /* If the sender is a player */
            Player player = (Player) sender;

            if (player.hasPermission("simplespawn.admin")) {
                /* If the player has the correct permission */
                RegisterSpawn(player.getLocation());
                player.sendMessage(Color("&9Spawn &8> &7You have set the new spawn location."));

            } else
                /* If the player does not have the correct permission */
                player.sendMessage(Color("&cYou don't have permission to use this command."));

        } else
            /* If the sender is not a player */
            sender.sendMessage("Only players can execute this command.");

        return true;

    }

    public void RegisterSpawn(Location location) {
        this.dataFile.getConfig().set("Spawn.World", location.getWorld().getName());
        this.dataFile.getConfig().set("Spawn.X", location.getX());
        this.dataFile.getConfig().set("Spawn.Y", location.getY());
        this.dataFile.getConfig().set("Spawn.Z", location.getZ());
        this.dataFile.getConfig().set("Spawn.Yaw", location.getYaw());
        this.dataFile.getConfig().set("Spawn.Pitch", location.getPitch());

        this.dataFile.saveConfig();
    }

}
