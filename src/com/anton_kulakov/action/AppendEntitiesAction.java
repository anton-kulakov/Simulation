package com.anton_kulakov.action;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.HashMap;
import java.util.Map;

public class AppendEntitiesAction extends Action {
    private static int newJuniorCounter;
    public void perform(World world) {
        HashMap<String, Integer> entityCounterMap = countEntities(world);
        int counter = 0;

        while (counter < newJuniorCounter) {
            world.insertEntities(world.getNewEntityCoordinates(), new Junior(1, 16, 2, 1));
            counter++;
        }

        for (Map.Entry<String, Integer> entity : entityCounterMap.entrySet()) {
            if (entity.getValue() < 1) {
                world.appendEntity(entity.getKey());
            }
        }
    }

    private HashMap<String, Integer> countEntities(World world) {
        HashMap<String, Integer> entityCounterMap = new HashMap<>();

        entityCounterMap.put("Money", 0);
        entityCounterMap.put("Employer", 0);
        entityCounterMap.put("Junior", 0);
        entityCounterMap.put("ProgrammingCourse", 0);

        world.getEntitiesOfType(Entity.class).forEach((key, value) -> {
            switch (value.getClass().getSimpleName()) {
                case "Money" -> {
                    int entityCounter = entityCounterMap.get("Money");
                    entityCounterMap.put("Money", entityCounter + 1);
                }

                case "Employer" -> {
                    int entityCounter = entityCounterMap.get("Employer");
                    entityCounterMap.put("Employer", entityCounter + 1);
                }

                case "Junior" -> {
                    int entityCounter = entityCounterMap.get("Junior");
                    entityCounterMap.put("Junior", entityCounter + 1);
                }

                case "ProgrammingCourse" -> {
                    int entityCounter = entityCounterMap.get("ProgrammingCourse");
                    entityCounterMap.put("ProgrammingCourse", entityCounter + 1);
                }
            }
        });

        return entityCounterMap;
    }

    public static void incrementNewJuniorCounter() {
        newJuniorCounter++;
    }

    static void resetNewJuniorCounter() {
        newJuniorCounter = 0;
    }
}
