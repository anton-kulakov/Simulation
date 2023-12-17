package com.anton_kulakov;

import java.util.*;

public class RouteFinder {
    public List<Coordinates> getRoute(World world, World copyWorld, Coordinates startCoordinates, Coordinates targetCoordinates) {
        List<Coordinates> route = new ArrayList<>();
        int rowDifference = targetCoordinates.row - startCoordinates.row;
        int columnDifference = targetCoordinates.column - startCoordinates.column;

        if (Coordinates.EMPTY.equals(targetCoordinates)) {
            return route;
        }

        if (ifTargetOnNextCell(rowDifference, columnDifference)) {
            route.add(targetCoordinates);
        } else {
            route.addAll(findRoute(world, copyWorld, startCoordinates, targetCoordinates));
        }

        route.remove(startCoordinates);
        route.remove(targetCoordinates);

        return route;
    }

    public List<Coordinates> findRoute(World world, World copyWorld, Coordinates startCoordinates, Coordinates targetCoordinates) {
        List<Coordinates> route = new ArrayList<>();
        Set<Coordinates> openSet = new HashSet<>();
        Set<Coordinates> closedSet = new HashSet<>();
        Coordinates previousCoordinates = startCoordinates;
        Set<Coordinates> neighboringCells = getNeighboringCellsForRoute(world, copyWorld, startCoordinates, targetCoordinates, openSet, closedSet);

        if (neighboringCells.size() > 0) {
            while (!Objects.equals(previousCoordinates, targetCoordinates)) {
                openSet.addAll(getNeighboringCellsForRoute(world, copyWorld, previousCoordinates, targetCoordinates, openSet, closedSet));
                closedSet.add(previousCoordinates);

                calculateFGHValues(previousCoordinates, targetCoordinates, openSet);
                previousCoordinates = getNewPreviousCoordinates(openSet, closedSet);
                if (Coordinates.EMPTY.equals(previousCoordinates)) {
                    break;
                }
            }

            if (openSet.contains(targetCoordinates)) {
                targetCoordinates.parent = previousCoordinates.parent;
                Coordinates elementOfRoute = targetCoordinates;

                while (!Objects.equals(elementOfRoute, startCoordinates)) {
                    elementOfRoute = elementOfRoute.parent;
                    if (!route.contains(elementOfRoute)) {
                        route.add(elementOfRoute);
                    }
                }
            }
        }

        Collections.reverse(route);
        return route;
    }

    private boolean ifTargetOnNextCell(int rowDifference, int columnDifference) {
        return (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 1) ||
               (Math.abs(rowDifference) == 0 && Math.abs(columnDifference) == 1) ||
               (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 0);
    }

    public Set<Coordinates> getNeighboringCellsForRoute(World world, World copyWorld, Coordinates previousCoordinates, Coordinates targetCoordinates, Set<Coordinates> openSet, Set<Coordinates> closedSet) {
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
