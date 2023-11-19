package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

public class Junior extends Person {
    private int powerOfAttack = 5;

    public Junior(int speed, int hp, int powerOfAttack) {
        super(speed, hp);
        this.powerOfAttack = powerOfAttack;
    }

    // На что может потратить ход хищник:
    //
    // 1. Переместиться (чтобы приблизиться к работодателю)
    // 2. Атаковать работодателя. При этом количество HP работодателя уменьшается
    // на силу атаки джуниора.

    @Override
    void attack(World world, Coordinates targetCoordinates) {
        Employer targetEmployer = (Employer) world.entities.get(targetCoordinates);
        targetEmployer.changeHP(-powerOfAttack);

        if (targetEmployer.getHp() <= 0) {
            world.entities.remove(targetCoordinates);
        }
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Employer.class;
    }
}
