package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

public class Employer extends Person {

    public Employer(int speed, int hp) {
        super(speed, hp);
    }

    @Override
     void attack(World world, Coordinates targetCoordinates) {
        world.entities.remove(targetCoordinates);
        this.changeHPAfterAttack(5);
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Money.class;
    }
}
