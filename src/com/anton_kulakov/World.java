package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;
import java.util.concurrent.ConcurrentHashMap;

public class World {
    public ConcurrentHashMap<Coordinates, Entity> entities = new ConcurrentHashMap<>();

    public boolean isCellEmpty(int row, int column) {
        for (Coordinates coordinates : entities.keySet()) {
            if (coordinates.row == row && coordinates.column == column) {
                return false;
            }
        }
        return true;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }
}
