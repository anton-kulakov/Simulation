package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.Person;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class RouteFinder {
    private Set<Coordinates> openSet;
    private Set<Coordinates> closedSet;
    public Set<Coordinates> findRoute(World world, Coordinates startCoordinates) {
        Set<Coordinates> route = new LinkedHashSet<>();
        Coordinates targetCoordinates = findTarget(world, startCoordinates);
        Coordinates previousCoordinates = startCoordinates;

        while (previousCoordinates != targetCoordinates) {
            openSet.addAll(getNeighboringCells(world, previousCoordinates));
            closedSet.add(previousCoordinates);
            previousCoordinates = getNextStartCoordinates(openSet, previousCoordinates, targetCoordinates);
        }

        return route;
    }

    private Set<Coordinates> getNeighboringCells(World world, Coordinates startCoordinates) {
        Set<Coordinates> neighboringCells = new HashSet<>();

        neighboringCells.add(new Coordinates(startCoordinates.row + 1, startCoordinates.column));
        neighboringCells.add(new Coordinates(startCoordinates.row + 1, startCoordinates.column + 1));
        neighboringCells.add(new Coordinates(startCoordinates.row, startCoordinates.column + 1));
        neighboringCells.add(new Coordinates(startCoordinates.row - 1, startCoordinates.column + 1));
        neighboringCells.add(new Coordinates(startCoordinates.row - 1, startCoordinates.column));
        neighboringCells.add(new Coordinates(startCoordinates.row - 1, startCoordinates.column - 1));
        neighboringCells.add(new Coordinates(startCoordinates.row - 1, startCoordinates.column - 1));
        neighboringCells.add(new Coordinates(startCoordinates.row, startCoordinates.column - 1));
        neighboringCells.add(new Coordinates(startCoordinates.row + 1, startCoordinates.column - 1));

        neighboringCells.removeIf(cell -> !cell.isPassable(world));
        neighboringCells.removeIf(cell -> closedSet.contains(cell));

        for (Coordinates cell : neighboringCells) {
            cell.parent = startCoordinates;
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
                    target = new Coordinates(entity.coordinates.row, entity.coordinates.column);
                    minDistance = distanceFromAtoB;
                }
            }
        }
        return target;
    }

    private Coordinates getNextStartCoordinates(Set<Coordinates> openSet, Coordinates startCoordinates, Coordinates targetCoordinates) {
        int minFValue = 100;
        Coordinates nextStartCoordinates = null;

        for (Coordinates coordinates : openSet) {
            int GValue = 0;
            int HValue = Math.abs(targetCoordinates.row - coordinates.row) + Math.abs(targetCoordinates.column - coordinates.column);

            if (Objects.equals(coordinates.row, startCoordinates.row) || Objects.equals(coordinates.column, startCoordinates.column)) {
                GValue = 10;
            } else {
                GValue = 14;
            }

            int FValue = GValue + HValue;
            if (FValue < minFValue) {
                minFValue = FValue;
                nextStartCoordinates = coordinates;
            }
        }
        return nextStartCoordinates;
    }
}
