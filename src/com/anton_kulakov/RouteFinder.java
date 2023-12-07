package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.Person;
import java.util.*;
import static com.anton_kulakov.action.Action.random;

public class RouteFinder {
    public List<Coordinates> findRoute(World world, Coordinates startCoordinates) {
        Set<Coordinates> openSet = new HashSet<>();
        Set<Coordinates> closedSet = new HashSet<>();
        ArrayList<Coordinates> route = new ArrayList<>();

        // Здесь нужно проверить. Метод findTarget может вернуть Coordinates.EMPTY
        Coordinates targetCoordinates = findTarget(world, startCoordinates);
        int rowDifference = targetCoordinates.row - startCoordinates.row;
        int columnDifference = targetCoordinates.column - startCoordinates.column;

        if (ifTargetOnNextCell(rowDifference, columnDifference)) {
            route.add(targetCoordinates);
        } else {
            Coordinates previousCoordinates = startCoordinates;
            Set<Coordinates> neighboringCells = getNeighboringCellsForRoute(world, startCoordinates, targetCoordinates, openSet, closedSet);

            if (neighboringCells.size() > 0) {
                while (!Objects.equals(previousCoordinates, targetCoordinates)) {
                    openSet.addAll(getNeighboringCellsForRoute(world, previousCoordinates, targetCoordinates, openSet, closedSet));
                    closedSet.add(previousCoordinates);

                    calculateFGHValues(previousCoordinates, targetCoordinates, openSet);
                    // Здесь нужно проверить. Метод getNewPreviousCoordinates может вернуть Coordinates.EMPTY
                    previousCoordinates= getNewPreviousCoordinates(openSet, closedSet);
                }

                if (openSet.contains(targetCoordinates)) {
                    targetCoordinates.parent = previousCoordinates;
                    Coordinates elementOfRoute = targetCoordinates;

                    while (!Objects.equals(elementOfRoute, startCoordinates)) {
                        elementOfRoute = elementOfRoute.parent;
                        route.add(elementOfRoute);
                    }
                }
            }
        }

        route.remove(startCoordinates);
        return route;
    }

    private boolean ifTargetOnNextCell(int rowDifference, int columnDifference) {
        return (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 1) ||
               (Math.abs(rowDifference) == 0 && Math.abs(columnDifference) == 1) ||
               (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 0);
    }

    public Set<Coordinates> getNeighboringCellsForRoute(World world, Coordinates previousCoordinates, Coordinates targetCoordinates, Set<Coordinates> openSet, Set<Coordinates> closedSet) {
        Set<Coordinates> neighboringCells = new HashSet<>();

        neighboringCells.add(new Coordinates(previousCoordinates.row + 1, previousCoordinates.column));
        neighboringCells.add(new Coordinates(previousCoordinates.row + 1, previousCoordinates.column + 1));
        neighboringCells.add(new Coordinates(previousCoordinates.row, previousCoordinates.column + 1));
        neighboringCells.add(new Coordinates(previousCoordinates.row - 1, previousCoordinates.column + 1));
        neighboringCells.add(new Coordinates(previousCoordinates.row - 1, previousCoordinates.column));
        neighboringCells.add(new Coordinates(previousCoordinates.row - 1, previousCoordinates.column - 1));
        neighboringCells.add(new Coordinates(previousCoordinates.row - 1, previousCoordinates.column - 1));
        neighboringCells.add(new Coordinates(previousCoordinates.row, previousCoordinates.column - 1));
        neighboringCells.add(new Coordinates(previousCoordinates.row + 1, previousCoordinates.column - 1));

        neighboringCells.removeIf(cell -> !cell.equals(targetCoordinates) && !cell.isPassable(world));
        neighboringCells.removeIf(closedSet::contains);

        for (Coordinates cell : neighboringCells) {
            if (!openSet.contains(cell)) {
                cell.parent = previousCoordinates;
            }
        }

        return neighboringCells;
    }

    public Coordinates getNeighboringCell(World world, Coordinates coordinates) {
        List<Coordinates> neighboringCells = new ArrayList<>();

        neighboringCells.add(new Coordinates(coordinates.row + 1, coordinates.column));
        neighboringCells.add(new Coordinates(coordinates.row + 1, coordinates.column + 1));
        neighboringCells.add(new Coordinates(coordinates.row, coordinates.column + 1));
        neighboringCells.add(new Coordinates(coordinates.row - 1, coordinates.column + 1));
        neighboringCells.add(new Coordinates(coordinates.row - 1, coordinates.column));
        neighboringCells.add(new Coordinates(coordinates.row - 1, coordinates.column - 1));
        neighboringCells.add(new Coordinates(coordinates.row - 1, coordinates.column - 1));
        neighboringCells.add(new Coordinates(coordinates.row, coordinates.column - 1));
        neighboringCells.add(new Coordinates(coordinates.row + 1, coordinates.column - 1));

        neighboringCells.removeIf(cell -> !cell.isPassable(world));

        int randomCell = 0;
        Coordinates neighboringCell = Coordinates.EMPTY;
        if (neighboringCells.size() > 0) {
            randomCell = random.nextInt(neighboringCells.size());
            neighboringCell = neighboringCells.get(randomCell);
        }

        return neighboringCell;
    }

    private Coordinates findTarget(World world, Coordinates startCoordinates) {
        Coordinates target = Coordinates.EMPTY;
        Person person = (Person) world.getEntity(startCoordinates);
        Class<? extends Entity> targetClass = person.getTargetClass();
        int minDistance = 100;
        int distanceFromAtoB;

        for (Entity entity : world.entities.values()) {
            if (entity.getClass().equals(targetClass)) {
                distanceFromAtoB = (int) Math.sqrt(
                        Math.pow((entity.coordinates.row - startCoordinates.row), 2) +
                                Math.pow((entity.coordinates.column - startCoordinates.column), 2)
                );

                if (distanceFromAtoB < minDistance) {
                    target = entity.coordinates;
                    minDistance = distanceFromAtoB;
                }
            }
        }

        return target;
    }

    private void calculateFGHValues(Coordinates previousCoordinates, Coordinates targetCoordinates, Set<Coordinates> openSet) {
        int potentialGValue;

        for (Coordinates coordinates : openSet) {
            if (coordinates.HValue == 0) {
                coordinates.HValue = 10 * (Math.abs(targetCoordinates.row - coordinates.row) + Math.abs(targetCoordinates.column - coordinates.column));
            }

            if (Objects.equals(coordinates.row, coordinates.parent.row) || Objects.equals(coordinates.column, coordinates.parent.column)) {
                potentialGValue = coordinates.parent.GValue + 10;
            } else {
                potentialGValue = coordinates.parent.GValue + 14;
            }

            if (coordinates.GValue == 0 || coordinates.GValue > potentialGValue) {
                coordinates.GValue = potentialGValue;
                coordinates.parent = previousCoordinates;
            }
            
            coordinates.FValue = coordinates.GValue + coordinates.HValue;
        }
    }

    private Coordinates getNewPreviousCoordinates(Set<Coordinates> openSet, Set<Coordinates> closedSet) {
        int minFValue = 100;
        Coordinates newPreviousCoordinates = Coordinates.EMPTY;

        for (Coordinates coordinates : openSet) {
            if (coordinates.FValue < minFValue && !closedSet.contains(coordinates)) {
                minFValue = coordinates.FValue;
                newPreviousCoordinates = coordinates;
            }
        }

        return newPreviousCoordinates;
    }
}
