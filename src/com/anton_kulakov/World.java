package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;
import java.util.HashMap;

public class World {
    public HashMap<Coordinates, Entity> entities = new HashMap<>();

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }
}
