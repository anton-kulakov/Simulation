package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;

public class Employer extends Person {

    public Employer(int speed, int hp, int hpRequiredForMove) {
        super(speed, hp, hpRequiredForMove);
    }

    @Override
     void attack(Entity targetMoney) {
        targetMoney.coordinates = Coordinates.EMPTY;
        this.changeHP(5);
    }

    @Override
    Class<? extends Entity> getTargetClass() {
        return Money.class;
    }
}
