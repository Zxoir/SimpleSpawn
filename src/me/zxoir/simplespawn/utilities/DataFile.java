package me.zxoir.simplespawn.utilities;

import me.zxoir.simplespawn.SimpleSpawn;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFile {
    public SimpleSpawn plugin;
    public FileConfiguration playerscfg;
    public File playersfile;

    public DataFile(SimpleSpawn plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }

        this.playersfile = new File(this.plugin.getDataFolder(), "DataFile.yml");

        if (!this.playersfile.exists()) {
            try {
                this.playersfile.createNewFile();
                this.plugin.getServer().getConsoleSender().sendMessage("the DataFile.yml file has been created!");
            } catch (IOException e) {
                this.plugin.getServer().getConsoleSender().sendMessage("Could not create the DataFile.yml file");
            }
        }

        this.playerscfg = YamlConfiguration.loadConfiguration(this.playersfile);
    }

    public FileConfiguration getConfig() {
        return this.playerscfg;
    }

    public void saveConfig() {
        try {
            this.playerscfg.save(this.playersfile);
        } catch (IOException localIOException) {
        }
    }

    public void reloadConfig() {
        this.playerscfg = YamlConfiguration.loadConfiguration(this.playersfile);
    }
}
