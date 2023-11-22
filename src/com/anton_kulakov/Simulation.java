package com.anton_kulakov;

import com.anton_kulakov.action.*;
import java.util.List;

public class Simulation {
    private World world = new World();
    private int turnsCounter = 0;
    private WorldConsoleRenderer renderer;
    private final List<Action> initActions = List.of(
            new SetupDefaultWorldAction(),
            new PrintStartInfoAction()
    );
    private final List<Action> turnActions = List.of(
            new MakeMoveAction(),
            new AddMoneyAction()
    );

    private void nextTurn() {
        // просимулировать и отрендерить один ход
    }

    private void startSimulation() {
        // запустить бесконечный цикл симуляции и рендеринга
    }

    private void pauseSimulation() {
        // приостановить бесконечный цикл симуляции и рендеринга
    }
}
