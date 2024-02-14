package com.anton_kulakov.action;

import com.anton_kulakov.entity.Junior;

public class JuniorSpawnAction extends PersonSpawnAction<Junior> {
    @Override
    Junior generateEntity() {
        return new Junior(1, 16, 2, 1);
    }
}
