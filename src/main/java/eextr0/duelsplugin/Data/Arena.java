package eextr0.duelsplugin.Data;

import javax.xml.stream.Location;

public class Arena {

    private String name;
    private Location spawnLocation;
    private Location spawnLocation2;

    public Arena (String name, Location spawnLocation, Location spawnLocation2) {
        this.name = name;
        this.spawnLocation = spawnLocation;
        this.spawnLocation2 = spawnLocation2;
    }

    public String getName() {
        return name;
    }
    public Location getSpawnLocation() {
        return spawnLocation;
    }
    public Location getSpawnLocation2() {
        return spawnLocation2;
    }
}
