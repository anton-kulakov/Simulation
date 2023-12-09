package com.anton_kulakov;

import com.anton_kulakov.entity.*;
import java.util.concurrent.ConcurrentHashMap;
import static com.anton_kulakov.action.Action.random;

public class World {
    public ConcurrentHashMap<Coordinates, Entity> entities = new ConcurrentHashMap<>();

    public boolean isCellEmpty(int row, int column) {
        for (Coordinates coordinates : entities.keySet()) {
            if (coordinates.row == row && coordinates.column == column) {
                return false;
            }
        }
        return true;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
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

                    case "Employer" -> newEntity = new Employer(2, 10, 3);

                    case "Junior" -> newEntity = new Junior(3, 5, 2, 3);

                    case "ProgrammingSchool" -> newEntity = new ProgrammingSchool(4, 7, 1);
                }
                newEntity.coordinates = newEntityCoordinates;
                this.entities.put(newEntity.coordinates, newEntity);


            }

            newEntityCounter++;
        }
    }

    private Coordinates getNewEntityCoordinates() {
        Coordinates newEntityCoordinates = Coordinates.EMPTY;

        while (this.entities.containsKey(newEntityCoordinates) || Coordinates.EMPTY.equals(newEntityCoordinates)) {
            int row = random.nextInt(10);
            int column = random.nextInt(10);
            newEntityCoordinates = new Coordinates(row, column);
        }

        return newEntityCoordinates;
    }
}
