package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.Person;

import java.util.HashSet;
import java.util.Set;

public class RouteFinder {
    private Set<Coordinates> openSet;
    private Set<Coordinates> closedSet;
    public Set<Coordinates> findRoute(World world, Coordinates startCoordinates) {
        openSet.addAll(getNeighboringCells(world, startCoordinates));
        closedSet.add(startCoordinates);

        return openSet;
    }
    public Coordinates findTarget(World world, Coordinates startCoordinates) {
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
        for (Coordinates cell : neighboringCells) {
            cell.parent = startCoordinates;
        }

        return neighboringCells;
    }
}
