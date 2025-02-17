package com.kokosaba.movePoint.ability.MovePoint.tool;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class GetViewpointPos {

    public Location get(Player player, double range, double step) {
        // 視線の起点と方向を取得
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection().normalize();

        // 現在位置を設定
        Location currentLocation = eyeLocation.clone();

        for (double distance = 0; distance <= range; distance += step) {
            // 現在位置を更新
            currentLocation.add(direction.clone().multiply(step));

            // 現在位置のブロックを取得
            Block block = currentLocation.getBlock();

            // 固体ブロックかチェック
            if (block.getType().isSolid()) {
                // 固体ブロックが見つかった場合、正確な座標を返す
                return currentLocation.clone().subtract(direction.clone().normalize().multiply(1));
            }
        }

        // 見つからなかった場合
        return null;
    }

}
