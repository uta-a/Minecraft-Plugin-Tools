package Tools.GetEntitiesFromFov;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GetEntitiesFromFov {

    public static List<Entity> get(Player player, double maxDistance, double fieldOfView) {
        List<Entity> entitiesInView = new ArrayList<>();

        // プレイヤーの位置と方向
        Location playerLocation = player.getEyeLocation();
        Vector playerDirection = playerLocation.getDirection();

        // プレイヤーの周囲のエンティティを取得
        List<Entity> nearbyEntities = player.getNearbyEntities(maxDistance, maxDistance, maxDistance);

        for (Entity entity : nearbyEntities) {
            // エンティティの位置
            Location entityLocation = entity.getLocation();
            Vector toEntity = entityLocation.toVector().subtract(playerLocation.toVector());

            // 視線との角度を計算
            double angle = playerDirection.angle(toEntity);

            // 視界内にある場合にリストに追加
            if (angle <= Math.toRadians(fieldOfView / 2)) {
                //エンティティが生きているかアイテム

                if (entity instanceof LivingEntity || entity.getType() == EntityType.ITEM) {
                    if (entity == player) continue;
                    if (entity.getType() == EntityType.ARMOR_STAND) continue;
                    entitiesInView.add(entity);
                }
                else if (entity.getType() == EntityType.FALLING_BLOCK || entity.getType() == EntityType.POTION || entity.getType() == EntityType.EGG || entity.getType() == EntityType.SNOWBALL) {
                    entitiesInView.add(entity);
                }

            }
        }

        return entitiesInView;
    }
}
