package eextr0.duelsplugin.Data;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerStatistics {

    private int wins;
    private int losses;
    private int kills;
    private int deaths;
    private int winStreak;

    public PlayerStatistics() {

    this.wins = 0;
    this.losses = 0;
    this.kills = 0;
    this.deaths = 0;
    this.winStreak = 0;
    }
    public int getWins() {
        return wins;
    }
    public void setWins (int wins) {
        this.wins = wins;
    }
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }
    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }
    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    public int getWinStreak() {
        return winStreak;
    }
    public void setWinStreak(int winStreak) {
        this.winStreak = winStreak;
    }
}
