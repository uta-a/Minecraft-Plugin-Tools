package Tools.GetEntitiesByDistance;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GetEntitiesByDistance {

    public static List<Entity> get(Player player, double radius) {
        List<Entity> list = new ArrayList<>();

        var nEntity = player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius);

        for (Entity entity : nEntity) {

            if (player.getLocation().distance(entity.getLocation()) > radius) continue;

            if (entity instanceof LivingEntity || entity.getType() == EntityType.ITEM) {
                if (entity == player) continue;
                if (entity.getType() == EntityType.ARMOR_STAND) continue;
                list.add(entity);
            }
            else if (entity.getType() == EntityType.FALLING_BLOCK || entity.getType() == EntityType.POTION || entity.getType() == EntityType.EGG || entity.getType() == EntityType.SNOWBALL) {
                list.add(entity);
            }
        }

        return list;
    }
}
