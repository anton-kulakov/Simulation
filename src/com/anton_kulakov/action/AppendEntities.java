package com.anton_kulakov.action;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.HashMap;
import java.util.Map;

public class AppendEntities extends Action {
    private static int newJuniorCounter;
    public void perform(World world) {
        HashMap<String, Integer> sumOfEntities = countEntities(world);
        int counter = 0;

        while (counter < newJuniorCounter) {
            world.insertEntity(world.getNewEntityCoordinates(), new Junior(1, 16, 2, 1));
            counter++;
        }

        for (Map.Entry<String, Integer> entityAmount : sumOfEntities.entrySet()) {
            if (entityAmount.getValue() < 2) {
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

        for (Entity entity : world.getCollectionOfEntities()) {
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

    public static void incrementNewJuniorCounter() {
        newJuniorCounter++;
    }

    public static void setNewJuniorCounter(int i) {
        newJuniorCounter = i;
    }
}
