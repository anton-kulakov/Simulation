package com.anton_kulakov;

import com.anton_kulakov.action.*;
import java.util.List;

public class Simulation {
    private Map map = new Map();
    private int turnsCounter = 0;
    private MapRenderer renderer;
    private final List<Action> initActions = List.of(
            // действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существ на карте
            new SetEntitiesAction()
    );
    private final List<Action> turnActions = List.of(
            // действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы или
            // травоядных, если их осталось слишком мало
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
