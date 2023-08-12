package eextr0.duelsplugin;

import eextr0.duelsplugin.Data.DuelStates;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class DuelsPlugin extends JavaPlugin {

    private Connection connection;
    private Map<UUID, DuelStates> duelStates = new HashMap<>();

    //Getters
    public Connection getConnection() {return connection;}
    public Map<UUID, DuelStates> getDuelStates() {return duelStates;};
    //Setters
    public Connection setConnection(Connection connection) {this.connection = connection;
        return connection;}
    public void setDuelState(UUID uuid, DuelStates duelStates) {
        this.duelStates.put(uuid, duelStates);
    }
    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
