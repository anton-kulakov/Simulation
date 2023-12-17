package com.anton_kulakov;

public class Main {
        public static void main(String[] args) {
                Simulation simulation = new Simulation();
                simulation.startSimulation();

                int counter = 0;


                while (true) {
                        simulation.nextTurn();

                        counter++;
                }
        }
}
