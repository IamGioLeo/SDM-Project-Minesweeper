package game.minesweeper;


import java.util.List;

public interface GridInterface<C extends CoordinateInterface> {

    void initCells();

    Cell getCell(C coordinate);

    List<Cell> getCellNeighbors(C coordinate);

    List<Cell> getAllCells();

    public List<C> getAllCoordinates();
}
