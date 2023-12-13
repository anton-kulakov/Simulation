package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

public class Junior extends Person {
    private int powerOfAttack;

    public Junior(int speed, int hp, int hpRequiredForMove, int powerOfAttack) {
        super(speed, hp, hpRequiredForMove);
        this.powerOfAttack = powerOfAttack;
    }

    @Override
    void attack(World world, World copyWorld, Coordinates targetCoordinates) {
        Employer targetEmployer = (Employer) world.entities.get(targetCoordinates);
        targetEmployer.changeHP(-powerOfAttack);
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Employer.class;
    }
}
