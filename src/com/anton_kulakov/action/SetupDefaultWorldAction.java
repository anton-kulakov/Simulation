package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;
import java.util.ArrayList;
import java.util.List;

public class SetupDefaultWorldAction extends Action {
    private static final int MAX_ENTITIES_ON_START = 48;
    private static final int NUMBER_OF_TYPES_OF_ENTITIES = 6;
    public void doAction(World world) {
        List<Entity> entityList = new ArrayList<>();
        int max = MAX_ENTITIES_ON_START / NUMBER_OF_TYPES_OF_ENTITIES;
        for (int j = 0; j < max; j++) {
            entityList.add(new House());
            entityList.add(new Tree());
            entityList.add(new Money());
            entityList.add(new ProgrammingSchool(4, 10, 1));
            entityList.add(new Employer(2, 40, 3));
            entityList.add(new Junior(3, 20, 2, 3));
        }

        int i = MAX_ENTITIES_ON_START - 1;
        while (i >= 0) {
            int row = random.nextInt(10);
            int column = random.nextInt(10);

            if(world.isCellEmpty(row, column)) {
                Coordinates coordinates = new Coordinates(row, column);
                world.entities.put(coordinates, entityList.get(i));
                world.entities.get(coordinates).coordinates = coordinates;
                entityList.remove(i);
                i--;
            }
        }
    }
}
