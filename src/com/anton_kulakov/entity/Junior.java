package com.anton_kulakov.entity;

import com.anton_kulakov.World;

public class Junior extends Person {
    private int powerOfAttack;

    public Junior(int speed, int hp, int powerOfAttack) {
        super(speed, hp);
        this.powerOfAttack = powerOfAttack;
    }

    @Override
    void makeMove(World world) {
        // На что может потратить ход хищник:
        //
        // 1. Переместиться (чтобы приблизиться к работодателю)
        // 2. Атаковать работодателя. При этом количество HP работодателя уменьшается
        // на силу атаки джуниора.
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Employer.class;
    }
}
