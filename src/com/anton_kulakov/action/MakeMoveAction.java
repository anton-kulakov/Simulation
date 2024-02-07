package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.HashMap;
import java.util.Iterator;

public class MakeMoveAction extends Action {
    public void perform(World world) {
        AppendEntitiesAction.setNewJuniorCounter(0);
        Iterator<Entity> iterator = world.getEntitiesIterator();

        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity instanceof Person) {
                ((Person) entity).makeMove(world);
            }
        }

        Iterator<Entity> afterMoveIterator = world.getEntitiesIterator();
        HashMap<Coordinates, Entity> cellsCopy = new HashMap<>();

        while (afterMoveIterator.hasNext()) {
            Entity entity = afterMoveIterator.next();

            if (entity.coordinates != Coordinates.EMPTY) {
                cellsCopy.put(entity.coordinates, entity);
            }
        }

        world.clearWorld();
        world.insertCollectionOfEntities(cellsCopy);
    }
}
