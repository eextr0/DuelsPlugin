package eextr0.duelsplugin.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();

    public void registerArena(Arena arena) {
        arenas.add(arena);
    }
    public Arena getRandomArena() {
        if (arenas.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(arenas.size());
        return arenas.get(randomIndex);
    }
}
