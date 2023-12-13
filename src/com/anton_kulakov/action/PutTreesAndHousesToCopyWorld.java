package com.anton_kulakov.action;

import com.anton_kulakov.World;
import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.House;
import com.anton_kulakov.entity.Tree;

import java.util.Iterator;

public class PutTreesAndHousesToCopyWorld extends Action {
    @Override
    public void doAction(World world, World copyWorld) {
        Iterator<Entity> iterator = world.entities.values().iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity instanceof House || entity instanceof Tree) {
                copyWorld.entities.put(entity.coordinates, entity);
            }
        }
    }
}
