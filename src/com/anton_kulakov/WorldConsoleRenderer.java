package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;

import java.io.IOException;

public class WorldConsoleRenderer {
    private static final ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
    public void render(World world) {
        clearConsole();
        System.out.println();

        for (int row = World.getMaxRows(); row >= 0; row--) {
            String line = row + "|";

            for (int column = 0; column < World.getMaxColumns(); column++) {
                if (world.isCellEmpty(row, column)) {
                    line += "\u26AB";
                } else {
                    line += getEntityImage(world.getEntity(new Coordinates(row, column)));
                }
            }

            System.out.println(line);
        }

        System.out.println();
    }

    private String getEntityImage(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "House" -> "\uD83C\uDFE0";
            case "Money" -> "\uD83D\uDCB0";
            case "ProgrammingCourse" -> "\uD83C\uDF93";
            case "Tree" -> "\uD83C\uDF33";
            case "Employer" -> "\uD83E\uDDD4";
            case "Junior" -> "\uD83D\uDC76";
            default -> "";
        };
    }

    private void clearConsole() {
        String osName = System.getProperty("os.name");

        if (osName.contains("Windows")) {
            try {
                processBuilder.inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.print("\n\033[H\033[2J");
        }
    }
}
