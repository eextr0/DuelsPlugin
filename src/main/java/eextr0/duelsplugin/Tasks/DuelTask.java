package eextr0.duelsplugin.Tasks;

import eextr0.duelsplugin.Data.Arena;
import eextr0.duelsplugin.Data.DuelStates;
import eextr0.duelsplugin.Data.KitManager;
import eextr0.duelsplugin.DuelsPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DuelTask {

    private final DuelsPlugin plugin;

    public DuelTask (DuelsPlugin plugin) {
        this.plugin = plugin;
    }
    public void startDuel(Player player1, Player player2, String kit) {

        Arena selectedArena = plugin.getArenaManager().getRandomArena();
        player1.teleport((Location) selectedArena.getSpawnLocation());
        player2.teleport((Location) selectedArena.getSpawnLocation2());
        clearAndEquip(player1, kit);
        clearAndEquip(player2, kit);

        plugin.setDuelState(player1.getUniqueId(), DuelStates.STARTING);
        plugin.setDuelState(player2.getUniqueId(), DuelStates.STARTING);
        startCountdown(player1, player2);
    }

    private void clearAndEquip(Player player, String kit) {
        KitManager kitManager = plugin.getKitManager();
        kitManager.equipKit(player, kit);
    }

    private void startCountdown(Player player1, Player player2) {
        CountdownTask countdownTask = new CountdownTask(player1, player2);
        countdownTask.runTaskTimer(plugin, 0, 20);
    }
}
