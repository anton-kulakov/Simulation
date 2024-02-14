package com.anton_kulakov;

import java.util.*;

public class RouteFinder {
    public List<Coordinates> getRoute(World world, Coordinates startCoordinates, Coordinates targetCoordinates) {
        List<Coordinates> route = new ArrayList<>();
        int rowDifference = targetCoordinates.getRow() - startCoordinates.getRow();
        int columnDifference = targetCoordinates.getColumn() - startCoordinates.getColumn();

        if (Coordinates.EMPTY.equals(targetCoordinates)) {
            return route;
        }

        if (isTargetOnNextCell(rowDifference, columnDifference)) {
            route.add(targetCoordinates);
        } else {
            route.addAll(findRoute(world, startCoordinates, targetCoordinates));
        }

        route.remove(startCoordinates);
        route.remove(targetCoordinates);

        return route;
    }

    private List<Coordinates> findRoute(World world, Coordinates startCoordinates, Coordinates targetCoordinates) {
        List<Coordinates> route = new ArrayList<>();
        Set<Coordinates> openSet = new HashSet<>();
        Set<Coordinates> closedSet = new HashSet<>();
        Coordinates previousCoordinates = startCoordinates;
        Set<Coordinates> neighboringCells = getNeighboringCells(world, startCoordinates, targetCoordinates, openSet, closedSet);

        if (neighboringCells.size() > 0) {
            while (!Objects.equals(previousCoordinates, targetCoordinates)) {
                openSet.addAll(getNeighboringCells(world, previousCoordinates, targetCoordinates, openSet, closedSet));
                closedSet.add(previousCoordinates);

                previousCoordinates = getNewPreviousCoordinates(previousCoordinates, targetCoordinates, openSet, closedSet);

                if (Coordinates.EMPTY.equals(previousCoordinates)) {
                    break;
                }
            }

            if (openSet.contains(targetCoordinates)) {
                targetCoordinates.setParent(previousCoordinates.getParent());
                Coordinates elementOfRoute = targetCoordinates;

                while (!Objects.equals(elementOfRoute, startCoordinates)) {
                    elementOfRoute = elementOfRoute.getParent();

                    if (!route.contains(elementOfRoute)) {
                        route.add(elementOfRoute);
                    }
                }
            }
        }

        Collections.reverse(route);
        return route;
    }

    private boolean isTargetOnNextCell(int rowDifference, int columnDifference) {
        return (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 1) ||
               (Math.abs(rowDifference) == 0 && Math.abs(columnDifference) == 1) ||
               (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 0);
    }

    private Set<Coordinates> getNeighboringCells(World world, Coordinates previousCoordinates, Coordinates targetCoordinates, Set<Coordinates> openSet, Set<Coordinates> closedSet) {
        Set<Coordinates> neighboringCells = new HashSet<>();

        for (int row = -1; row <= 1; row++) {
            for (int column = -1; column <= 1; column++) {
                if (row == 0 && column == 0) continue;
                neighboringCells.add(new Coordinates(previousCoordinates.getRow() + row, previousCoordinates.getColumn() + column));
            }
        }

        neighboringCells.removeIf(cell -> !cell.equals(targetCoordinates) && !cell.isPassable(world));
        neighboringCells.removeIf(closedSet::contains);

        for (Coordinates cell : neighboringCells) {
            if (!openSet.contains(cell)) {
                cell.setParent(previousCoordinates);
            }
        }

        return neighboringCells;
    }

    private Coordinates getNewPreviousCoordinates(Coordinates previousCoordinates, Coordinates targetCoordinates, Set<Coordinates> openSet, Set<Coordinates> closedSet) {
        int minFValue = Integer.MAX_VALUE;
        Coordinates newPreviousCoordinates = Coordinates.EMPTY;

        for (Coordinates coordinates : openSet) {
            coordinates.calculateFValue(previousCoordinates, targetCoordinates);
            if (coordinates.getFValue() < minFValue && !closedSet.contains(coordinates)) {
                minFValue = coordinates.getFValue();
                newPreviousCoordinates = coordinates;
            }
        }

        return newPreviousCoordinates;
    }
}
