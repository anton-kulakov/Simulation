package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.HashMap;
import java.util.Iterator;

public class MakeMove extends Action {
    // public static int newJuniorCounter;
    public void doAction(World world) {
        // newJuniorCounter = 0;
        AddEntities.newJuniorCounter = 0;
        Iterator<Entity> iterator = world.entities.values().iterator();

        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity instanceof Person) {
                ((Person) entity).makeMove(world);
            }
        }

        Iterator<Entity> iteratorAfterMove = world.entities.values().iterator();
        HashMap<Coordinates, Entity> entitiesCopy = new HashMap<>();

        while (iteratorAfterMove.hasNext()) {
            Entity entity = iteratorAfterMove.next();

            if (entity.coordinates != Coordinates.EMPTY) {
                entitiesCopy.put(entity.coordinates, entity);
            }
        }

        world.entities.clear();
        world.entities.putAll(entitiesCopy);

//        int counter = 0;
//        while (counter < newJuniorCounter) {
//            Junior newJunior = new Junior(1, 16, 2, 1);
//            Coordinates coordinatesForNewJunior = world.getNewEntityCoordinates();
//            newJunior.coordinates = coordinatesForNewJunior;
//            world.entities.put(coordinatesForNewJunior, newJunior);
//
//            counter++;
//        }
    }
}
