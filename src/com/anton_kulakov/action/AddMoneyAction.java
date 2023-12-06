package com.anton_kulakov.action;

import com.anton_kulakov.Coordinates;
import com.anton_kulakov.RouteFinder;
import com.anton_kulakov.World;
import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.Money;
import com.anton_kulakov.entity.ProgrammingSchool;

import java.util.Optional;

public class AddMoneyAction extends Action {
    public void doAction(World world) {
        int moneyCounter = 0;
        for (Entity entity : world.entities.values()) {
            if (entity instanceof Money) {
                moneyCounter++;
            }
        }

        if (moneyCounter < 3) {
            int counter = 0;
            while (counter < 4) {
                for (Entity entity : world.entities.values()) {
                    if (entity instanceof ProgrammingSchool) {
                        RouteFinder routeFinder = new RouteFinder();
                        Optional<Coordinates> newMoneyCoordinates = routeFinder.getNeighboringCell(world, entity.coordinates);

                        if (newMoneyCoordinates.isPresent()) {
                            Money newMoney = new Money();
                            newMoney.coordinates = newMoneyCoordinates.get();
                            world.entities.put(newMoney.coordinates, newMoney);

                            counter++;
                        }
                    }
                }
            }
        }
    }
}
