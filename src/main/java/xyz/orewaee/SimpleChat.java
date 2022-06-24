package xyz.orewaee;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleChat extends JavaPlugin {
    private static SimpleChat instance;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults( true );
        saveDefaultConfig();

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents( new ChatEvent(), this );
    }

    public static SimpleChat getInstance() {
        return instance;
    }
}
