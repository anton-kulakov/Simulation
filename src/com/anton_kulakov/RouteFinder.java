package com.anton_kulakov;

import java.util.*;

public class RouteFinder {
    public ArrayDeque<Coordinates> getRoute(World world, Coordinates startCell, Coordinates targetCell) {
        PathNode startPathNode = new PathNode(startCell.getRow(), startCell.getColumn());
        PathNode targetPathNode = new PathNode(targetCell.getRow(), targetCell.getColumn());

        ArrayDeque<PathNode> pathNodeRoute = new ArrayDeque<>();
        ArrayDeque<Coordinates> route = new ArrayDeque<>();

        if (Coordinates.EMPTY.equals(targetCell)) {
            return route;
        }

        if (isTargetOnNextCell(startPathNode, targetPathNode)) {
            pathNodeRoute.add(targetPathNode);
        } else {
            pathNodeRoute.addAll(findRoute(world, startPathNode, targetPathNode));
        }

        pathNodeRoute.remove(startPathNode);
        pathNodeRoute.remove(targetPathNode);

        for (PathNode pathNode : pathNodeRoute) {
            route.add(new Coordinates(pathNode.getRow(), pathNode.getColumn()));
        }

        return route;
    }

    private ArrayDeque<PathNode> findRoute(World world, PathNode startPathNode, PathNode targetPathNode) {
        ArrayDeque<PathNode> route = new ArrayDeque<>();
        Set<PathNode> openSet = new HashSet<>();
        Set<PathNode> closedSet = new HashSet<>();
        PathNode previousPathNode = startPathNode;
        Set<PathNode> neighboringPathNodes = getNeighboringPathNodes(world, startPathNode, targetPathNode, openSet, closedSet);

        if (neighboringPathNodes.size() > 0) {
            while (!Objects.equals(previousPathNode, targetPathNode)) {
                openSet.addAll(getNeighboringPathNodes(world, previousPathNode, targetPathNode, openSet, closedSet));
                closedSet.add(previousPathNode);

                previousPathNode = getNewPreviousPathNode(previousPathNode, targetPathNode, openSet, closedSet);

                if (PathNode.EMPTY.equals(previousPathNode)) {
                    break;
                }
            }

            if (openSet.contains(targetPathNode)) {
                targetPathNode.setParent(previousPathNode.getParent());
                PathNode elementOfRoute = targetPathNode;

                while (!Objects.equals(elementOfRoute, startPathNode)) {
                    elementOfRoute = elementOfRoute.getParent();

                    if (!route.contains(elementOfRoute)) {
                        route.add(elementOfRoute);
                    }
                }
            }
        }

        return route;
    }

    private boolean isTargetOnNextCell(PathNode startPathNode, PathNode targetPathNode) {
        int rowDifference = targetPathNode.getRow() - startPathNode.getRow();
        int columnDifference = targetPathNode.getColumn() - startPathNode.getColumn();

        return (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 1) ||
               (Math.abs(rowDifference) == 0 && Math.abs(columnDifference) == 1) ||
               (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 0);
    }

    private Set<PathNode> getNeighboringPathNodes(World world, PathNode previousPathNode, PathNode targetPathNode, Set<PathNode> openSet, Set<PathNode> closedSet) {
        Set<PathNode> neighboringPathNodes = new HashSet<>();

        for (int row = -1; row <= 1; row++) {
            for (int column = -1; column <= 1; column++) {
                if (row == 0 && column == 0) continue;
                neighboringPathNodes.add(new PathNode(previousPathNode.getRow() + row, previousPathNode.getColumn() + column));
            }
        }

        neighboringPathNodes.removeIf(cell -> !cell.equals(targetPathNode) && !cell.isPassable(world));
        neighboringPathNodes.removeIf(closedSet::contains);

        for (PathNode pathNode : neighboringPathNodes) {
            if (!openSet.contains(pathNode)) {
                pathNode.setParent(previousPathNode);
            }
        }

        return neighboringPathNodes;
    }

    private PathNode getNewPreviousPathNode(PathNode previousPathNode, PathNode targetPathNode, Set<PathNode> openSet, Set<PathNode> closedSet) {
        int minMoveCost = Integer.MAX_VALUE;
        PathNode newPreviousPathNode = PathNode.EMPTY;

        for (PathNode pathNode : openSet) {
            pathNode.calculateMoveCost(previousPathNode, targetPathNode);

            if (pathNode.getMoveCost() < minMoveCost && !closedSet.contains(pathNode)) {
                minMoveCost = pathNode.getMoveCost();
                newPreviousPathNode = pathNode;
            }
        }

        return newPreviousPathNode;
    }
}
