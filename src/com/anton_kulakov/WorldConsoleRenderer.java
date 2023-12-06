package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;

public class WorldConsoleRenderer {
    public void render(World world) {
        System.out.println();

        for (int row = 9; row >= 0; row--) {
            String line = row + "|";
            for (int column = 0; column <= 9; column++) {
                if (world.isCellEmpty(row, column)) {
                    line += "   ";
                } else {
                    line += getEntityImage(world.getEntity(new Coordinates(row, column)));
                }
            }
            System.out.println(line);
        }

        System.out.println();
    }
    private String getEntityImage(Entity entity) {
        String image = switch (entity.getClass().getSimpleName()) {
            case "House" -> "🏠";
            case "Money" -> "\uD83D\uDCB0";
            case "ProgrammingSchool" -> "\uD83C\uDF93";
            case "Tree" -> "\uD83C\uDF33";
            case "Employer" -> "\uD83D\uDC68\u200D\uD83D\uDCBC";
            case "Junior" -> "\uD83D\uDC76";
            default -> "";
        };

        return " " + image + " ";
    }
}
