package ink.anh.example;

import org.bukkit.plugin.java.JavaPlugin;

// The main class of the plugin, extending JavaPlugin.
public class ExamplePlugin extends JavaPlugin {
    
    // Singleton instance of this class.
    private static ExamplePlugin instance;

    // Global manager for handling various functionalities.
    private GlobalManager manager;

    // Public method to get the instance of this class.
    public static ExamplePlugin getInstance() {
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
