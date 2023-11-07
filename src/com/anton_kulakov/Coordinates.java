package com.anton_kulakov;

public class Coordinates {
    public final Integer row;
    public final Integer column;

    public Coordinates(Integer row, Integer column) {
        this.row = row;
        this.column = column;
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
}
