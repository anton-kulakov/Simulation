package com.anton_kulakov.action;

import com.anton_kulakov.entity.Employer;
import com.anton_kulakov.entity.Person;

public class EmployerSpawnAction extends PersonSpawnAction<Employer> {
    @Override
    Person generateEntity() {
        return new Employer(1, 20, 1);
    }
}
