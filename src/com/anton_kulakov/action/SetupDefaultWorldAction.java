package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.ArrayList;
import java.util.List;

public class SetupDefaultWorldAction extends Action {
    private static final int MAX_ENTITIES_ON_START = 24;
    private static final int NUMBER_OF_ENTITIES_TYPES = 6;

    public void perform(World world) {
        List<Entity> entityList = new ArrayList<>();
        int limit = MAX_ENTITIES_ON_START / NUMBER_OF_ENTITIES_TYPES;

        for (int i = 0; i < limit; i++) {
            entityList.add(new House());
            entityList.add(new Tree());
            entityList.add(new Money());
            entityList.add(new ProgrammingCourse(1, 14, 1, 2));
            entityList.add(new Employer(1, 20, 1));
            entityList.add(new Junior(1, 16, 2, 1));
        }

        int i = MAX_ENTITIES_ON_START;

        while (i > 0) {
            int row = random.nextInt(World.getMaxRows());
            int column = random.nextInt(World.getMaxColumns());

            if(world.isCellEmpty(row, column)) {
                world.insertEntity(new Coordinates(row, column), entityList.get(i - 1));
                entityList.remove(i - 1);
                i--;
            }
        }
    }
}