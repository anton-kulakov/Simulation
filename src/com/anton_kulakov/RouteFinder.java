package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.Person;

import java.util.*;

public class RouteFinder {
    private Set<Coordinates> openSet = new HashSet<>();
    private Set<Coordinates> closedSet = new HashSet<>();
    private Stack<Coordinates> route = new Stack<>();
    public Stack<Coordinates> findRoute(World world, Coordinates startCoordinates) {

        Coordinates targetCoordinates = findTarget(world, startCoordinates);
        Coordinates previousCoordinates = startCoordinates;

        while (previousCoordinates != targetCoordinates) {
            openSet.addAll(getNeighboringCells(world, previousCoordinates));
            closedSet.add(previousCoordinates);

            calculateFGHValues(openSet, previousCoordinates, targetCoordinates);
            previousCoordinates = getNewPreviousCoordinates(openSet);
        }

        route.push(targetCoordinates);
        Coordinates elementOfRoute = targetCoordinates;

        while (elementOfRoute != startCoordinates) {
            elementOfRoute = elementOfRoute.parent;
            route.push(elementOfRoute);
        }

        return route;
    }

    public Set<Coordinates> getNeighboringCells(World world, Coordinates previousCoordinates) {
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

        neighboringCells.removeIf(cell -> !cell.isPassable(world));
        neighboringCells.removeIf(cell -> closedSet.contains(cell));

        for (Coordinates cell : neighboringCells) {
            if (!openSet.contains(cell)) {
                cell.parent = previousCoordinates;
            }
        }

        return neighboringCells;
    }

    private Coordinates findTarget(World world, Coordinates startCoordinates) {
        Person person = (Person) world.getEntity(startCoordinates);
        Class<? extends Entity> targetClass = person.getTargetClass();
        int minDistance = 100;
        int distanceFromAtoB;

        Coordinates target = null;

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

    private void calculateFGHValues(Set<Coordinates> openSet, Coordinates previousCoordinates, Coordinates targetCoordinates) {
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

    private Coordinates getNewPreviousCoordinates(Set<Coordinates> openSet) {
        int minFValue = 100;
        Coordinates newPreviousCoordinates = null;

        for (Coordinates coordinates : openSet) {
            if (coordinates.FValue < minFValue) {
                minFValue = coordinates.FValue;
                newPreviousCoordinates = coordinates;
            }
        }

        return newPreviousCoordinates;
    }
}
