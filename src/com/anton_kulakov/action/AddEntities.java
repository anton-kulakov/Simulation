package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.HashMap;
import java.util.Map;

public class AddEntities extends Action {
    public static int newJuniorCounter;
    public void doAction(World world) {
        HashMap<String, Integer> sumOfEntities = countEntities(world);
        int counter = 0;

        while (counter < newJuniorCounter) {
            Junior newJunior = new Junior(1, 16, 2, 1);
            Coordinates coordinatesForNewJunior = world.getNewEntityCoordinates();
            newJunior.coordinates = coordinatesForNewJunior;
            world.entities.put(coordinatesForNewJunior, newJunior);

            counter++;
        }

        for (Map.Entry<String, Integer> entityAmount : sumOfEntities.entrySet()) {
            if (entityAmount.getValue() < 2) {
                world.addEntity(entityAmount.getKey());
            }
        }
    }

    private HashMap<String, Integer> countEntities(World world) {
        HashMap<String, Integer> sumOfEntities = new HashMap<>();

        sumOfEntities.put("Money", 0);
        sumOfEntities.put("Employer", 0);
        sumOfEntities.put("Junior", 0);
        sumOfEntities.put("ProgrammingCourse", 0);

        for (Entity entity : world.entities.values()) {
            switch (entity.getClass().getSimpleName()) {
                case "Money" -> {
                    int value = sumOfEntities.get("Money");
                    sumOfEntities.put("Money", value + 1);
                }

                case "Employer" -> {
                    int value = sumOfEntities.get("Employer");
                    sumOfEntities.put("Employer", value + 1);
                }

                case "Junior" -> {
                    int value = sumOfEntities.get("Junior");
                    sumOfEntities.put("Junior", value + 1);
                }

                case "ProgrammingCourse" -> {
                    int value = sumOfEntities.get("ProgrammingCourse");
                    sumOfEntities.put("ProgrammingCourse", value + 1);
                }
            }
        }

        return sumOfEntities;
    }
}
