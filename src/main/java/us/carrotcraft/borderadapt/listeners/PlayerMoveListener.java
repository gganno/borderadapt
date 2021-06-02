package us.carrotcraft.borderadapt.listeners;

import jdk.javadoc.internal.doclets.toolkit.util.Utils;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import us.carrotcraft.borderadapt.Borderadapt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerMoveListener implements Listener
{
    private Borderadapt plugin;

    public PlayerMoveListener(Borderadapt instance)
    {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {

        Double totalX = 0d;
        Double totalZ = 0d;

        // Get a handle on the world
        World world = event.getPlayer().getWorld();

        // Get a list of all players in said world
        List<Player> PlayersInWorld = world.getPlayers();

        for (Player player: PlayersInWorld)
        {
            totalX += Double.valueOf(player.getLocation().getX());
            totalZ += Double.valueOf(player.getLocation().getZ());
        }

        if (world.getWorldBorder() != null)
        {
            world.getWorldBorder().setCenter(totalX / PlayersInWorld.size(), totalZ / PlayersInWorld.size());
        }
    }
}
