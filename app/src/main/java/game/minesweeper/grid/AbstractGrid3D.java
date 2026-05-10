package game.minesweeper.grid;

import java.util.List;

public abstract class AbstractGrid3D extends AbstractGrid<Coordinate3D> {

    protected final int width;
    protected final int height;
    protected final int depth;

    public AbstractGrid3D(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        initCells();
    }

    @Override
    protected void initCells() {
        for (int x = 1; x <= width; x++) {
            for (int y = 1; y <= height; y++) {
                for (int z = 1; z <= depth; z++) {
                    Coordinate3D coordinate = new Coordinate3D(x, y, z);
                    cells.put(coordinate, new Cell());
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public Cell getCell(int x, int y, int z) {
        Coordinate3D coordinate = new Coordinate3D(x, y, z);
        return cells.get(coordinate);
    }

    public List<Cell> getCellNeighbors(int x, int y, int z) {
        Coordinate3D coordinate = new Coordinate3D(x, y, z);
        return getCellNeighbors(coordinate);
    }
}
