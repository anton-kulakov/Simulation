package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.HashMap;
import java.util.Iterator;

public class MakeMove extends Action {
    public void doAction(World world) {
        AddEntities.newJuniorCounter = 0;
        Iterator<Entity> iterator = world.entities.values().iterator();

        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity instanceof Person) {
                ((Person) entity).makeMove(world);
            }
        }

        Iterator<Entity> afterMoveIterator = world.entities.values().iterator();
        HashMap<Coordinates, Entity> entitiesCopy = new HashMap<>();

        while (afterMoveIterator.hasNext()) {
            Entity entity = afterMoveIterator.next();

            if (entity.coordinates != Coordinates.EMPTY) {
                entitiesCopy.put(entity.coordinates, entity);
            }
        }

        world.entities.clear();
        world.entities.putAll(entitiesCopy);
    }
}
