package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.RouteFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Person extends Entity {
    private int speed;
    private int hp;
    public RouteFinder routeFinder;

    public Person(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public int getHP() {
        return hp;
    }

    public void changeHPAfterAttack(int hp) {
        this.hp += hp;
    }

    void makeMove(World world) {
        Stack<Coordinates> route = routeFinder.findRoute(world, this.coordinates);
        List<Coordinates> nextStepsList = new ArrayList<>();
        for (int step = 0; step < this.speed; step++) {
            nextStepsList.add(route.peek());
        }
        Coordinates nextStep = nextStepsList.get(nextStepsList.size() - 1);

        if (!world.isCellEmpty(nextStep) && this.getTargetClass().equals(world.entities.get(nextStep).getClass())) {
            attack(world, nextStep);
        } else {
            world.entities.put(nextStep, this);
            world.entities.remove(this.coordinates);
            this.coordinates = nextStep;
        }
    }

    abstract void attack(World world, Coordinates nextStep);

    public abstract Class<? extends Entity> getTargetClass();
}
