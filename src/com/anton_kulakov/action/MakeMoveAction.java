package com.anton_kulakov.action;

import com.anton_kulakov.World;
import com.anton_kulakov.entity.*;

public class MakeMoveAction extends Action {
    public void doAction(World world) {
        for (Entity entity : world.entities.values()) {
            if (entity instanceof Person) {
                ((Person) entity).makeMove(world);
            }
        }
    }
}
