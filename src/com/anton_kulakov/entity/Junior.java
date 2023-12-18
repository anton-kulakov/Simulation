package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;

public class Junior extends Person {
    private int powerOfAttack;

    public Junior(int speed, int hp, int hpRequiredForMove, int powerOfAttack) {
        super(speed, hp, hpRequiredForMove);
        this.powerOfAttack = powerOfAttack;
    }

    @Override
    void attack(Entity targetEntity) {
        if (targetEntity instanceof Employer targetEmployer) {
            targetEmployer.changeHP(-powerOfAttack);

            if (targetEmployer.getHP() <= 0) {
                targetEmployer.coordinates = Coordinates.EMPTY;
            }
        }
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Employer.class;
    }
}
