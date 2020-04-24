package me.zxoir.simplespawn.utilities;

import me.zxoir.simplespawn.SimpleSpawn;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

import static me.zxoir.simplespawn.utilities.ColorUtility.Color;

public class TeleportationTimer extends BukkitRunnable {
    public static HashMap<Player, Integer> countdown = new HashMap();
    public static HashMap<Player, Integer> countdownTimer = new HashMap();
    Player player;
    SimpleSpawn plugin;
    DataFile dataFile;

    public TeleportationTimer(SimpleSpawn plugin, Player player) {
        this.player = player;
        this.plugin = plugin;
        this.dataFile = plugin.getDataFile();
    }

    @Override
    public void run() {

        if (!countdown.containsKey(player)) {
            /* Starts the timer */
            countdown.put(player, this.getTaskId());
            countdownTimer.put(player, this.plugin.getConfig().getInt("Time"));

        }

        int getTime = countdownTimer.get(player);

        if (getTime == 0) {
            /* If the timer reaches 0 */
            Teleport(player);
            CancelTeleportation(player);

            return;

        }

        if (!plugin.getConfig().getString("Teleporting Sound").equals("")) {

            try {
                Sound teleportationSound = Sound.valueOf(plugin.getConfig().getString("Teleporting Sound"));
                player.playSound(player.getLocation(), teleportationSound, 10, 0);

            } catch (Exception e) {
                new Alert("Please check the 'Teleporting Sound' config for errors!");

            }

        }

        countdownTimer.put(player, getTime - 1);

        if (!this.plugin.getConfig().getString("Actionbar Message").equals("")) {
            String message = Color(this.plugin.getConfig().getString("Actionbar Message").replace("<time>", String.valueOf(getTime)));
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }

    }

    public void CancelTeleportation(Player player) {
        Bukkit.getScheduler().cancelTask(this.getTaskId());
        countdownTimer.remove(player);
        countdown.remove(player);
    }

    public void Teleport(Player player) {
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
