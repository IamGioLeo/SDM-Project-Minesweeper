package game.minesweeper.gridTests;

import game.minesweeper.grid.AbstractGrid2D;
import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public interface Grid2DContract extends GridContractTest<Coordinate, AbstractGrid2D> {

    default Coordinate cornerCoordinate() {
        return new Coordinate(1, 1);
    }

    Coordinate boarderCoordinate();

    int expectedNumberOfCornerNeighbors();

    int expectedNumberOfBoarderNeighbors();

    int expectedNumberOfRows();

    int expectedNumberOfColumns();

    @Override
    default int expectedTotalCells(){
        return expectedNumberOfRows() * expectedNumberOfColumns();
    }

    default int internalRow() {
        return 1;
    }

    default int internalColumn() {
        return 1;
    }

    @Test
    default void gridHasExpectedNumberOfRows() {
        AbstractGrid2D grid = createGrid();

        assertEquals(expectedNumberOfRows(), grid.getNumberOfRows());
    }

    @Test
    default void gridHasExpectedNumberOfColumns() {
        AbstractGrid2D grid = createGrid();

        assertEquals(expectedNumberOfColumns(), grid.getNumberOfColumns());
    }

    @Test
    default void gridHasExpectedNumberOfCornerNeighbors() {
        AbstractGrid2D grid = createGrid();

        int numberOfCornerNeighbors = grid.getCellNeighbors(cornerCoordinate()).size();

        assertEquals(expectedNumberOfCornerNeighbors(), numberOfCornerNeighbors);
    }

    @Test
    default void gridHasExpectedNumberOfBoarderNeighbors() {
        AbstractGrid2D grid = createGrid();

        int numberOfBoarderNeighbors = grid.getCellNeighbors(boarderCoordinate()).size();

        assertEquals(expectedNumberOfBoarderNeighbors(), numberOfBoarderNeighbors);
    }

    @Test
    default void girdCanGetCellWithInts() {
        AbstractGrid2D grid = createGrid();

        Cell cell = grid.getCell(internalRow(), internalColumn());

        assertNotNull(cell);
    }
}
