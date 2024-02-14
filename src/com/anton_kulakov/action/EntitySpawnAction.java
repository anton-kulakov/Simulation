package com.anton_kulakov.action;

import com.anton_kulakov.World;
import com.anton_kulakov.entity.Entity;

import java.util.Random;

public abstract class EntitySpawnAction<T extends Entity> extends Action {
    Random random = new Random();
    int entityAmount = getEntityAmount();
    int counter;
    @Override
    public void perform(World world) {
        while (counter < entityAmount) {
            world.insertEntities(world.getNewEntityCoordinates(), generateEntity());
            counter++;
        }
    }

    abstract int getEntityAmount();

    abstract T generateEntity();
}
