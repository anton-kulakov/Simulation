package com.anton_kulakov;

import com.anton_kulakov.action.SetupDefaultMapAction;
import com.anton_kulakov.entity.Employer;
import com.anton_kulakov.entity.Entity;
import com.anton_kulakov.entity.Junior;
import com.anton_kulakov.entity.Money;

import java.util.Random;
import java.util.Set;

public class Main {
        public static void main(String[] args) {

                World world = new World();
                SetupDefaultMapAction setupDefaultMapAction = new SetupDefaultMapAction();
                setupDefaultMapAction.doAction(world);

                Coordinates c = new Coordinates(5, 5);
                world.entities.put(c, new Employer(1,1));

                RouteFinder r = new RouteFinder();
                Coordinates target = r.findTarget(world, c);

                MapConsoleRenderer renderer = new MapConsoleRenderer();
                renderer.render(world);
                System.out.println(target);
        }
}
