package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

public class ProgrammingSchool extends Person {
    public ProgrammingSchool(int speed, int hp, int hpRequiredForMove) {
        super(speed, hp, hpRequiredForMove);
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
        Coordinates coordinatesForNewJunior = routeFinder.getNeighboringCell(world, this.coordinates);

        if (coordinatesForNewJunior != null) {
            Junior newJunior = new Junior(3, 20, 2, 3);
            newJunior.coordinates = coordinatesForNewJunior;
            world.entities.put(newJunior.coordinates, newJunior);
        }
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Junior.class;
    }
}
