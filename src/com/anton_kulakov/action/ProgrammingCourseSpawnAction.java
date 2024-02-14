package com.anton_kulakov.action;

import com.anton_kulakov.entity.Person;
import com.anton_kulakov.entity.ProgrammingCourse;

public class ProgrammingCourseSpawnAction extends PersonSpawnAction<ProgrammingCourse> {
    @Override
    Person generateEntity() {
        return new ProgrammingCourse(1, 14, 1, 2);
    }
}
