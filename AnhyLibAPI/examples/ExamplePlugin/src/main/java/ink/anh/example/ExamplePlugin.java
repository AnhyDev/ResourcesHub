package ink.anh.example;

import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {
	
    private static ExamplePlugin instance;
    private GlobalManager manager;


    public static ExamplePlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        manager = GlobalManager.getManager(instance);

        this.getCommand("example").setExecutor(new ExampleCommand(this));
    }

	public GlobalManager getManager() {
		return manager;
	}
}
