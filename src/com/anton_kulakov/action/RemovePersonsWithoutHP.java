package com.anton_kulakov.action;

import com.anton_kulakov.World;
import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.Person;

public class RemovePersonsWithoutHP extends Action {
    @Override
    public void doAction(World world) {
        for (Entity entity : world.entities.values()) {
            if (entity instanceof Person && ((Person) entity).getHP() <= 0) {
                world.entities.remove(entity);
            }
        }
    }
}
