package com.anton_kulakov.entity;

import com.anton_kulakov.action.MakeMove;

public class ProgrammingSchool extends Person {
    public ProgrammingSchool(int speed, int hp, int hpRequiredForMove) {
        super(speed, hp, hpRequiredForMove);
    }

    @Override
    void attack(Entity targetEntity) {
        if (targetEntity instanceof Junior targetJunior) {
            if (targetJunior.getHP() > 15) {
                MakeMove.newJuniorCounter++;
                this.changeHP(3);
            }
        }
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Junior.class;
    }
}
