package eextr0.duelsplugin.Commands;

import eextr0.duelsplugin.Data.DuelStates;
import eextr0.duelsplugin.DuelsPlugin;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DuelsPluginCommands implements TabExecutor {

    private final DuelsPlugin plugin;

    public DuelsPluginCommands (DuelsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender,Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendPlainMessage("This command can only be executed by players.");
            return true;
        }

        Player p = (Player)commandSender;
        if (args.length == 0) {
            commandSender.sendPlainMessage("Command List:");
            commandSender.sendPlainMessage("/duel challenge <name> [kit]");
            commandSender.sendPlainMessage("/duel stats [name]");
            commandSender.sendPlainMessage("/duel accept <name>");
            return true;
        }

        switch (args[0]) {
            case "challenge" -> {
                if (p.hasPermission("duel.challenge")) {
                    if (args.length < 2) {
                        commandSender.sendPlainMessage("Usage: /duel challenge <name> [kit]");
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        p.sendPlainMessage(NamedTextColor.RED + "Player not found or not online.");
                        return true;
                    }

                    UUID playerUUID = p.getUniqueId();
                    UUID targetUUID = target.getUniqueId();
                    Map<UUID, DuelStates> duelStates = plugin.getDuelStates();

                    DuelStates playerState = duelStates.get(playerUUID);
                    DuelStates targetState = duelStates.get(targetUUID);

                    if (playerState == null) {
                        if (targetState == null) {
                            plugin.setDuelState(playerUUID, DuelStates.INVITED);
                            plugin.setDuelState(targetUUID, DuelStates.INVITED);

                            String kitName = args.length >3 ? args[2] : "default";

                            p.sendPlainMessage(NamedTextColor.GREEN + "Duel request sent to " + target.getName() + ".");
                            target.sendPlainMessage(NamedTextColor.GREEN + "You have been challenged to a duel by " + p.getName() + ". Type '/duel accept " + p.getName() + "' to accept.");
                            return true;
                        } else {
                            p.sendPlainMessage(NamedTextColor.RED + target.getName() + "is already in a duel or has a pending duel request.");
                            return true;
                        }

                    } else {
                        p.sendPlainMessage(NamedTextColor.RED + "You are already in a duel or have an ongoing duel request.");
                        return true;
                    }

                }
                return true;
            }
            case "stats" -> {
                if (p.hasPermission("duel.stats")) {

                }
                return true;
            }

            case "accept" -> {
                if (p.hasPermission("duel.accept")) {

                }
                return true;
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        return null;
    }
}
