package Tools.EditMetaData.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MetaCommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 1) {
                return List.of("player","item");
            }

            if (args.length == 2) {
                return List.of("set","get","remove");
            }

            if (args.length == 3 && Objects.equals(args[0], "player")) {
                List<String> list = new ArrayList<>();

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    list.add(onlinePlayer.getName());
                }

                return list;
            }

            return List.of();
        }
        return List.of();
    }
}
