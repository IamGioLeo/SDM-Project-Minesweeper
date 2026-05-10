package game.minesweeper.gridTests;

import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;

public class GridOfSquaresTest implements Grid2DContract {

    @Override
    public Coordinate boarderCoordinate() {
        return new Coordinate(5,1);
    }

    @Override
    public int expectedNumberOfCornerNeighbors() {
        return 3;
    }

    @Override
    public int expectedNumberOfBoarderNeighbors() {
        return 5;
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
    public GridOfSquares createGrid() {
        return new GridOfSquares(expectedNumberOfRows(), expectedNumberOfColumns());
    }

    @Override
    public Coordinate validCoordinate() {
        return new Coordinate(6, 7);
    }

    @Override
    public Coordinate invalidCoordinate() {
        return new Coordinate(67, 420);
    }

    @Override
    public Coordinate coordinateWithMaximumNumberOfNeighbors() {
        return new Coordinate(6,7);
    }

    @Override
    public int expectedMaximumNumberOfNeighbors() {
        return 8;
    }
}
