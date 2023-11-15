package com.anton_kulakov.entity;

import com.anton_kulakov.World;
import com.anton_kulakov.RouteFinder;

public abstract class Person extends Entity {
    private int speed;
    private int hp;
    public RouteFinder routeFinder;

    public Person(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    abstract void makeMove(World world);

    public abstract Class<? extends Entity> getTargetClass();
}
