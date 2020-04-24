package me.zxoir.simplespawn.listeners;

import me.zxoir.simplespawn.SimpleSpawn;
import me.zxoir.simplespawn.utilities.DataFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class TeleportListener implements Listener {
    public SimpleSpawn plugin;
    private DataFile dataFile;

    public TeleportListener(SimpleSpawn plugin) {
        this.plugin = plugin;
        this.dataFile = plugin.getDataFile();
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (this.plugin.getConfig().getBoolean("Teleport on spawn")) {
            /* If teleport on spawn is enabled */
            player.teleport(GetSpawnLocation());

        }

    }

    @EventHandler
    public void OnSpawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (this.plugin.getConfig().getBoolean("Teleport on death")) {
            /* If teleport on death is enabled */
            player.teleport(GetSpawnLocation());
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
