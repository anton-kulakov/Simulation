package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

import java.util.Iterator;
import java.util.Set;

public class ProgrammingSchool extends Person {
    public ProgrammingSchool(int speed, int hp) {
        super(speed, hp);
    }

    @Override
    void attack(World world, Coordinates targetCoordinates) {
        Junior targetEmployer = (Junior) world.entities.get(targetCoordinates);

        if (targetEmployer.getHP() > 5) {
            createNewJunior(world);
            this.changeHP(3);
        }
    }

    private void createNewJunior(World world) {
        Set<Coordinates> neighboringCells = routeFinder.getNeighboringCells(world, this.coordinates);
        Iterator<Coordinates> iterator = neighboringCells.iterator();

        Coordinates coordinatesForNewJunior = iterator.next();
        world.entities.put(coordinatesForNewJunior, new Junior(3, 20, 3));
        world.entities.get(coordinatesForNewJunior).coordinates = coordinatesForNewJunior;
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Junior.class;
    }
}
