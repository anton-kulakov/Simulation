package com.anton_kulakov.action;

import com.anton_kulakov.World;
import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.Person;

import java.util.Iterator;

public class RemovePersonsWithoutHP extends Action {
    @Override
    public void doAction(World world, World copyWorld) {
        Iterator<Entity> iterator = copyWorld.entities.values().iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            if (entity instanceof Person && ((Person) entity).getHP() <= 0) {
                iterator.remove();
            }
        }
    }
}
