package com.anton_kulakov.action;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.HashMap;
import java.util.Map;

public class AppendEntitiesAction extends Action {
    private static int newJuniorCounter;
    public void perform(World world) {
        HashMap<String, Integer> sumOfEntities = countEntities(world);
        int counter = 0;

        while (counter < newJuniorCounter) {
            world.insertEntities(world.getNewEntityCoordinates(), new Junior(1, 16, 2, 1));
            counter++;
        }

        for (Map.Entry<String, Integer> entityAmount : sumOfEntities.entrySet()) {
            if (entityAmount.getValue() < 1) {
                world.appendEntity(entityAmount.getKey());
            }
        }
    }

    private HashMap<String, Integer> countEntities(World world) {
        HashMap<String, Integer> sumOfEntities = new HashMap<>();

        sumOfEntities.put("Money", 0);
        sumOfEntities.put("Employer", 0);
        sumOfEntities.put("Junior", 0);
        sumOfEntities.put("ProgrammingCourse", 0);

        world.getEntitiesOfType(Entity.class).forEach((key, value) -> {
            switch (value.getClass().getSimpleName()) {
                case "Money" -> {
                    int entityClass = sumOfEntities.get("Money");
                    sumOfEntities.put("Money", entityClass + 1);
                }

                case "Employer" -> {
                    int entityClass = sumOfEntities.get("Employer");
                    sumOfEntities.put("Employer", entityClass + 1);
                }

                case "Junior" -> {
                    int entityClass = sumOfEntities.get("Junior");
                    sumOfEntities.put("Junior", entityClass + 1);
                }

                case "ProgrammingCourse" -> {
                    int entityClass = sumOfEntities.get("ProgrammingCourse");
                    sumOfEntities.put("ProgrammingCourse", entityClass + 1);
                }
            }
        });

        return sumOfEntities;
    }

    public static void incrementNewJuniorCounter() {
        newJuniorCounter++;
    }

    static void resetNewJuniorCounter() {
        newJuniorCounter = 0;
    }
}
