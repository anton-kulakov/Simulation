package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.HashMap;

public class MakeMoveAction extends Action {
    public void perform(World world) {
        AppendEntitiesAction.resetNewJuniorCounter();
        HashMap<Coordinates, Entity> cellsCopy = new HashMap<>();

        world.getEntitiesOfType(Person.class).forEach((key, value) -> value.makeMove(world));

        world.getCells().forEach((key, value) -> {
            if (value.coordinates != Coordinates.EMPTY) {
                cellsCopy.put(value.coordinates, value);
            }
        });

        world.clearWorld();
        world.insertCollectionOfEntities(cellsCopy);
    }
}
