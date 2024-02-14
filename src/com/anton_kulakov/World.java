package com.anton_kulakov;

import com.anton_kulakov.entity.*;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class World {
    private static final int MAX_ROWS = 9;
    private static final int MAX_COLUMNS = 9;
    private static final Random random = new Random();
    private final HashMap<Coordinates, Entity> cells = new HashMap<>();

    public HashMap<Coordinates, Entity> getCells() {
        return cells;
    }

    public static int getMaxRows() {
        return MAX_ROWS;
    }

    public static int getMaxColumns() {
        return MAX_COLUMNS;
    }

    public Entity getEntity(Coordinates coordinates) {
        return cells.get(coordinates);
    }

    public boolean isCellEmpty(int row, int column) {
        for (Coordinates coordinates : cells.keySet()) {
            if (coordinates.getRow() == row && coordinates.getColumn() == column) {
                return false;
            }
        }
        return true;
    }

    public void insertEntities(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        cells.put(coordinates, entity);
    }

    public void insertEntities(HashMap<Coordinates, Entity> cellsCopy) {
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

                insertEntities(newEntityCoordinates, newEntity);
            }

            newEntityCounter++;
        }
    }

    public Coordinates getNewEntityCoordinates() {
        Coordinates newEntityCoordinates = Coordinates.EMPTY;

        while (cells.containsKey(newEntityCoordinates) || newEntityCoordinates.equals(Coordinates.EMPTY)) {
            int row = random.nextInt(MAX_ROWS + 1);
            int column = random.nextInt(MAX_COLUMNS);
            newEntityCoordinates = new Coordinates(row, column);
        }

        return newEntityCoordinates;
    }

    public <T> HashMap<Coordinates, T> getEntitiesOfType(Class<T> type) {
        HashMap<Coordinates, T> map = new HashMap<>();

        for (Entry<Coordinates, Entity> e : cells.entrySet()) {
            if (type.isInstance(e
                    .getValue())) {
                Entry<Coordinates, T> coordinatesTEntry = (Entry<Coordinates, T>) e;
                map.put(coordinatesTEntry.getKey(), coordinatesTEntry.getValue());
            }
        }

        return map;
    }
}
