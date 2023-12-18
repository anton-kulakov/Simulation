package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.RouteFinder;

import java.util.*;

public abstract class Person extends Entity {
    private final int speed;
    private int hp;
    private final int hpRequiredForMove;
    public RouteFinder routeFinder = new RouteFinder();

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
        Coordinates targetCoordinates = findTarget(world, this.coordinates);
        Entity targetEntity = world.entities.get(targetCoordinates);
        List<Coordinates> route = routeFinder.getRoute(world, this.coordinates, targetCoordinates);

        if (!route.isEmpty()) {
            int limit = Math.min(route.size(), this.speed);
            this.coordinates = route.get(limit - 1);

            this.hp -= this.hpRequiredForMove;
        } else if (targetCoordinates != Coordinates.EMPTY){
            attack(targetEntity);
        }

        if (this.getHP() <= 0) {
            this.coordinates = Coordinates.EMPTY;
        }
    }

    public Coordinates findTarget(World world, Coordinates startCoordinates) {
        Coordinates target = Coordinates.EMPTY;
        Class<? extends Entity> targetClass = this.getTargetClass();
        int minDistance = 100;
        int distanceFromAtoB;

        for (Entity entity : world.entities.values()) {
            if (entity.getClass().equals(targetClass)) {
                distanceFromAtoB = (int) Math.sqrt(
                        Math.pow((entity.coordinates.row - startCoordinates.row), 2) +
                        Math.pow((entity.coordinates.column - startCoordinates.column), 2)
                );

                if (distanceFromAtoB < minDistance && entity.coordinates != Coordinates.EMPTY) {
                    target = entity.coordinates;
                    minDistance = distanceFromAtoB;
                }
            }
        }

        return target;
    }

    abstract void attack(Entity targetEntity);

    public abstract Class<? extends Entity> getTargetClass();
}
