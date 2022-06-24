package xyz.orewaee;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    private final FileConfiguration config = SimpleChat.getInstance().getConfig();

    @EventHandler
    public void eventHandler( AsyncPlayerChatEvent event ) {
        Player player = event.getPlayer();

        String name = player.getName();
        String message = event.getMessage();

        if ( message.startsWith( config.getString( "prefix" ) ) ) {
            String globalMessage = config.getString( "global-message" )
                .replace( "%name%", name )
                .replace( "%message%", message.substring( 1 ) );

            Bukkit.getOnlinePlayers().forEach( p -> p.sendMessage( Utils.colorize( globalMessage ) ) );
        } else {
            String localMessage = config.getString( "local-message" )
                .replace( "%name%", name )
                .replace( "%message%", message );

            Utils.getPlayersNearby( player, config.getInt( "radius" ) ).forEach( p -> p.sendMessage( Utils.colorize( localMessage ) ) );
        }

        event.setCancelled( true );
    }

}
