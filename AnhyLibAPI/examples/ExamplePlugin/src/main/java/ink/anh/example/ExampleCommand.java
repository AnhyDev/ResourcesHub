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
	
	private ExamplePlugin plugin;
    private GlobalManager globalManager;


	public ExampleCommand(ExamplePlugin plugin) {
		this.plugin = plugin;
		this.globalManager = plugin.getManager();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {

            switch (args[0].toLowerCase()) {
            case "reload":
                return reload(sender);
            default:
                return false;
            }
        }
		return false;
	}

    private boolean reload(CommandSender sender) {
    	String[] langs = checkPlayerPermissions(sender, "exampleplugin.reload");
	    if (langs != null && langs[0] == null) {
            return true;
	    }
	    
        if (plugin.getManager().reload()) {
            sendMessage(sender, Translator.translateKyeWorld(globalManager, "language_reloaded ", langs), MessageType.NORMAL);
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
