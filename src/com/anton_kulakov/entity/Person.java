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

        ArrayDeque<Coordinates> route = routeFinder.getRoute(world, this.coordinates, targetEntity.coordinates);

        if (!route.isEmpty()) {
            int limit = Math.min(route.size(), this.speed);

            for(int i = 0; i < limit; i++) {
                this.coordinates = route.pollLast();
            }

            this.hp -= this.hpRequiredForMove;

            if (this.getHP() <= 0) {
                this.coordinates = Coordinates.EMPTY;
            }
        } else {
            attack(targetEntity);
        }
    }

    private Entity findTarget(World world, Coordinates startCoordinates) {
        Entity target = new Entity() {};
        target.coordinates = Coordinates.EMPTY;
        int minDistance = 1000;
        int distanceFromStartToTarget;

        for (Entity entity : world.getEntitiesOfType(getTargetClass()).values()) {
            distanceFromStartToTarget =
                    (int) Math.sqrt(Math.pow((entity.coordinates.getRow() - startCoordinates.getRow()), 2) +
                            Math.pow((entity.coordinates.getColumn() - startCoordinates.getColumn()), 2)
                    );

            if (distanceFromStartToTarget < minDistance && entity.coordinates != Coordinates.EMPTY) {
                target = entity;
                minDistance = distanceFromStartToTarget;
            }
        }

        return target;
    }

    abstract void attack(Entity targetEntity);

    abstract Class<? extends Entity> getTargetClass();
}
