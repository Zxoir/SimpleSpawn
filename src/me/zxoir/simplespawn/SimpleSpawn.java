/**
 *
 * Created by Zxoir 4/24/2020 3:37 PM
 *
 */
package me.zxoir.simplespawn;

import me.zxoir.simplespawn.commands.SetSpawn;
import me.zxoir.simplespawn.commands.Spawn;
import me.zxoir.simplespawn.listener.MoveListener;
import me.zxoir.simplespawn.utilities.DataFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleSpawn extends JavaPlugin {
    private DataFile dataFile;

    @Override
    public void onEnable() {
        /* Config related */
        saveDefaultConfig();
        dataFile = new DataFile(this);
        dataFile.setup();

        /* Register Listener */
        getServer().getPluginManager().registerEvents(new MoveListener(this), this);

        /* Register Command */
        getCommand("Spawn").setExecutor(new Spawn(this));
        getCommand("setspawn").setExecutor(new SetSpawn(this));

        /* Console Sender */
        getServer().getConsoleSender().sendMessage("SimpleSpawn by Zxoir has been enabled!");
    }

    @Override
    public void onDisable() {
        /* Console Sender */
        getServer().getConsoleSender().sendMessage("SimpleSpawn by Zxoir has been enabled!");
    }

    public DataFile getDataFile() {
        return dataFile;
    }
}
