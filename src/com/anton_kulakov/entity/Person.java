package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;
import com.anton_kulakov.RouteFinder;

import java.util.List;

public abstract class Person extends Entity {
    private int speed;
    private int hp;
    private int hpRequiredForMove;
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
        List<Coordinates> route = routeFinder.findRoute(world, this.coordinates);
        int nextStepRow = -1;
        int nextStepColumn = -1;

        if (route.size() > 1) {
            route.remove(0);

            int limit = Math.min(route.size(), this.speed);
            for (int step = 0; step < limit; step++) {
                nextStepRow = route.get(route.size() - 1 - step).row;
                nextStepColumn = route.get(route.size() - 1 - step).column;
            }

            Coordinates nextStep = new Coordinates(nextStepRow, nextStepColumn);
            world.entities.put(nextStep, this);
            world.entities.remove(this.coordinates);
            this.coordinates = nextStep;

        } else if (!route.isEmpty()){
            nextStepRow = route.get(0).row;
            nextStepColumn = route.get(0).column;
            attack(world, new Coordinates(nextStepRow, nextStepColumn));
        }

        this.hp -= this.hpRequiredForMove;
    }

    abstract void attack(World world, Coordinates nextStep);

    public abstract Class<? extends Entity> getTargetClass();
}
