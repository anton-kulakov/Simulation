package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;

import java.util.Objects;

public class Coordinates {
    public static final Coordinates EMPTY = new Coordinates(100, 100);
    private final Integer row;
    private final Integer column;
    private Coordinates parent;
    private int FValue = 0;
    private int GValue = 0;
    private int HValue = 0;

    public Coordinates(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public int getFValue() {
        return FValue;
    }
    public Coordinates getParent() {
        return parent;
    }

    public void setParent(Coordinates parent) {
        this.parent = parent;
    }

    public boolean isPassable(World world) {
        for (Entity entity : world.getEntitiesOfType(Entity.class).values()) {
            if (
                    (Objects.equals(entity.coordinates.row, this.row) && Objects.equals(entity.coordinates.column, this.column) ||
                            (this.row < 0 || this.row > World.getMaxRows()) ||
                            (this.column < 0 || this.column > World.getMaxColumns())
                    )) { return false; }
        }
        return true;
    }

    public void calculateFValue(Coordinates previousCoordinates, Coordinates targetCoordinates) {
        int potentialGValue;

        if (HValue == 0) {
            HValue = 10 * (Math.abs(targetCoordinates.row - row) + Math.abs(targetCoordinates.column - column));
        }

        if (Objects.equals(row, parent.row) || Objects.equals(column, parent.column)) {
            potentialGValue = parent.GValue + 10;
        } else {
            potentialGValue = parent.GValue + 14;
        }

        if (GValue == 0 || GValue > potentialGValue) {
            GValue = potentialGValue;
            parent = previousCoordinates;
        }

        FValue = GValue + HValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (!row.equals(that.row)) return false;
        return column.equals(that.column);
    }

    @Override
    public int hashCode() {
        int result = row.hashCode();
        result = 31 * result + column.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
