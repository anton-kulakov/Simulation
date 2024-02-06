package com.anton_kulakov;

import com.anton_kulakov.entity.*;
import java.util.HashMap;

import static com.anton_kulakov.action.Action.random;

public class World {
    public static final int MAX_ROWS = 9;
    public static final int MAX_COLUMNS = 9;
    public HashMap<Coordinates, Entity> entities = new HashMap<>();

    public boolean isCellEmpty(int row, int column) {
        for (Coordinates coordinates : entities.keySet()) {
            if (coordinates.row == row && coordinates.column == column) {
                return false;
            }
        }
        return true;
    }

    public void addEntity(String entityClass) {
        int newEntityCounter = 0;
        int newEntityLimit = random.nextInt(5);

        while (newEntityCounter < newEntityLimit) {
            Coordinates newEntityCoordinates = getNewEntityCoordinates();

            if (newEntityCoordinates != Coordinates.EMPTY) {
                Entity newEntity = new Entity() {
                };

                switch (entityClass) {
                    case "Money" -> newEntity = new Money();

                    case "Employer" -> newEntity = new Employer(1, 20, 1);

                    case "Junior" -> newEntity = new Junior(1, 16, 2, 1);

                    case "ProgrammingCourse" -> newEntity = new ProgrammingCourse(1, 14, 1, 2);
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
            int row = random.nextInt(MAX_ROWS + 1);
            int column = random.nextInt(MAX_COLUMNS + 1);
            newEntityCoordinates = new Coordinates(row, column);
        }

        return newEntityCoordinates;
    }
}
