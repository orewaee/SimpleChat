package xyz.orewaee;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.orewaee.commands.SimpleChatCommand;

public class SimpleChat extends JavaPlugin {
    private static SimpleChat instance;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults( true );
        saveDefaultConfig();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents( new ChatEvent(), this );

        getCommand( "simplechat" ).setExecutor( new SimpleChatCommand() );
    }

    public static SimpleChat getInstance() {
        return instance;
    }
}
