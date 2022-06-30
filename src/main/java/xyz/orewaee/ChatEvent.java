package xyz.orewaee;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler
    public void eventHandler( AsyncPlayerChatEvent event ) {
        Player player = event.getPlayer();

        String name = player.getName();
        String message = event.getMessage();

        String prefix = SimpleChat.getInstance().getConfig().getString( "prefix" );

        if ( message.startsWith( prefix ) ) {
            String globalMessageTemplate = SimpleChat.getInstance().getConfig().getString( "global-message" );

            String globalMessage = globalMessageTemplate
                    .replace( "%name%", name )
                    .replace( "%message%", message.substring( 1 ) );

            Bukkit.getOnlinePlayers().forEach( p -> p.sendMessage( Utils.colorize( globalMessage ) ) );
        } else {
            String localMessageTemplate = SimpleChat.getInstance().getConfig().getString( "local-message" );

            String localMessage = localMessageTemplate
                    .replace( "%name%", name )
                    .replace( "%message%", message );

            int radius = SimpleChat.getInstance().getConfig().getInt( "radius" );

            Utils.getPlayersNearby( player, radius ).forEach( p -> p.sendMessage( Utils.colorize( localMessage ) ) );
        }

        event.setCancelled( true );
    }
}
