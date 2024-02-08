package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.RouteFinder;

import java.util.*;

public abstract class Person extends Entity {
    private final int speed;
    private int hp;
    private final int hpRequiredForMove;
    private final RouteFinder routeFinder = new RouteFinder();

    public Person(int speed, int hp, int hpRequiredForMove) {
        this.speed = speed;
        this.hp = hp;
        this.hpRequiredForMove = hpRequiredForMove;
    }

    public int getHP() {
        return hp;
    }

    public void changeHP(int hp) {
        this.hp += hp;
    }

    public void makeMove(World world) {
        Entity targetEntity = findTarget(world, this.coordinates);

        if(targetEntity.coordinates == Coordinates.EMPTY) {
            return;
        }

        List<Coordinates> route = routeFinder.getRoute(world, this.coordinates, targetEntity.coordinates);

        if (!route.isEmpty()) {
            int limit = Math.min(route.size(), this.speed);
            this.coordinates = route.get(limit - 1);
            this.hp -= this.hpRequiredForMove;
        } else {
            attack(targetEntity);
        }

        if (this.getHP() <= 0) {
            this.coordinates = Coordinates.EMPTY;
        }
    }

    private Entity findTarget(World world, Coordinates startCoordinates) {
        Entity target = new Entity() {};
        target.coordinates = Coordinates.EMPTY;
        Class<? extends Entity> targetClass = this.getTargetClass();
        int minDistance = 1000;
        int distanceFromStartToTarget;

        for (Entity entity : world.getCollectionOfEntities()) {
            if (entity.getClass().equals(targetClass)) {
                distanceFromStartToTarget =
                        (int) Math.sqrt(Math.pow((entity.coordinates.row - startCoordinates.row), 2) +
                        Math.pow((entity.coordinates.column - startCoordinates.column), 2)
                );

                if (distanceFromStartToTarget < minDistance && entity.coordinates != Coordinates.EMPTY) {
                    target = entity;
                    minDistance = distanceFromStartToTarget;
                }
            }
        }

        return target;
    }

    abstract void attack(Entity targetEntity);

    abstract Class<? extends Entity> getTargetClass();
}
