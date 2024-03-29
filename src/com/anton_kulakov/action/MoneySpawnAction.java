package com.anton_kulakov.action;

import com.anton_kulakov.entity.Money;

public class MoneySpawnAction extends EntitySpawnAction<Money> {
    @Override
    int getEntityAmount() {
        return random.nextInt(4,8);
    }

    @Override
    Money generateEntity() {
        return new Money();
    }
}
