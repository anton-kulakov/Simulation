package com.anton_kulakov.entity;

public class Junior extends Person {
    // 👶
    private int powerOfAttack;

    public Junior(int speed, int hp, int powerOfAttack) {
        super(speed, hp);
        this.powerOfAttack = powerOfAttack;
    }

    @Override
    void makeMove() {
        // На что может потратить ход хищник:
        //
        // 1. Переместиться (чтобы приблизиться к работодателю)
        // 2. Атаковать работодателя. При этом количество HP работодателя уменьшается
        // на силу атаки джуниора.
    }
}
