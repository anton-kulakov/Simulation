package com.anton_kulakov;

import java.util.Objects;

public class PathNode extends Coordinates {
    public static final PathNode EMPTY = new PathNode(200, 200);
    private PathNode parent;
    private int moveCost = 0;
    private int moveFromPrevCellToThisCost = 0;
    private int moveFromThisToTargetCellCost = 0;
    public PathNode(Integer row, Integer column) {
        super(row, column);
    }
    public int getMoveCost() {
        return moveCost;
    }
    public PathNode getParent() {
        return parent;
    }
    public void setParent(PathNode parent) {
        this.parent = parent;
    }

    public void calculateMoveCost(PathNode previousPathNode, PathNode targetPathNode) {
        int potentialMoveFromPrevCellToThisCost;

        if (moveFromThisToTargetCellCost == 0) {
            moveFromThisToTargetCellCost = 10 * (Math.abs(targetPathNode.row - row) + Math.abs(targetPathNode.column - column));
        }

        if (Objects.equals(row, parent.row) || Objects.equals(column, parent.column)) {
            potentialMoveFromPrevCellToThisCost = parent.moveFromPrevCellToThisCost + 10;
        } else {
            potentialMoveFromPrevCellToThisCost = parent.moveFromPrevCellToThisCost + 14;
        }

        if (moveFromPrevCellToThisCost == 0 || moveFromPrevCellToThisCost > potentialMoveFromPrevCellToThisCost) {
            moveFromPrevCellToThisCost = potentialMoveFromPrevCellToThisCost;
            parent = previousPathNode;
        }

        moveCost = moveFromPrevCellToThisCost + moveFromThisToTargetCellCost;
    }
}
