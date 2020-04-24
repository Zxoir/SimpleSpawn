package me.zxoir.simplespawn.listeners;

import me.zxoir.simplespawn.SimpleSpawn;
import me.zxoir.simplespawn.utilities.TeleportationTimer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.zxoir.simplespawn.utilities.ColorUtility.Color;

public class MoveListener implements Listener {
    public final SimpleSpawn plugin;

    public MoveListener(SimpleSpawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (TeleportationTimer.countdownTimer.containsKey(player)) {
            /* If the player is teleporting */

            if (e.getFrom() != e.getTo()) {
                /* If the player moved */
                if (!this.plugin.getConfig().getString("Cancel Message").equals("")) {
                    String message = Color(this.plugin.getConfig().getString("Cancel Message"));
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                }

                CancelTeleportation(player);

            }

        }

    }

    public void CancelTeleportation(Player player) {
        Bukkit.getScheduler().cancelTask(TeleportationTimer.countdown.get(player));
        TeleportationTimer.countdownTimer.remove(player);
        TeleportationTimer.countdown.remove(player);
    }

}
