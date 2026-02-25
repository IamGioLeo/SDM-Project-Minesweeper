package game.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    void newGridHasCorrectNumberOfRows() {
        Grid grid = new Grid(9, 9);
        int numberOfRows = grid.getNumberOfRows();
        assertEquals(9, numberOfRows);
    }

    @Test
    void newGridHasCorrectNumberOfColumns() {
        Grid grid = new Grid(9, 9);
        int numberOfColumns = grid.getNumberOfColumns();
        assertEquals(9, numberOfColumns);
    }

    @Test
    void girdCanGetCellWithInts() {
        Grid grid = new Grid(9, 9);
        Cell cell = grid.getCell(6, 7);
        assertNotNull(cell);
    }

    @Test
    void girdCanGetCellWithCoordinate() {
        Grid grid = new Grid(9, 9);
        Coordinate coordinate = new Coordinate(9,9);
        Cell cell = grid.getCell(coordinate);
        assertNotNull(cell);
    }

    @Test
    void newGridDoesNotHaveCellInIncorrectPosition() {
        Grid grid = new Grid(9, 9);
        Cell cell = grid.getCell(67, 67);
        assertNull(cell);
    }

    @Test
    void gridCanFindCorrectAmountOfNeighbors(){
        Grid grid = new Grid(9, 9);

        int angleNeighborCount = grid.getCellNeighbors(1,1).size();
        int boarderNeighborCount = grid.getCellNeighbors(6,1).size();
        int internalNeighborCount = grid.getCellNeighbors(6,7).size();

        assertEquals(3, angleNeighborCount);
        assertEquals(5, boarderNeighborCount);
        assertEquals(8, internalNeighborCount);
    }

    @Test
    void gridHasCorrectAmountOfCells(){
        Grid grid = new Grid(9,9);

        int cellsCount = grid.getAllCells().size();

        assertEquals(81,cellsCount);
    }
}
