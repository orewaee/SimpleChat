package xyz.orewaee;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
    public static String colorize( String text ) {
        Pattern pattern = Pattern.compile( "#[a-fA-F0-9]{6}" );

        Matcher matcher = pattern.matcher( text );

        while ( matcher.find() ) {
            String color = text.substring( matcher.start(), matcher.end() );

            text = text.replace( color, ChatColor.of( color ) + "" );

            matcher = pattern.matcher( text );
        }

        return ChatColor.translateAlternateColorCodes( '&', text );
    }

    public static List<Player> getPlayersNearby( Player player, int radius ) {
        return Bukkit.getOnlinePlayers().parallelStream()
            .filter( p -> p.getWorld().equals( player.getWorld() ) && p.getLocation().distanceSquared( player.getLocation() ) <= radius * radius )
            .collect( Collectors.toList() );
    }
}
