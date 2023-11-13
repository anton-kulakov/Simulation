package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.Map;
import com.anton_kulakov.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetupDefaultMapAction extends Action {
    private static final int MAX_ENTITIES_ON_START = 48;
    private static final int NUMBER_OF_TYPES_OF_ENTITIES = 6;
    private static Random random = new Random();
    @Override
    public void doAction(Map map) {
        List<Entity> entityList = new ArrayList<>();
        int max = MAX_ENTITIES_ON_START / NUMBER_OF_TYPES_OF_ENTITIES;
        for (int j = 0; j < max; j++) {
            entityList.add(new House());
            entityList.add(new Tree());
            entityList.add(new ProgrammingCourse());
            entityList.add(new Money());
            entityList.add(new Employer(2, 40));
            entityList.add(new Junior(3, 20, 3));
        }

        int i = MAX_ENTITIES_ON_START - 1;
        while (i >= 0) {
            int row = random.nextInt(11);
            int column = random.nextInt(11);
            Coordinates coordinates = new Coordinates(row, column);
            if(map.isCellEmpty(coordinates)) {
                map.entities.put(coordinates, entityList.get(i));
                entityList.remove(i);
                i--;
            }
        }
    }
}
