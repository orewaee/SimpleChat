package xyz.orewaee.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.orewaee.SimpleChat;
import xyz.orewaee.Utils;

public class SimpleChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand( @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args ) {
        if ( args.length == 0 ) return false;

        if ( args[0].equalsIgnoreCase( "reload" ) ) {
            SimpleChat.getInstance().reloadConfig();

            sender.sendMessage( Utils.colorize( "#8ac926Config successfully reloaded&r" ) );

            return true;
        }

        return true;
    }
}
