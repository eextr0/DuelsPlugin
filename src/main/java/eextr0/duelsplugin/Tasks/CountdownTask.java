package eextr0.duelsplugin.Tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownTask extends BukkitRunnable {

    private final Player player1;
    private final Player player2;
    private int countdown;

    public CountdownTask(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.countdown = 5;
    }

    @Override
    public void run() {
        if (countdown > 0) {
            sendTitle(player1, "Duel Starting in", String.valueOf(countdown));
            sendTitle(player2, "Duel Starting in", String.valueOf(countdown));
            countdown--;
        } else {
            sendTitle(player1, "FIGHT!", "");
            sendTitle(player2, "FIGHT!", "");
            this.cancel();
        }
    }

    private void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(title, subtitle, 0, 20, 0);
    }
}
