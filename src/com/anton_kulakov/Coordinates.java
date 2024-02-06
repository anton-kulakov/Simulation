package com.anton_kulakov;

import com.anton_kulakov.entity.Entity;

import java.util.Objects;

public class Coordinates {
    public static final Coordinates EMPTY = new Coordinates(100, 100);
    public final Integer row;
    public final Integer column;
    Coordinates parent;
    int FValue = 0;
    int GValue = 0;
    int HValue = 0;

    public Coordinates(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    public boolean isPassable(World world) {
        for (Entity entity : world.getCollectionOfEntities()) {
            if (
                    (Objects.equals(entity.coordinates.row, this.row) && Objects.equals(entity.coordinates.column, this.column) ||
                            (this.row < 0 || this.row > World.getMaxRows()) ||
                            (this.column < 0 || this.column > World.getMaxColumns())
                    )) { return false; }
        }
        return true;
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
