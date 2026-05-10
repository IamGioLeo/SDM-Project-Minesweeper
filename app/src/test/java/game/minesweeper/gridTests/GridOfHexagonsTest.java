package game.minesweeper.gridTests;

import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfHexagons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GridOfHexagonsTest implements Grid2DContract {

    @Override
    public int expectedNumberOfCornerNeighbors() {
        return 2;
    }

    @Override
    public Coordinate borderCoordinate() {
        return new Coordinate(5,1);
    }

    @Override
    public int expectedNumberOfBorderNeighbors() {
        return 4;
    }

    @Override
    public int expectedNumberOfRows() {
        return 9;
    }

    @Override
    public int expectedNumberOfColumns() {
        return 9;
    }

    @Override
    public GridOfHexagons createGrid() {
        return new GridOfHexagons(expectedNumberOfColumns(), expectedNumberOfRows());
    }

    @Override
    public Coordinate validCoordinate() {
        return new Coordinate(6, 7);
    }

    @Override
    public Coordinate invalidCoordinate() {
        return new Coordinate(42, 42);
    }

    @Override
    public Coordinate coordinateWithMaximumNumberOfNeighbors() {
        return new Coordinate(6, 7);
    }

    @Override
    public int expectedMaximumNumberOfNeighbors() {
        return 6;
    }

    @Test
    public void bottomLeftCornerHasCorrectNumberOfNeighbors() {
        GridOfHexagons grid = createGrid();
        Coordinate coordinate = new Coordinate(9, 1);

        int numberOfNeighbors = grid.getCellNeighbors(coordinate).size();

        assertEquals(3,  numberOfNeighbors);
    }

    @Test
    public void topBorderCellsHaveCorrectNumberOfNeighbors() {
        GridOfHexagons grid = createGrid();
        Coordinate coordinate = new Coordinate(1, 5);
        Coordinate coordinate2 = new Coordinate(1, 4);

        int numberOfNeighbors = grid.getCellNeighbors(coordinate).size();
        int numberOfNeighbors2 = grid.getCellNeighbors(coordinate2).size();

        assertEquals(3,  numberOfNeighbors);
        assertEquals(5,  numberOfNeighbors2);
    }

    @Test
    public void leftBorderCellHaveCorrectNumberOfNeighbors() {
        GridOfHexagons grid = createGrid();
        Coordinate coordinate = new Coordinate(4, 1);
        Coordinate coordinate2 = new Coordinate(5, 1);

        int numberOfNeighbors = grid.getCellNeighbors(coordinate).size();
        int numberOfNeighbors2 = grid.getCellNeighbors(coordinate2).size();

        assertEquals(4,  numberOfNeighbors);
        assertEquals(4,  numberOfNeighbors2);
    }
}
