package Tools.EditMetaData.Item;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class EditItemData {

    public static ItemStack set(Plugin plugin, ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            NamespacedKey metaKey = new NamespacedKey(plugin, key);
            meta.getPersistentDataContainer().set(metaKey, PersistentDataType.BOOLEAN, true);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static boolean get(Plugin plugin, ItemStack item, String key) {
        if (!item.hasItemMeta()) return false;

        ItemMeta meta = item.getItemMeta();
        NamespacedKey metaKey = new NamespacedKey(plugin, key);

        if (!Objects.requireNonNull(meta).getPersistentDataContainer().has(metaKey, PersistentDataType.BOOLEAN)) return false;

        return Boolean.TRUE.equals(meta.getPersistentDataContainer().get(metaKey, PersistentDataType.BOOLEAN));
    }

    public static ItemStack remove(Plugin plugin, ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            NamespacedKey metaKey = new NamespacedKey(plugin, key);
            meta.getPersistentDataContainer().remove(metaKey);
            item.setItemMeta(meta);
        }
        return item;
    }
}
