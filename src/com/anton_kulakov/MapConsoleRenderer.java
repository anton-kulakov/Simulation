package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;

public class MapConsoleRenderer {
    public void render(Map map) {

        for (int row = 9; row > 0; row--) {
            String line = row + "|";
            for (int column = 1; column <= 11; column++) {
                Coordinates coordinates = new Coordinates(row, column);
                if (map.isCellEmpty(coordinates)) {
                    line += "   ";
                } else {
                    line += getEntityImage(map.getEntity(coordinates));
                }
            }

            System.out.println(line);
        }
    }

    private String getImageForEntity(Entity entity) {
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
        return " " + getImageForEntity(entity) + " ";
    }
}
