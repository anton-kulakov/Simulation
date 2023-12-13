package com.anton_kulakov.action;

import com.anton_kulakov.World;

public class AdjustCurrentWorldStateAction extends Action {
    @Override
    public void doAction(World world, World copyWorld) {
        world.entities.clear();
        world.entities.putAll(copyWorld.entities);
        copyWorld.entities.clear();
    }
}
