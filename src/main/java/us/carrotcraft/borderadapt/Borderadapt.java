package us.carrotcraft.borderadapt;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import us.carrotcraft.borderadapt.commands.StartCommand;
import us.carrotcraft.borderadapt.listeners.PlayerJoinListener;
import us.carrotcraft.borderadapt.listeners.PlayerMoveListener;

public final class Borderadapt extends JavaPlugin
{
    private PlayerJoinListener playerJoin = new PlayerJoinListener(this);
    private PlayerMoveListener playerMove = new PlayerMoveListener(this);

    @Override
    public void onEnable()
    {
        // Plugin startup logic

        PluginManager pm = getServer().getPluginManager();

        // Register for events
        pm.registerEvents(playerJoin, this);
        pm.registerEvents(playerMove, this);

        //Register commands
        //getCommand("start").setExecutor(new StartCommand());


    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }
}
