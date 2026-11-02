package com.inkarius.bedrockbox;

import com.inkarius.bedrockbox.commands.SetupCommand;
import com.inkarius.bedrockbox.managers.ConfigManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        configManager = new ConfigManager(this);

        PluginCommand command = getCommand("bb");
        if (command != null) {
            command.setExecutor(new SetupCommand(configManager));
        } else {
            getLogger().severe("Команда bb не объявлена в plugin.yml!");
        }

        getLogger().info("BedrockBoxGame успешно запущен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("BedrockBoxGame выключен.");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
