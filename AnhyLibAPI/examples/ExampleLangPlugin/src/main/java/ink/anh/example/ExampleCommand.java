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

public class ExampleCommand implements CommandExecutor {
	
	private ExampleLangPlugin plugin;
    private GlobalManager globalManager;


	public ExampleCommand(ExampleLangPlugin plugin) {
		this.plugin = plugin;
		this.globalManager = plugin.getManager();
	}

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

    private boolean setLang(CommandSender sender, String[] args) {
        String langData = "Lingo";

        // Перевіряємо, чи є викликач команди гравцем
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // Перевірка, чи є достатньо аргументів (мінімум 2)
            if (args.length < 2) {
                sendMessage(sender, "err_command_format /example set <lang1> <lang2> ...", MessageType.WARNING);
                return true;
            }

            // Створюємо масив для зберігання мовних кодів
            String[] langs = Arrays.copyOfRange(args, 1, args.length);

            // Отримуємо масив доступних мовних кодів ISO 639-1
            String[] isoLanguages = Locale.getISOLanguages();
            
            for (int i = 0; i < langs.length; i++) {
                String lang = langs[i].toLowerCase(); // Переведення до нижнього регістру

                // Перевірка на довжину мовного коду
                if (lang.length() != 2) {
                    sendMessage(sender, "err_language_code_2letters ", MessageType.WARNING);
                    return true;
                }

                // Перевірка, чи є код мови дійсним ISO мовним кодом або спеціальним випадком "ua"
                if (!Arrays.asList(isoLanguages).contains(lang)) {
                    sendMessage(sender, "err_invalid_language_code " + lang, MessageType.WARNING);
                    return true;
                }
            }

            // Якщо все добре, здійснюємо налаштування мови для гравця
            new PlayerData().setCustomData(player, langData, langs);
            sendMessage(sender, "language_is_selected " + String.join(" ", langs), MessageType.NORMAL);
        }
        return true;
    }

    private boolean getLang(CommandSender sender) {
        // Перевіряємо, чи є викликач команди гравцем
        if (sender instanceof Player) {
            Player player = (Player) sender;
        	String langs;
        	String langData = "Lingo";
        	
        	PlayerData data = new PlayerData();
        	if (data.hasCustomData(player, langData)) {
        		langs = data.getStringData(player, langData).replace(',', ' ');
                sendMessage(sender, "you_language " + langs, MessageType.NORMAL);
                return true;
        	}
            sendMessage(sender, "you_have_not set_language ", MessageType.WARNING);
            return true;
        }
        return false;
    }

    private boolean resetLang(CommandSender sender) {
        // Перевіряємо, чи є викликач команди гравцем
        if (sender instanceof Player) {
            Player player = (Player) sender;
        	String langData = "Lingo";
        	
        	PlayerData data = new PlayerData();
        	if (data.hasCustomData(player, langData)) {
        		data.removeCustomData(player, langData);
                sendMessage(sender, "cleared_the_language ", MessageType.NORMAL);
                return true;
        	}
            sendMessage(sender, "you_have_not set_language ", MessageType.WARNING);
            return true;
        }
        return false;
    }
	
    private String[] checkPlayerPermissions(CommandSender sender, String permission) {
        // Перевірка, чи команду виконує консоль
        if (sender instanceof ConsoleCommandSender) {
            return null;
        }

        // Ініціалізація масиву з одним елементом null
        String[] langs = new String[] {null};

        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Отримуємо мови для гравця
            langs = LangUtils.getPlayerLanguage(player);

            // Перевіряємо наявність дозволу у гравця
            if (!player.hasPermission(permission)) {
                sendMessage(sender, Translator.translateKyeWorld(globalManager, "err_not_have_permission ", langs), MessageType.ERROR);
                return langs;
            }
        }

        return langs;
    }

	private void sendMessage(CommandSender sender, String message, MessageType type) {
    	Messenger.sendMessage(globalManager, sender, message, type);
    }
}
