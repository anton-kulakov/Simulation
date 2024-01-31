package com.anton_kulakov;

import com.anton_kulakov.entity.*;
import java.util.HashMap;

import static com.anton_kulakov.action.Action.random;

public class World {
    public HashMap<Coordinates, Entity> entities = new HashMap<>();

    public boolean isCellEmpty(int row, int column) {
        for (Coordinates coordinateInWorld : entities.keySet()) {
            if (coordinateInWorld.row == row && coordinateInWorld.column == column) {
                return false;
            }
        }
        return true;
    }

    public void addEntity(String entityClass) {
        int newEntityCounter = 0;
        int newEntityLimit = random.nextInt(4);

        while (newEntityCounter < newEntityLimit) {
            Coordinates newEntityCoordinates = getNewEntityCoordinates();

            if (newEntityCoordinates != Coordinates.EMPTY) {
                Entity newEntity = new Entity() {
                };

                switch (entityClass) {
                    case "Money" -> newEntity = new Money();

                    case "Employer" -> newEntity = new Employer(1, 10, 3);

                    case "Junior" -> newEntity = new Junior(1, 8, 2, 3);

                    case "ProgrammingSchool" -> newEntity = new ProgrammingSchool(1, 7, 1, 2);
                }

                newEntity.coordinates = newEntityCoordinates;
                this.entities.put(newEntity.coordinates, newEntity);
            }

            newEntityCounter++;
        }
    }

    public Coordinates getNewEntityCoordinates() {
        Coordinates newEntityCoordinates = Coordinates.EMPTY;

        while (!newEntityCoordinates.isPassable(this)) {
            int row = random.nextInt(10);
            int column = random.nextInt(10);
            newEntityCoordinates = new Coordinates(row, column);
        }

        return newEntityCoordinates;
    }
}
