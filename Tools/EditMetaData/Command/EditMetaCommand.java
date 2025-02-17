package Tools.EditMetaData.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import Tools.EditMetaData.Item.EditItemData;
import Tools.EditMetaData.Player.EditPlayerData;

import java.util.Objects;

public class EditMetaCommand implements CommandExecutor {
    private final Plugin plugin;

    public EditMetaCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            //実行されたコマンドが"meta"か
            if (command.getName().equalsIgnoreCase("meta")) {
                if (args.length == 0) return false;

                //mode { player : item }
                String mode = args[0];

                //mode player
                if (Objects.equals(mode, "player")) {

                    // /meta player <user> <option> <string>

                    String option = args[1];    //{ set : get : remove }
                    String target = args[2];    //指定するプレイヤー
                    String key = args[3];   //設定するキー

                    return playerCommand(player, option, target, key);
                }
                //mode item
                else if (Objects.equals(mode, "item")) {

                    // /meta item <option> <string>

                    String option = args[1];    //{ set : get : remove }
                    String key = args[2];   //設定するキー

                    return itemCommand(player, option, key);
                }

            }
        }
        return false;
    }


    private boolean playerCommand(Player player, String option, String playerName, String key) {
        //名前からサーバにいるプレイヤーの取得
        Player target = Bukkit.getPlayer(playerName);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "プレイヤー名が無効です。");
            return false;
        }

        switch (option) {
            //データ設定
            case "set" -> {
                EditPlayerData.set(plugin, target, key);
                player.sendMessage(ChatColor.GREEN + "SetPlayerMeta" + ChatColor.WHITE + ": " + player.getName() + ChatColor.GOLD + " [ " + ChatColor.WHITE + key + ChatColor.GOLD + " ]");
                return true;
            }
            //データがあるかの確認
            case "get" -> {
                if (EditPlayerData.get(plugin, target, key)) {
                    player.sendMessage(ChatColor.GREEN + "HasPlayerMeta" + ChatColor.GOLD + " [ " + ChatColor.WHITE + "TRUE" + ChatColor.GOLD + " ]");
                    return true;
                }
                else {
                    player.sendMessage(ChatColor.RED + "HasPlayerMeta" + ChatColor.GOLD + " [ " + ChatColor.WHITE + "FALSE" + ChatColor.GOLD + " ]");
                    return true;
                }

            }
            //データの消去
            case "remove" -> {
                EditPlayerData.remove(plugin, target, key);
                player.sendMessage(ChatColor.RED + "RemovePlayerMeta" + ChatColor.WHITE + ": " + player.getName() + ChatColor.GOLD + " [ " + ChatColor.WHITE + key + ChatColor.GOLD + " ]");
                return true;
            }
            //デフォルトの処理
            default -> {
                player.sendMessage(ChatColor.RED + "引数が無効です。");
                return false;
            }
        }
    }


    private boolean itemCommand(Player player, String option, String key) {
        switch (option) {
            //データの設定
            case "set" -> {
                ItemStack item = player.getInventory().getItemInMainHand();
                item = EditItemData.set(plugin, item, key);

                player.getInventory().setItemInMainHand(item);

                player.sendMessage(ChatColor.GREEN + "SetItemMeta" + ChatColor.GOLD + " [ " + ChatColor.WHITE + key + ChatColor.GOLD + " ]");

                return true;
            }
            //データがあるかの確認
            case "get" -> {
                ItemStack item = player.getInventory().getItemInMainHand();
                if (EditItemData.get(plugin, item, key)) {
                    player.sendMessage(ChatColor.GREEN + "HasItemMeta" + ChatColor.GOLD + " [ " + ChatColor.WHITE + "TRUE" + ChatColor.GOLD + " ]");
                }
                else {
                    player.sendMessage(ChatColor.RED + "HasItemMeta" + ChatColor.GOLD + " [ " + ChatColor.WHITE + "FALSE" + ChatColor.GOLD + " ]");
                }

                return true;
            }
            //データの消去
            case "remove" -> {
                ItemStack item = player.getInventory().getItemInMainHand();
                item = EditItemData.remove(plugin, item, key);
                player.getInventory().setItemInMainHand(item);

                player.sendMessage(ChatColor.RED + "RemoveItemMeta" + ChatColor.GOLD + " [ " + ChatColor.WHITE + key + ChatColor.GOLD + " ]");

                return true;
            }
            //デフォルトの処理
            default -> {
                player.sendMessage(ChatColor.RED + "引数が無効です。");
                return false;
            }
        }
    }
}
