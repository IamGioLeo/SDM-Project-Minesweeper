package game.minesweeper.grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridOfTriangles extends AbstractGrid2D {

    private final Set<Coordinate> pointingUpTriangles;
    private final Set<Coordinate> pointingDownTriangles;

    public GridOfTriangles(int numberOfRows, int numberOfColumns) {
        super(numberOfRows, numberOfColumns);
        pointingUpTriangles = new HashSet<>();
        pointingDownTriangles = new HashSet<>();
        for (Coordinate triangleCoordinate : cells.keySet()) {
            if ((triangleCoordinate.row() + triangleCoordinate.column()) % 2 == 0) {
                pointingUpTriangles.add(triangleCoordinate);
            } else {
                pointingDownTriangles.add(triangleCoordinate);
            }
        }
    }

    @Override
    public List<Coordinate> getNeighborCoordinates(Coordinate coordinate) {
        int row = coordinate.row();
        int column = coordinate.column();
        List<Coordinate> neighbors = new ArrayList<>();
        if (pointingUpTriangles.contains(coordinate)) {
            for (int dc = -1; dc <= 1; dc++) {
                Coordinate neighbor = new Coordinate(row - 1, column + dc);
                if (cells.containsKey(neighbor)) neighbors.add(neighbor);
            }

            for (int dc = -2; dc <= 2; dc++) {
                for (int dr = 0; dr <= 1; dr++) {
                    if (dr == 0 && dc == 0) continue;
                    Coordinate neighbor = new Coordinate(row + dr, column + dc);
                    if (cells.containsKey(neighbor)) neighbors.add(neighbor);
                }
            }
        } else {
            for (int dc = -1; dc <= 1; dc++) {
                Coordinate neighbor = new Coordinate(row + 1, column - dc);
                if (cells.containsKey(neighbor)) neighbors.add(neighbor);
            }

            for (int dc = -2; dc <= 2; dc++) {
                for (int dr = -1; dr <= 0; dr++) {
                    if (dr == 0 && dc == 0) continue;
                    Coordinate neighbor = new Coordinate(row + dr, column + dc);
                    if (cells.containsKey(neighbor)) neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }
}
