package com.anton_kulakov;

import com.anton_kulakov.entity.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import static com.anton_kulakov.action.Action.random;

public class World {
    private static final int MAX_ROWS = 9;
    private static final int MAX_COLUMNS = 9;
    private final HashMap<Coordinates, Entity> cells = new HashMap<>();

    public static int getMaxRows() {
        return MAX_ROWS;
    }

    public static int getMaxColumns() {
        return MAX_COLUMNS;
    }

    public Entity getEntity(Coordinates coordinates) {
        return cells.get(coordinates);
    }

    public Collection<Entity> getCollectionOfEntities() {
        return cells.values();
    }

    public Iterator<Entity> getEntitiesIterator() {
        return getCollectionOfEntities().iterator();
    }

    public boolean isCellEmpty(int row, int column) {
        for (Coordinates coordinates : cells.keySet()) {
            if (coordinates.row == row && coordinates.column == column) {
                return false;
            }
        }
        return true;
    }

    public void insertEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        cells.put(coordinates, entity);
    }

    public void insertEntity(HashMap<Coordinates, Entity> cellsCopy) {
        cells.putAll(cellsCopy);
    }

    public void clearWorld() {
        cells.clear();
    }

    public void appendEntity(String entityClass) {
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

                insertEntity(newEntityCoordinates, newEntity);
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
