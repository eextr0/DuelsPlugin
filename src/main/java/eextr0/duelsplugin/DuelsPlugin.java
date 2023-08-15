package eextr0.duelsplugin;

import eextr0.duelsplugin.Data.*;
import eextr0.duelsplugin.Data.DatabaseManagers.StatisticsManager;
import eextr0.duelsplugin.Tasks.CountdownTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class DuelsPlugin extends JavaPlugin {

    private Connection connection;
    private Map<UUID, DuelStates> duelStates = new HashMap<>();
    private StatisticsManager statisticsManager;
    private ArenaManager arenaManager;
    private KitManager kitManager;
    private CountdownTask countdownTask;

    //Getters
    public Connection getConnection() {return connection;}
    public Map<UUID, DuelStates> getDuelStates() {return duelStates;};
    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }
    public ArenaManager getArenaManager() {return arenaManager;}
    public KitManager getKitManager() {return kitManager;}
    public CountdownTask getCountdownTask() {return countdownTask;}
    //Setters
    public Connection setConnection(Connection connection) {this.connection = connection;
        return connection;}
    public void setDuelState(UUID uuid, DuelStates duelStates) {
        this.duelStates.put(uuid, duelStates);
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        statisticsManager = new StatisticsManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
