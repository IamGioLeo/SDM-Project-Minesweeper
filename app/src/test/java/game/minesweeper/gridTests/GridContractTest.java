package game.minesweeper.gridTests;

import game.minesweeper.grid.Cell;
import game.minesweeper.grid.CoordinateInterface;
import game.minesweeper.grid.GridInterface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public interface GridContractTest<C extends CoordinateInterface, G extends GridInterface<C>> {

    G createGrid();

    C validCoordinate();

    C invalidCoordinate();

    int expectedTotalCells();

    C coordinateWithMaximumNumberOfNeighbors();

    int expectedMaximumNumberOfNeighbors();

    @Test
    default void allGridCellsExists() {
        G grid = createGrid();


        for (Cell cell : grid.getAllCells()) {
            assertNotNull(cell);
        }
    }

    @Test
    default void allCoordinatesGiveAnExistingCell() {
        G grid = createGrid();


        for (C coordinate : grid.getAllCoordinates()) {
            assertNotNull(grid.getCell(coordinate));
        }
    }

    @Test
    default void gridHasCorrectAmountOfCells() {
        G grid = createGrid();

        int cellsCount = grid.getAllCells().size();

        assertEquals(expectedTotalCells(), cellsCount);
    }

    @Test
    default void gridHasCorrectAmountOfCoordinates() {
        G grid = createGrid();

        int coordinateCount = grid.getAllCoordinates().size();

        assertEquals(expectedTotalCells(), coordinateCount);
    }

    @Test
    default void gridCanGetCellWithCoordinate() {
        G grid = createGrid();
        C coordinate = validCoordinate();

        Cell cell = grid.getCell(coordinate);

        assertNotNull(cell);
    }

    @Test
    default void newGridDoesNotHaveCellInIncorrectPosition() {
        G grid = createGrid();
        C coordinate = invalidCoordinate();

        assertNull(grid.getCell(coordinate));
    }

    @Test
    default void neighborCellsAreAllNonNull() {
        G grid = createGrid();
        C coordinate = validCoordinate();


        for (Cell neighborCell : grid.getCellNeighbors(coordinate)) {
            assertNotNull(neighborCell);
        }
    }

    @Test
    default void neighborCoordinatesAreAllNonNull() {
        G grid = createGrid();
        C coordinate = validCoordinate();


        for (C neighborCoordinate : grid.getNeighborCoordinates(coordinate)) {
            assertNotNull(neighborCoordinate);
        }
    }

    @Test
    default void neighborCoordinatesMatchNeighborCells() {
        G grid = createGrid();
        C coordinate = validCoordinate();


        List<Cell> neighborCells = grid.getCellNeighbors(coordinate);

        List<C> neighborCoordinates = grid.getNeighborCoordinates(coordinate);
        List<Cell> neighborCells2 = new ArrayList<>();
        for (C neighborCoordinate : neighborCoordinates) {
            neighborCells2.add(grid.getCell(neighborCoordinate));
        }


        assertTrue(neighborCells.containsAll(neighborCells2));
        assertTrue(neighborCells2.containsAll(neighborCells));
    }

    @Test
    default void cellWithMaximumNumberOfNeighborsHasCorrectAmountOfNeighbors() {
        G grid = createGrid();

        int maximumNumberOfNeighbors = grid.getNeighborCoordinates(coordinateWithMaximumNumberOfNeighbors()).size();

        assertEquals(expectedMaximumNumberOfNeighbors(), maximumNumberOfNeighbors);
    }
}
