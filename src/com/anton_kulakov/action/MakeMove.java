package com.anton_kulakov.action;

import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;
import java.util.Iterator;

public class MakeMove extends Action {
    public void doAction(World world, World copyWorld) {
        Iterator<Entity> iterator = world.entities.values().iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity instanceof Person) {
                ((Person) entity).makeMove(world, copyWorld);
            }
        }

        world.entities.clear();
        world.entities.putAll(copyWorld.entities);
        copyWorld.entities.clear();
    }
}