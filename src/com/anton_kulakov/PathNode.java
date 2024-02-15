package com.anton_kulakov;

import java.util.Objects;

public class PathNode extends Coordinates {
    public static final PathNode EMPTY = new PathNode(200, 200);
    private PathNode parent;
    private int FValue = 0;
    private int GValue = 0;
    private int HValue = 0;
    public PathNode(Integer row, Integer column) {
        super(row, column);
    }
    public int getFValue() {
        return FValue;
    }
    public PathNode getParent() {
        return parent;
    }
    public void setParent(PathNode parent) {
        this.parent = parent;
    }

    public void calculateFValue(PathNode previousPathNode, PathNode targetPathNode) {
        int potentialGValue;

        if (HValue == 0) {
            HValue = 10 * (Math.abs(targetPathNode.row - row) + Math.abs(targetPathNode.column - column));
        }

        if (Objects.equals(row, parent.row) || Objects.equals(column, parent.column)) {
            potentialGValue = parent.GValue + 10;
        } else {
            potentialGValue = parent.GValue + 14;
        }

        if (GValue == 0 || GValue > potentialGValue) {
            GValue = potentialGValue;
            parent = previousPathNode;
        }

        FValue = GValue + HValue;
    }
}
