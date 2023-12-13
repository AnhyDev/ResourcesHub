package ink.anh.example;

import java.util.Arrays;
import java.util.Locale;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import ink.anh.api.lingo.Translator;
import ink.anh.api.messages.MessageType;
import ink.anh.api.messages.Messenger;
import ink.anh.api.player.PlayerData;
import ink.anh.api.utils.LangUtils;

// Implements CommandExecutor to handle commands for the plugin.
public class ExampleCommand implements CommandExecutor {
    
    // Reference to the main plugin instance.
    private ExamplePlugin plugin;

    // Reference to the global manager.
    private GlobalManager globalManager;

    // Constructor initializing plugin and global manager.
    public ExampleCommand(ExamplePlugin plugin) {
        this.plugin = plugin;
        this.globalManager = plugin.getManager();
    }

    // Handles command execution.
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "reload":
                    return reload(sender); // Handles the 'reload' command.
                default:
                    return false; // Returns false if the command is not recognized.
            }
        }
        return false; // Returns false if no arguments are provided.
    }

    // Handles the 'reload' command.
    private boolean reload(CommandSender sender) {
        String[] langs = checkPlayerPermissions(sender, "exampleplugin.reload");
        if (langs != null && langs[0] == null) {
            return true;
        }
        
        if (plugin.getManager().reload()) {
            sendMessage(sender, Translator.translateKyeWorld(globalManager, "language_reloaded", langs), MessageType.NORMAL);
            return true;
        }
        return false;
    }

    // Checks if the player has the required permissions to execute the command.
    private String[] checkPlayerPermissions(CommandSender sender, String permission) {
        if (sender instanceof ConsoleCommandSender) {
            return null; // Console can always execute the command.
        }

        String[] langs = new String[]{null};

        if (sender instanceof Player) {
            Player player = (Player) sender;
            langs = LangUtils.getPlayerLanguage(player); // Gets the player's language.

            if (!player.hasPermission(permission)) {
                // Inform the player if they lack the required permission.
                sendMessage(sender, Translator.translateKyeWorld(globalManager, "err_not_have_permission", langs), MessageType.ERROR);
                return langs;
            }
        }

        return langs;
    }

    // Sends a message to the command sender.
    private void sendMessage(CommandSender sender, String message, MessageType type) {
        Messenger.sendMessage(globalManager, sender, message, type);
    }
}
