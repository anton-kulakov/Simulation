package com.anton_kulakov;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
                printStartInfo();
                Scanner scanner = new Scanner(System.in);

                while (true) {
                        System.out.println("Введите:");
                        System.out.println("s + Enter - для запуска симуляции");
                        System.out.println("e + Enter - для завершения симуляции");

                        char input = Character.toLowerCase(scanner.next().charAt(0));

                        switch (input) {
                                case 's' -> runSimulation(scanner);
                                case 'e' -> System.exit(0);
                                default -> printIncorrectInputInfo();
                        }
                }
        }

        public static void printStartInfo() {
                System.out.println();
                System.out.println(
                        """
                                        Данная симуляция демонстрирует состояние рынка труда в IT-сфере в 2023 году. 
                                        Компании (\uD83D\uDC68\u200D\uD83D\uDCBC) пытаются как можно быстрее найти деньги венчурных фондов (\uD83D\uDCB0). 
                                        Молодые программисты (\uD83D\uDC76) хотят найти работу, пробиваются на собеседования \s
                                        в компании и тем самым истощают их время и ресурсы. Школы программирования (\uD83C\uDF93) \s
                                        работают над тем, чтобы получить у Junior-разработчиков контакты друзей и \s
                                        родственников с целью продажи своих курсов. Это в итоге приводит к еще большему \s
                                        увеличению количества молодых специалистов на рынке труда и росту конкуренции.
                                """
                );
                System.out.println();
        }

        public static void runSimulation(Scanner scanner) {
                Simulation simulation = new Simulation();
                Thread simulationThread = new Thread(simulation::startSimulation);
                simulationThread.start();

                while (true) {
                        System.out.println("Введите:");
                        System.out.println("p + Enter - чтобы сделать паузу");
                        System.out.println("r + Enter - для возобновления симуляции");
                        System.out.println("e + Enter - для завершения симуляции");

                        char input = Character.toLowerCase(scanner.next().charAt(0));

                        switch (input) {
                                case 'r' -> simulation.resumeSimulation();
                                case 'p' -> {
                                        String currentState = simulation.currentState;
                                        simulation.pauseSimulation();
                                        System.out.println("Текущее состояние: " + currentState);
                                }
                                case 'e' -> System.exit(0);
                                default -> printIncorrectInputInfo();
                        }
                }
        }
        public static void printIncorrectInputInfo() {
                System.out.println("Вы ввели другой символ");
                System.out.println();
        }
}
