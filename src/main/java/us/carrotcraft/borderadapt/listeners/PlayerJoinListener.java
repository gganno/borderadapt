package us.carrotcraft.borderadapt.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import us.carrotcraft.borderadapt.Borderadapt;

public class PlayerJoinListener implements Listener
{
    private Borderadapt plugin;

    public PlayerJoinListener(Borderadapt instance)
    {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        World world = player.getWorld();


        // Check if there are other players in this world
        if (world.getPlayers().size() > 1)
        {
            // Get the location and size of world border, along with player location
            Location worldBoarderCenter = world.getWorldBorder().getCenter();
            Double worldSize = world.getWorldBorder().getSize();
            Location playerLocation = player.getLocation();

            // If the player is outside of the border they need to be tp'd to the closest player
            if (!inBorder(worldBoarderCenter, worldSize, playerLocation))
            {
                player.teleport(getClosestPlayer(player, world));
                player.sendMessage(ChatColor.GOLD + "You have been teleported to the nearest player as the border has moved");
            }
        }
    }

    private boolean inBorder(Location borderCenter, Double borderSize, Location playerLocation)
    {

        Double playerX = playerLocation.getX();
        Double playerZ = playerLocation.getZ();

        Double worldX = borderCenter.getX();
        Double worldZ = borderCenter.getZ();

        Double xMax = worldX + (borderSize / 2);
        Double zMax = worldZ + (borderSize / 2);
        Double xMin = worldX - (borderSize / 2);
        Double zMin = worldZ - (borderSize / 2);

        // Check if player is within square area
        if (playerX > xMin &&
            playerX < xMax &&
            playerZ > zMin &&
            playerZ < zMax)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private Player getClosestPlayer(Player originalPlayer, World world)
    {
        Player result = null;
        Double lastDistance = Double.MAX_VALUE;

        // Loop through all players and find the closest one
        for (Player player: world.getPlayers())
        {
            if (player != originalPlayer)
            {
                Double distance = originalPlayer.getLocation().distanceSquared(player.getLocation());

                if(distance < lastDistance)
                {
                    lastDistance = distance;
                    result = player;
                }
            }
        }

        return result;
    }
}
