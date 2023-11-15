package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;

public class MapConsoleRenderer {
    public void render(World world) {

        for (int row = 9; row >= 0; row--) {
            String line = row + "|";
            for (int column = 0; column <= 9; column++) {
                Coordinates coordinates = new Coordinates(row, column);
                if (world.isCellEmpty(coordinates)) {
                    line += "   ";
                } else {
                    line += getEntityImage(world.getEntity(coordinates));
                }
            }

            System.out.println(line);
        }
    }

    private String setImageForEntity(Entity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "House":
                return "🏠";

            case "Money":
                return "\uD83D\uDCB0";

            case "ProgrammingCourse":
                return "\uD83C\uDF93";

            case "Tree":
                return "\uD83C\uDF33";

            case "Employer":
                return "\uD83D\uDC68\u200D\uD83D\uDCBC";

            case "Junior":
                return "\uD83D\uDC76";
        }
        return "";
    }
    private String getEntityImage(Entity entity) {
        return " " + setImageForEntity(entity) + " ";
    }
}
