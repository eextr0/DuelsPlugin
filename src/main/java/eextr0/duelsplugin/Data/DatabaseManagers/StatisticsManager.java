package eextr0.duelsplugin.Data.DatabaseManagers;

import eextr0.duelsplugin.Data.PlayerStatistics;
import eextr0.duelsplugin.DuelsPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StatisticsManager {

    private final DuelsPlugin plugin;

    public StatisticsManager(DuelsPlugin plugin) {
        this.plugin = plugin;
    }

    public PlayerStatistics getStatsFromDatabase(UUID playerUUID) {
        Connection connection = plugin.getConnection();
        PlayerStatistics statistics = new PlayerStatistics();

        try {
            String query = "SELECT wins, losses, kills, deaths, win_streak FROM player_stats WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, playerUUID.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                statistics.setWins(resultSet.getInt("wins"));
                statistics.setLosses(resultSet.getInt("losses"));
                statistics.setKills(resultSet.getInt("kills"));
                statistics.setDeaths(resultSet.getInt("deaths"));
                statistics.setWinStreak(resultSet.getInt("win_streak"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  statistics;
    }
}
