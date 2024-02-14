package com.anton_kulakov.action;

import com.anton_kulakov.entity.House;

public class HouseSpawnAction extends EntitySpawnAction<House> {
        @Override
        int getEntityAmount() {
                return random.nextInt(4,7);
        }

        @Override
        House generateEntity() {
                return new House();
        }
}
