package com.anton_kulakov.action;

import com.anton_kulakov.entity.Person;

abstract class PersonCreationAction<T extends Person> extends EntityCreationAction<Person> {
    @Override
    int getEntityAmount() {
        return 4;
    }
}
