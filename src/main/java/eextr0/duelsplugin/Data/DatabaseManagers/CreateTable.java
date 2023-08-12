package eextr0.duelsplugin.Data.DatabaseManagers;

import eextr0.duelsplugin.DuelsPlugin;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    private final DuelsPlugin plugin;

    public CreateTable(DuelsPlugin plugin) {
        this.plugin = plugin;
    }
    public void createTable() {
        try(Statement statement = plugin.getConnection().createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS suggestions (title Text,body TEXT, author TEXT, tag TEXT)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
