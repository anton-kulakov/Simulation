package com.anton_kulakov.action;

import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

import java.util.HashMap;
import java.util.Map;
public class AddEntitiesAction extends Action {
    public void doAction(World world, World copyWorld) {
        HashMap<String, Integer> sumOfEntities = countEntities(world);

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
        sumOfEntities.put("ProgrammingSchool", 0);

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

                case "ProgrammingSchool" -> {
                    int value = sumOfEntities.get("ProgrammingSchool");
                    sumOfEntities.put("ProgrammingSchool", value + 1);
                }
            }
        }

        return sumOfEntities;
    }
}
