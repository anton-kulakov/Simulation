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

    public void makeMove(World world, World copyWorld) {
        Coordinates targetCoordinates = findTarget(world, this.coordinates);
        List<Coordinates> route = routeFinder.getRoute(world, copyWorld, this.coordinates, targetCoordinates);

        if (!route.isEmpty()) {
            int limit = Math.min(route.size(), this.speed);
            Coordinates nextStep = route.get(limit - 1);

            copyWorld.entities.put(nextStep, this);
            this.coordinates = nextStep;

            this.hp -= this.hpRequiredForMove;
        } else if (targetCoordinates != Coordinates.EMPTY){
            attack(world, copyWorld, targetCoordinates);
            copyWorld.entities.put(this.coordinates, this);
        }

        if (this.getHP() <= 0) {
            copyWorld.entities.remove(this.coordinates);
        }
    }

    public Coordinates findTarget(World world, Coordinates startCoordinates) {
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

                if (distanceFromAtoB < minDistance && entity.coordinates != Coordinates.EMPTY) {
                    target = entity.coordinates;
                    minDistance = distanceFromAtoB;
                }
            }
        }

        return target;
    }

    abstract void attack(World world, World copyWorld, Coordinates nextStep);

    public abstract Class<? extends Entity> getTargetClass();
}
