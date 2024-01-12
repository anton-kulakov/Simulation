package com.anton_kulakov;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                ArrayList<Character> inputList = new ArrayList<>(1);
                Simulation simulation = new Simulation();
                Thread simulationThread = new Thread(simulation::startSimulation);
                simulationThread.start();

                while (true) {
                        inputList.clear();

                        if (scanner.hasNext()) {
                                inputList.add(0, scanner.next().charAt(0));
                        }

                        if (simulation.isPaused && inputList.size() > 0 && ('r' == Character.toLowerCase(inputList.get(0)))) {
                                simulation.resumeSimulation();
                        } else if (inputList.size() > 0 && ('p' == Character.toLowerCase(inputList.get(0)))) {
                                String currentState = simulation.currentState;
                                simulation.pauseSimulation();
                                System.out.println("Текущее состояние: " + currentState);
                                System.out.println("Введите r, чтобы возобновить симуляцию");
                        } else {
                                System.out.println("Вы ввели другой символ. ");
                                System.out.println("Для паузы введите \"p\" ");
                                System.out.println("Для возобновления симуляции введите \"r\" ");
                                System.out.println();
                        }
                }
        }
}
