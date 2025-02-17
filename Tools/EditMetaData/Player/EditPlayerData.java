package Tools.EditMetaData.Player;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class EditPlayerData {

    public static void set(Plugin plugin, Player player, String key) {
        NamespacedKey metaKey = new NamespacedKey(plugin, key);
        player.getPersistentDataContainer().set(metaKey, PersistentDataType.BOOLEAN, true);
    }

    public static boolean get(Plugin plugin, Player player, String key) {
        NamespacedKey metaKey = new NamespacedKey(plugin, key);
        return player.getPersistentDataContainer().has(metaKey, PersistentDataType.BOOLEAN);
    }

    public static void remove(Plugin plugin, Player player, String key) {
        NamespacedKey metaKey = new NamespacedKey(plugin, key);
        player.getPersistentDataContainer().remove(metaKey);
    }
}
