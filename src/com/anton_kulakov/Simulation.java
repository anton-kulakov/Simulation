package com.anton_kulakov;

import com.anton_kulakov.action.*;

import java.util.List;

public class Simulation {
    private final World world = new World();
    private int turnsCounter = 0;
    private final WorldConsoleRenderer renderer = new WorldConsoleRenderer();
    public boolean isPaused = false;
    private final List<Action> initActions = List.of(
            new SetupDefaultWorld()
    );
    private final List<Action> turnActions = List.of(
            new MakeMove(),
            new AppendEntities()
    );

    public void startSimulation() {
        for (Action action : initActions) {
            action.doAction(world);
        }

        renderer.render(world);

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nextTurn();
    }
    public synchronized void nextTurn() {
        while (true) {
            turnsCounter++;

            if (this.isPaused) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (Action action : turnActions) {
                action.doAction(world);
            }

            renderer.render(world);

            System.out.println("Нажмите:");
            System.out.println("Enter - чтобы сделать паузу");
            System.out.println("e + Enter - для завершения программы");
            System.out.println();

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void pauseSimulation() {
        this.isPaused = true;
        renderer.render(world);
    }

    public synchronized void resumeSimulation() {
        this.isPaused = false;
        notify();
    }
}
