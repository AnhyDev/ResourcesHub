package ink.anh.example;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import ink.anh.api.LibraryManager;
import ink.anh.api.lingo.Translator;
import ink.anh.api.lingo.lang.LanguageManager;
import ink.anh.api.messages.Logger;
import net.md_5.bungee.api.ChatColor;

// Manages global functionalities of the ExampleLangPlugin.
public class GlobalManager extends LibraryManager {

    private static GlobalManager instance;
    private ExampleLangPlugin plugin;
    
    private LanguageManager langManager;
    private String pluginName;
    private String defaultLang;
    private boolean debug;
    
    // Constructor for initializing the manager with the plugin.
    private GlobalManager(ExampleLangPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
        this.saveDefaultConfig(); // Saves default config if not present.
        this.loadFields(plugin);  // Loads plugin fields from the config.
    }

    // Singleton pattern to get the instance of GlobalManager.
    public static synchronized GlobalManager getManager(ExampleLangPlugin plugin) {
        if (instance == null) {
            instance = new GlobalManager(plugin);
        }
        return instance;
    }
    
    // Returns the plugin instance.
    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    // Returns the plugin name.
    @Override
    public String getPluginName() {
        return pluginName;
    }

    // Returns the LanguageManager instance.
    @Override
    public LanguageManager getLanguageManager() {
        return this.langManager;
    }

    // Returns the default language.
    @Override
    public String getDefaultLang() {
        return defaultLang;
    }

    // Checks if the debug mode is enabled.
    @Override
    public boolean isDebug() {
        return debug;
    }
    
    // Loads various fields from the plugin's configuration.
    private void loadFields(ExampleLangPlugin plugin) {
        defaultLang = plugin.getConfig().getString("language", "en");
        pluginName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin_name", "ExampleLangPlugin"));
        debug = plugin.getConfig().getBoolean("debug", false);
        setLanguageManager();
    }

    // Saves the default configuration file if it doesn't exist.
    private void saveDefaultConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            YamlConfiguration defaultConfig = new YamlConfiguration();
            defaultConfig.set("plugin_name", "ExampleLangPlugin");
            defaultConfig.set("language", "en");
            defaultConfig.set("debug", false);
            try {
                defaultConfig.save(configFile);
                Logger.warn(plugin, "Default configuration created.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Sets the LanguageManager.
    private void setLanguageManager() {
        if (this.langManager == null) {
            this.langManager = LangMessage.getInstance(this);
        } else {
            this.langManager.reloadLanguages();
        }
    }

    // Reloads the plugin configuration.
    public boolean reload() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                saveDefaultConfig();
                plugin.reloadConfig();
                loadFields(plugin);
                Logger.info(plugin, Translator.translateKyeWorld(instance, "configuration_reloaded", new String[]{defaultLang}));
            } catch (Exception e) {
                e.printStackTrace();
                Logger.error(plugin, Translator.translateKyeWorld(instance, "err_reloading_configuration", new String[]{defaultLang}));
            }
        });
        return true;
    }
}
