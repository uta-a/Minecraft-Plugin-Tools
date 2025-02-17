package Tools.CreateSphere;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class Sphere {

    public static void draw(Location center, double radius, int density, Color color) {
        for (int i = 0; i <= density; i++) {
            double theta = Math.PI * i / density; // 緯度
            for (int j = 0; j < density; j++) {
                double phi = 2 * Math.PI * j / density; // 経度

                // 球の座標を計算
                double x = radius * Math.sin(theta) * Math.cos(phi);
                double y = radius * Math.cos(theta);
                double z = radius * Math.sin(theta) * Math.sin(phi);

                // パーティクルをスポーン
                Particle.DustOptions dustOptions = new Particle.DustOptions(color,2f);
                center.getWorld().spawnParticle(Particle.DUST, center.clone().add(x, y, z), 1,dustOptions);
            }
        }
    }

}
