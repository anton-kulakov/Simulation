package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

public class MakeMoveAction extends Action {
    public void perform(World world) {
        AppendEntitiesAction.resetNewJuniorCounter();

        world.getEntitiesOfType(Person.class).forEach((key, value) -> value.makeMove(world));

        world.getEntitiesOfType(Entity.class).forEach((key, value) -> {
            if (Coordinates.EMPTY.equals(value.coordinates)) {
                world.removeEntity(value.coordinates);
            }
        });

        world.getEntitiesOfType(Entity.class).forEach((key, value) -> {
            if (!(key.equals(value.coordinates))) {
                world.moveEntity(key, value.coordinates);
            }
        });
    }
}
