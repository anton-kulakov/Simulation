package com.anton_kulakov.entity;

import com.anton_kulakov.action.AppendEntitiesAction;

public class ProgrammingCourse extends Person {
    private int powerOfAttack;

    public ProgrammingCourse(int speed, int hp, int hpRequiredForMove, int powerOfAttack) {
        super(speed, hp, hpRequiredForMove);
        this.powerOfAttack = powerOfAttack;
    }

    @Override
    void attack(Entity targetEntity) {
        if (targetEntity instanceof Junior targetJunior) {
            if (targetJunior.getHP() > 15) {
                AppendEntitiesAction.incrementNewJuniorCounter();
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
