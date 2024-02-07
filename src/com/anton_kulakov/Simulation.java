package com.anton_kulakov;

import com.anton_kulakov.action.*;

import java.util.List;

public class Simulation {
    private final World world = new World();
    private final WorldConsoleRenderer renderer = new WorldConsoleRenderer();
    public boolean isPaused = false;
    private final List<Action> initActions = List.of(
            new SetupDefaultWorldAction()
    );
    private final List<Action> turnActions = List.of(
            new MakeMoveAction(),
            new AppendEntitiesAction()
    );

    public void startSimulation() {
        for (Action action : initActions) {
            action.perform(world);
        }

        renderer.render(world);
        this.sleep();

        nextTurn();
    }
    public synchronized void nextTurn() {
        while (true) {

            if (this.isPaused) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (Action action : turnActions) {
                action.perform(world);
            }

            renderer.render(world);

            System.out.println("Нажмите:");
            System.out.println("Enter - чтобы сделать паузу");
            System.out.println("e + Enter - для завершения программы");
            System.out.println();

            this.sleep();

        }
    }

    private void sleep() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
