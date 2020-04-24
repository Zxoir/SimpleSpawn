package me.zxoir.simplespawn.utilities;

import me.zxoir.simplespawn.SimpleSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import static me.zxoir.simplespawn.utilities.ColorUtility.Color;

public class TeleportationUtility {
    public final SimpleSpawn plugin;
    private DataFile dataFile;

    public TeleportationUtility(SimpleSpawn plugin, Player player) {
        this.plugin = plugin;
        this.dataFile = plugin.getDataFile();

        if (this.plugin.getConfig().getInt("Time") != 0) {
            /* If timer is enabled */
            new TeleportationTimer(this.plugin, player).runTaskTimer(this.plugin, 0L, 20L);

        } else {
            /* If timer is disabled */
            player.teleport(GetSpawnLocation());

            if (!this.plugin.getConfig().getString("Teleport Message").equals("")) {
                String message = this.plugin.getConfig().getString("Teleport Message");
                player.sendMessage(Color(message));
            }

            if (!plugin.getConfig().getString("Teleport Sound").equals("")) {

                try {
                    Sound teleportationSound = Sound.valueOf(plugin.getConfig().getString("Teleport Sound"));
                    player.playSound(player.getLocation(), teleportationSound, 10, 0);

                } catch (Exception e) {
                    new Alert("Please check the 'Teleport Sound' config for errors!");

                }

            }

        }

    }

    public Location GetSpawnLocation() {
        World world = Bukkit.getWorld(this.dataFile.getConfig().getString("Spawn.World"));
        double x = this.dataFile.getConfig().getDouble("Spawn.X");
        double y = this.dataFile.getConfig().getDouble("Spawn.Y");
        double z = this.dataFile.getConfig().getDouble("Spawn.Z");
        float yaw = (float) this.dataFile.getConfig().getDouble("Spawn.Yaw");
        float pitch = (float) this.dataFile.getConfig().getDouble("Spawn.Pitch");

        return new Location(world, x, y, z, yaw, pitch);
    }

}
