package game.minesweeper.grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridOfHexagons extends AbstractGrid2D {

    private final Set<Coordinate> upLineHexagons;
    private final Set<Coordinate> downLineHexagons;

    public GridOfHexagons(int width, int height) {
        super(width, height);

        upLineHexagons = new HashSet<>();
        downLineHexagons = new HashSet<>();
        for (Coordinate exagonCoordinate : cells.keySet()) {
            if ((exagonCoordinate.row() + exagonCoordinate.column()) % 2 == 0) {
                upLineHexagons.add(exagonCoordinate);
            } else {
                downLineHexagons.add(exagonCoordinate);
            }
        }
    }

    @Override
    public List<Coordinate> getNeighborCoordinates(Coordinate coordinate) {
        int row = coordinate.row();
        int column = coordinate.column();
        List<Coordinate> neighbors = new ArrayList<>();

        for (int d = -1; d <= 1; d++) {
            if (d == 0) continue;
            Coordinate columnNeighbor = new Coordinate(row, column + d);
            Coordinate rowNeighbor = new Coordinate(row + d, column);
            if (cells.containsKey(columnNeighbor)) neighbors.add(columnNeighbor);
            if (cells.containsKey(rowNeighbor)) neighbors.add(rowNeighbor);
        }

        if (upLineHexagons.contains(coordinate)) {
            neighbors.add(new Coordinate(row - 1, column - 1));
            neighbors.add(new Coordinate(row - 1, column + 1));
        } else if (downLineHexagons.contains(coordinate)) {
            neighbors.add(new Coordinate(row + 1, column - 1));
            neighbors.add(new Coordinate(row + 1, column + 1));
        }
        return neighbors;
    }
}
