package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

public class Employer extends Person {

    public Employer(int speed, int hp, int hpRequiredForMove, boolean canBeAttacked) {
        super(speed, hp, hpRequiredForMove, canBeAttacked);
    }

    @Override
     void attack(World world, World copyWorld, Coordinates targetCoordinates) {
        Entity target = world.entities.get(targetCoordinates);
        target.canBeAttacked = false;

        Money newMoney = new Money(true);
        copyWorld.entities.put(targetCoordinates, newMoney);
        newMoney.coordinates = targetCoordinates;

        this.changeHP(5);
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Money.class;
    }
}
