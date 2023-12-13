package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

public class Employer extends Person {

    public Employer(int speed, int hp, int hpRequiredForMove) {
        super(speed, hp, hpRequiredForMove);
    }

    @Override
     void attack(World world, World copyWorld, Coordinates targetCoordinates) {
        world.entities.remove(targetCoordinates);
        this.changeHP(5);
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Money.class;
    }
}
