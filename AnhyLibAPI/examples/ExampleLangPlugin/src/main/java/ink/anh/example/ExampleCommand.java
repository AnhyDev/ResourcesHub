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

// Class implementing CommandExecutor to handle plugin commands.
public class ExampleCommand implements CommandExecutor {
	
    // Reference to the main plugin instance.
    private ExampleLangPlugin plugin;

    // Reference to the global manager handling various functionalities.
    private GlobalManager globalManager;

    // Constructor initializing the plugin and global manager.
    public ExampleCommand(ExampleLangPlugin plugin) {
        this.plugin = plugin;
        this.globalManager = plugin.getManager();
    }

    // Method to handle command execution.
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check if any arguments were provided with the command.
        if (args.length > 0) {

            // Handle different command arguments.
            switch (args[0].toLowerCase()) {
                case "reload":
                    return reload(sender); // Handle 'reload' command.
                case "set":
                    return setLang(sender, args); // Handle 'set' command for language.
                case "get":
                    return getLang(sender); // Handle 'get' command to retrieve language.
                case "reset":
                    return resetLang(sender); // Handle 'reset' command to reset language settings.
                default:
                    return false; // Return false if the command is not recognized.
            }
        }
        return false; // Return false if no arguments are provided.
    }

    // Method to handle the 'reload' command.
    private boolean reload(CommandSender sender) {
        // Check player permissions for the 'reload' command.
        String[] langs = checkPlayerPermissions(sender, "examplelangplugin.reload");
        if (langs != null && langs[0] == null) {
            return true; // Permission granted or console command.
        }
        
        // Reload the plugin configuration.
        if (plugin.getManager().reload()) {
            // Send confirmation message.
            sendMessage(sender, Translator.translateKyeWorld(globalManager, "language_reloaded ", langs), MessageType.NORMAL);
            return true;
        }
        return false; // Return false if reload fails.
    }

    // Method to handle the 'set' command for setting the language.
    private boolean setLang(CommandSender sender, String[] args) {
        // Define a custom key for player data.
        String langData = "Lingo";

        // Check if the sender is a player.
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Check if enough arguments are provided.
            if (args.length < 2) {
                // Send error message for incorrect command format.
                sendMessage(sender, "err_command_format /example set <lang1> <lang2> ...", MessageType.WARNING);
                return true;
            }

            // Create an array to store language codes from the command arguments.
            String[] langs = Arrays.copyOfRange(args, 1, args.length);

            // Get an array of available ISO 639-1 language codes.
            String[] isoLanguages = Locale.getISOLanguages();
            
            // Iterate through the provided language codes.
            for (int i = 0; i < langs.length; i++) {
                // Convert each language code to lowercase.
                String lang = langs[i].toLowerCase();

                // Check the length of each language code.
                if (lang.length() != 2) {
                    // Send error message for incorrect language code length.
                    sendMessage(sender, "err_language_code_2letters ", MessageType.WARNING);
                    return true;
                }

                // Check if the language code is a valid ISO language code or a special case like "ua".
                if (!Arrays.asList(isoLanguages).contains(lang)) {
                    // Send error message for invalid language code.
                    sendMessage(sender, "err_invalid_language_code " + lang, MessageType.WARNING);
                    return true;
                }
            }

            // Set the language for the player.
            new PlayerData().setCustomData(player, langData, langs);
            // Send confirmation message.
            sendMessage(sender, "language_is_selected " + String.join(" ", langs), MessageType.NORMAL);
        }
        return true;
    }

    // Method to handle the 'get' command to retrieve the current language.
    private boolean getLang(CommandSender sender) {
        // Check if the sender is a player.
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String langs;
            String langData = "Lingo";
            
            // Create an instance of PlayerData.
            PlayerData data = new PlayerData();
            
            // Check if the player has custom language data set.
            if (data.hasCustomData(player, langData)) {
                // Retrieve the language data.
                langs = data.getStringData(player, langData).replace(',', ' ');
                // Send the current language to the player.
                sendMessage(sender, "you_language " + langs, MessageType.NORMAL);
                return true;
            }
            // Send error message if no language is set.
            sendMessage(sender, "you_have_not set_language ", MessageType.WARNING);
            return true;
        }
        return false;
    }

    // Method to handle the 'reset' command to reset language settings.
    private boolean resetLang(CommandSender sender) {
        // Check if the sender is a player.
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String langData = "Lingo";
            
            // Create an instance of PlayerData.
            PlayerData data = new PlayerData();
            
            // Check if the player has custom language data.
            if (data.hasCustomData(player, langData)) {
                // Remove the custom language data.
                data.removeCustomData(player, langData);
                // Send confirmation message.
                sendMessage(sender, "cleared_the_language ", MessageType.NORMAL);
                return true;
            }
            // Send error message if no language is set.
            sendMessage(sender, "you_have_not set_language ", MessageType.WARNING);
            return true;
        }
        return false;
    }

    // Method to check player permissions for executing a command.
    private String[] checkPlayerPermissions(CommandSender sender, String permission) {
        // Check if the command is executed by the console.
        if (sender instanceof ConsoleCommandSender) {
            return null; // Console has all permissions.
        }

        // Initialize an array with a null element.
        String[] langs = new String[] {null};

        // Check if the sender is a player.
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Get the languages for the player.
            langs = LangUtils.getPlayerLanguage(player);

            // Check if the player has the required permission.
            if (!player.hasPermission(permission)) {
                // Send error message for lacking permission.
                sendMessage(sender, Translator.translateKyeWorld(globalManager, "err_not_have_permission ", langs), MessageType.ERROR);
                return langs;
            }
        }

        return langs; // Return the language(s) of the player.
    }

    // Method to send a message to the command sender.
    private void sendMessage(CommandSender sender, String message, MessageType type) {
        Messenger.sendMessage(globalManager, sender, message, type);
    }
}
