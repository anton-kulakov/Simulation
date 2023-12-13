package com.anton_kulakov;

import java.io.IOException;

public class Main {
        public static void main(String[] args) {
                Simulation simulation = new Simulation();
                simulation.startSimulation();

                int counter = 0;
                // симуляция поначалу вроде работает, сущности двигаются
                // но в какой-то момент они все встают в какую-то позицию, что-то происходит в программе
                // и все сущности перестают двигаться. Хотя при этом все сущности в наличии. То есть и хищники и жертвы
                // но они перестают ходить и атаковать. Симуляция замирает
                while (true) {
                        simulation.nextTurn();

                        counter++;
                }
        }
}
