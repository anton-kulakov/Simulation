package com.anton_kulakov.action;

import com.anton_kulakov.World;
import com.anton_kulakov.entity.Employer;
import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.Junior;

public class ReduceHPAfterTurnAction extends Action {
    @Override
    void doAction(World world) {
        for (Entity entity : world.entities.values()) {
            if (entity instanceof Employer) {
                ((Employer) entity).changeHP(-3);
            } else if (entity instanceof Junior) {
                ((Junior) entity).changeHP(-2);
            }
        }
    }
}
