package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetupDefaultWorldAction extends Action {
    private static final int MAX_ENTITIES_ON_START = 48;
    private static final int NUMBER_OF_TYPES_OF_ENTITIES = 6;
    private static Random random = new Random();
    public static void doAction(World world) {
        List<Entity> entityList = new ArrayList<>();
        int max = MAX_ENTITIES_ON_START / NUMBER_OF_TYPES_OF_ENTITIES;
        for (int j = 0; j < max; j++) {
            entityList.add(new House());
            entityList.add(new Tree());
            entityList.add(new Money());
            entityList.add(new ProgrammingSchool(4, 10));
            entityList.add(new Employer(2, 40));
            entityList.add(new Junior(3, 20, 3));
        }

        int i = MAX_ENTITIES_ON_START - 1;
        while (i >= 0) {
            int row = random.nextInt(10);
            int column = random.nextInt(10);
            Coordinates coordinates = new Coordinates(row, column);
            if(world.isCellEmpty(coordinates)) {
                world.entities.put(coordinates, entityList.get(i));
                world.entities.get(coordinates).coordinates = coordinates;
                entityList.remove(i);
                i--;
            }
        }
    }
}
