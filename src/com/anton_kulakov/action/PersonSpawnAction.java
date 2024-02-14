package com.anton_kulakov.action;

import com.anton_kulakov.entity.Person;

abstract class PersonSpawnAction<T extends Person> extends EntitySpawnAction<Person> {
    @Override
    int getEntityAmount() {
        return 4;
    }
}
