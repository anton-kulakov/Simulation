package com.anton_kulakov.entity;

import com.anton_kulakov.action.MakeMove;

public class ProgrammingSchool extends Person {
    private int powerOfAttack;

    public ProgrammingSchool(int speed, int hp, int hpRequiredForMove, int powerOfAttack) {
        super(speed, hp, hpRequiredForMove);
        this.powerOfAttack = powerOfAttack;
    }

    @Override
    void attack(Entity targetEntity) {
        if (targetEntity instanceof Junior targetJunior) {
            if (targetJunior.getHP() > 15) {
                MakeMove.newJuniorCounter++;
                this.changeHP(3);
            }

            targetJunior.changeHP(-powerOfAttack);
        }
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Junior.class;
    }
}
