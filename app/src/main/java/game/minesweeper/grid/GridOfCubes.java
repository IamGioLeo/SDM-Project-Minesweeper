package game.minesweeper.grid;

import java.util.ArrayList;
import java.util.List;

public class GridOfCubes extends AbstractGrid3D {
    public GridOfCubes(int width, int height, int depth) {
        super(width, height, depth);
    }


    @Override
    public List<Coordinate3D> getNeighborCoordinates(Coordinate3D coordinate) {
        int y = coordinate.y();
        int x = coordinate.x();
        int z = coordinate.z();
        List<Coordinate3D> neighbors = new ArrayList<>();
        for (int dz = -1; dz <= 1; dz++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dz == 0 && dy == 0 && dx == 0) continue;

                    Coordinate3D neighbor = new Coordinate3D(x + dx, y + dy, z + dz);

                    if (cells.containsKey(neighbor)) {
                        neighbors.add(neighbor);
                    }
                }
            }
        }
        return neighbors;
    }
}
