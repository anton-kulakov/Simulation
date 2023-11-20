package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

public class Junior extends Person {
    private int powerOfAttack;

    public Junior(int speed, int hp, int powerOfAttack) {
        super(speed, hp);
        this.powerOfAttack = powerOfAttack;
    }

    @Override
    void attack(World world, Coordinates targetCoordinates) {
        Employer targetEmployer = (Employer) world.entities.get(targetCoordinates);
        targetEmployer.changeHPAfterAttack(-powerOfAttack);

        if (targetEmployer.getHP() <= 0) {
            world.entities.remove(targetCoordinates);
        }
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Employer.class;
    }
}
