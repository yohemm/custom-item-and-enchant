package fr.yohem.bettertools;

import org.bukkit.plugin.java.JavaPlugin;

public final class BetterTools extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("customenchant").setExecutor(new ToolsCommand());
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ToolsListener(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
