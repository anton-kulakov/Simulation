package com.anton_kulakov;

import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException, InterruptedException {
                Simulation simulation = new Simulation();
                simulation.startSimulation();

                int counter = 0;
                while (true) {
                        simulation.nextTurn();

                        counter++;
                }
        }
}
