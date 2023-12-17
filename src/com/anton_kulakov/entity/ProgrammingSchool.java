package com.anton_kulakov.entity;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.World;

public class ProgrammingSchool extends Person {
    public ProgrammingSchool(int speed, int hp, int hpRequiredForMove, boolean isDead) {
        super(speed, hp, hpRequiredForMove, isDead);
    }

    @Override
    void attack(World world, World copyWorld, Coordinates targetCoordinates) {
        Junior targetJunior = (Junior) world.entities.get(targetCoordinates);

        if (targetJunior.getHP() > 15) {
            createNewJunior(world, copyWorld);
            this.changeHP(3);
        }
    }

    private void createNewJunior(World world, World copyWorld) {
        Coordinates coordinatesForNewJunior = world.getNewEntityCoordinates();

        while (copyWorld.entities.containsKey(coordinatesForNewJunior)) {
            coordinatesForNewJunior = world.getNewEntityCoordinates();
        }

        if (coordinatesForNewJunior != Coordinates.EMPTY) {
            Junior newJunior = new Junior(1, 8, 2, false,3);
            newJunior.coordinates = coordinatesForNewJunior;
            copyWorld.entities.put(newJunior.coordinates, newJunior);
        }
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Junior.class;
    }
}
