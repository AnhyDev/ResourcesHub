package ink.anh.example;

import org.bukkit.plugin.java.JavaPlugin;

// Main class for the ExampleLangPlugin, extending JavaPlugin.
public class ExampleLangPlugin extends JavaPlugin {
    
    // Singleton instance of this class.
    private static ExampleLangPlugin instance;

    // Manager for global functionalities.
    private GlobalManager manager;

    // Method to get the singleton instance of this class.
    public static ExampleLangPlugin getInstance() {
        return instance;
    }

    // Method called when the plugin is enabled.
    @Override
    public void onEnable() {
        // Assign the current instance to the static variable.
        instance = this;

        // Initialize the global manager.
        manager = GlobalManager.getManager(instance);

        // Set the executor for the command "example".
        this.getCommand("example").setExecutor(new ExampleCommand(this));
    }

    // Getter for the GlobalManager instance.
    public GlobalManager getManager() {
        return manager;
    }
}
