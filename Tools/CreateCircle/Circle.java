package Tools.CreateCircle;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.Particle;

public class Circle {

    public static void draw(Location center, double radius, int resolution, Color color, double xAngle, double zAngle) {
        World world = center.getWorld();
        if (world == null) return;

        // 回転角をラジアンに変換
        double xRad = Math.toRadians(xAngle);
        double zRad = Math.toRadians(zAngle);

        // 円を描画
        for (int i = 0; i < resolution; i++) {
            // 円の点を計算 (水平円)
            double angle = 2 * Math.PI * i / resolution;
            double x = radius * Math.cos(angle);
            double y = 0;
            double z = radius * Math.sin(angle);

            // x軸回転を適用
            double newY = y * Math.cos(xRad) - z * Math.sin(xRad);
            double newZ = y * Math.sin(xRad) + z * Math.cos(xRad);
            y = newY;
            z = newZ;

            // z軸回転を適用
            double newX = x * Math.cos(zRad) - y * Math.sin(zRad);
            newY = x * Math.sin(zRad) + y * Math.cos(zRad);
            x = newX;
            y = newY;

            // 回転後の座標に基づくパーティクルの位置
            Location particleLocation = center.clone().add(x, y, z);

            // パーティクルを表示
            Particle.DustOptions dustOptions = new Particle.DustOptions(color ,2f);
            world.spawnParticle(Particle.DUST, particleLocation, 1,dustOptions);
        }
    }

}
