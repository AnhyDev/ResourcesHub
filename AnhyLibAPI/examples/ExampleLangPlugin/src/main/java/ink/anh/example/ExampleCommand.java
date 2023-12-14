package ink.anh.example;

import java.util.Arrays;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import ink.anh.api.lingo.Translator;
import ink.anh.api.messages.MessageType;
import ink.anh.api.messages.Messenger;
import ink.anh.api.utils.LangUtils;

/**
 * Implements a command executor for handling custom commands in the plugin.
 * This class is responsible for processing commands related to language settings.
 */
public class ExampleCommand implements CommandExecutor {
	
    private ExampleLangPlugin plugin; // Reference to the main plugin instance.
    private GlobalManager globalManager; // Reference to the global manager for shared functionality.


    /**
     * Constructor for the command.
     * @param plugin The instance of the main plugin class.
     */
	public ExampleCommand(ExampleLangPlugin plugin) {
		this.plugin = plugin;
		this.globalManager = plugin.getManager();
	}

    /**
     * Handles the execution of the command.
     * 
     * @param sender The sender of the command.
     * @param cmd The command that was executed.
     * @param label The alias of the command used.
     * @param args The arguments passed with the command.
     * @return true if the command was processed correctly, false otherwise.
     */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {

            switch (args[0].toLowerCase()) {
            case "reload":
                return reload(sender);
            case "set":
                return setLang(sender, args);
            case "get":
                return getLang(sender);
            case "reset":
                return resetLang(sender);
            default:
                return false;
            }
        }
		return false;
	}

    /**
     * Handles the 'reload' command to reload language settings.
     * 
     * @param sender The sender of the command.
     * @return true if the operation was successful, false otherwise.
     */
    private boolean reload(CommandSender sender) {
    	String[] langs = checkPlayerPermissions(sender, "examplelangplugin.reload");
	    if (langs != null && langs[0] == null) {
            return true;
	    }
	    
        if (plugin.getManager().reload()) {
            sendMessage(sender, Translator.translateKyeWorld(globalManager, "language_reloaded ", langs), MessageType.NORMAL);
            return true;
        }
        return false;
    }

    /**
     * Handles the 'set' command to set player's language preferences.
     * 
     * @param sender The sender of the command.
     * @param args Arguments provided with the command.
     * @return true if the operation was successful, false otherwise.
     */
    private boolean setLang(CommandSender sender, String[] args) {
        // Check if the command is executed by a player
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Check if enough arguments are provided (minimum 2)
            if (args.length < 2) {
                sendMessage(sender, "err_command_format /example set <lang1> <lang2> ...", MessageType.WARNING);
                return true;
            }

            // Create an array to store language codes
            String[] newlangs = Arrays.copyOfRange(args, 1, args.length);
            
            // Attempt to set the player's language preferences
            int result = LangUtils.setLangs(player, newlangs);
            
            // Handling the result of the language setting operation
            if (result == 1) {
                // Successful operation
                String[] langs = LangUtils.getPlayerLanguage(player);
                sendMessage(sender, "language_is_selected " + String.join(", ", langs), MessageType.NORMAL);
            } else if (result == 0) {
                // Invalid language code length
                sendMessage(sender, "err_language_code_2letters ", MessageType.WARNING);
            } else {
                // Other errors
                sendMessage(sender, "err_invalid_language_code ", MessageType.WARNING);
            }
        } else {
            // Command can only be executed by a player
            sendMessage(sender, "err_command_only_player", MessageType.WARNING);
        }
        return true;
    }

    /**
     * Handles the 'get' command to retrieve the player's current language settings.
     * 
     * @param sender The sender of the command.
     * @return true if the operation was successful, false otherwise.
     */
    private boolean getLang(CommandSender sender) {
        // Check if the command is executed by a player
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            // Retrieve the player's current language settings
            String[] langs = LangUtils.getLangs(player);
            if (langs != null) {
                // Display the current language settings to the player
                sendMessage(sender, "you_language " + String.join(", ", langs), MessageType.NORMAL);
            } else {
                // No language settings found
                sendMessage(sender, "you_have_not set_language ", MessageType.WARNING);
            }
        } else {
            // Command can only be executed by a player
            sendMessage(sender, "err_command_only_player", MessageType.WARNING);
        }
        return true;
    }

    /**
     * Handles the 'reset' command to reset the player's language settings.
     * 
     * @param sender The sender of the command.
     * @return true if the operation was successful, false otherwise.
     */
    private boolean resetLang(CommandSender sender) {
        // Check if the command is executed by a player
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            // Attempt to reset the player's language settings
            int result = LangUtils.resetLangs(player);
            
            // Handling the result of the reset operation
            if (result == 1) {
                // Successful reset
                sendMessage(sender, "cleared_the_language ", MessageType.NORMAL);
            } else {
                // No language settings to reset
                sendMessage(sender, "you_have_not set_language ", MessageType.WARNING);
            }
        } else {
            // Command can only be executed by a player
            sendMessage(sender, "err_command_only_player", MessageType.WARNING);
        }
        return true;
    }
    
    /**
     * Checks if the player has the required permissions to execute the command.
     * 
     * @param sender The sender of the command.
     * @param permission The required permission string.
     * @return An array of language codes, or null if executed by the console.
     */
    private String[] checkPlayerPermissions(CommandSender sender, String permission) {
        // Checking if the command is executed by the console
        if (sender instanceof ConsoleCommandSender) {
            return null;
        }

        // Ініціалізація масиву з одним елементом null
        String[] langs = new String[] {null};

        if (sender instanceof Player) {
            Player player = (Player) sender;

            // We get languages for the player
            langs = LangUtils.getPlayerLanguage(player);

            // We check whether the player has permission
            if (!player.hasPermission(permission)) {
                sendMessage(sender, Translator.translateKyeWorld(globalManager, "err_not_have_permission ", langs), MessageType.ERROR);
                return langs;
            }
        }

        return langs;
    }

    /**
     * Sends a message to the command sender.
     * 
     * @param sender The recipient of the message.
     * @param message The message to be sent.
     * @param type The type of message (e.g., normal, error).
     */
	private void sendMessage(CommandSender sender, String message, MessageType type) {
    	Messenger.sendMessage(globalManager, sender, message, type);
    }
}
