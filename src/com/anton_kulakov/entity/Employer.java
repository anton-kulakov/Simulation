package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

public class Employer extends Person {
    // Стремятся найти ресурс (деньги венчурного фонда).
    // Может потратить свой ход на движение в сторону денег, либо на их поглощение

    // Количество HP работодателя уменьшается от отсутствия денег и от атак джуниоров
    // Если значение HP работодателя опускается до 0 при отсутствии денег и атак джуниоров, то работодатель исчезает

    public Employer(int speed, int hp) {
        super(speed, hp);
    }

    @Override
     void attack(World world, Coordinates targetCoordinates) {
        world.entities.remove(targetCoordinates);
        this.changeHP(5);
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Money.class;
    }
}
