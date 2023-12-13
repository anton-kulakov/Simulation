package com.anton_kulakov;

import com.anton_kulakov.action.*;
import java.util.List;

public class Simulation {
    private final World world = new World();
    private final World copyWorld = new World();
    private int turnsCounter = 0;
    private final WorldConsoleRenderer renderer = new WorldConsoleRenderer();
    private final List<Action> initActions = List.of(
            new SetupDefaultWorldAction(),
            new PrintStartInfoAction()
    );
    private final List<Action> turnActions = List.of(
            new MakeMoveAction(),
            new RemovePersonsWithoutHP(),
            new AdjustCurrentWorldStateAction(),
            new AddEntitiesAction()
    );

    public void startSimulation() {
        for (Action action : initActions) {
            action.doAction(world, copyWorld);
        }

        renderer.render(world);
    }
    public void nextTurn() {
        for (Action action : turnActions) {
            action.doAction(world, copyWorld);
        }

        renderer.render(world);

        turnsCounter++;
    }

//    public void pauseSimulation() {
//        // приостановить бесконечный цикл симуляции и рендеринга
//        Thread.onSpinWait();
//        // 23-11-2023 остановился здесь. Нужно понять, как поставить выполнение программы на паузу
//        // в частности, нужно прочитать про метод onSpinWait(). Возможно он поможет
//    }
}
