package com.anton_kulakov;

import com.anton_kulakov.actions.MakeMoveAction;

public class Simulation {
    private Map map = new Map();
    private int turnsCounter = 0;
    private Renderer renderer;
    MakeMoveAction makeMoveAction = new MakeMoveAction();
    // Actions - список действий, исполняемых перед стартом симуляции или на каждом ходу

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
