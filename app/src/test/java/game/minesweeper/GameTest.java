package game.minesweeper;

import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameState;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void openingAMineEndsTheGame() {
        GridOfSquares grid = new GridOfSquares(2, 2);
        Game<Coordinate> game = new Game<>(grid);
        GridInitializer<Coordinate> initializer = new GridInitializer<>(grid);
        Coordinate mine = new Coordinate(1, 1);

        initializer.placeMine(mine);
        game.openCell(mine);

        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    public void openingSafeCellRevealsIt() {
        GridOfSquares grid = new GridOfSquares(2, 2);
        Game<Coordinate> game = new Game<>(grid);
        Coordinate safe = new Coordinate(1, 1);


        game.openCell(safe);

        assertTrue(grid.getCell(safe).isRevealed());
    }

    @Test
    public void openingSafeCellContinuesTheGame() {
        GridOfSquares grid = new GridOfSquares(2, 2);
        Game<Coordinate> game = new Game<>(grid);
        GridInitializer<Coordinate> initializer = new GridInitializer<>(grid);

        Coordinate mine = new Coordinate(1, 1);
        Coordinate safe = new Coordinate(2, 2);

        initializer.placeMine(mine);


        game.openCell(safe);

        assertEquals(GameState.RUNNING, game.getState());
    }

    @Test
    public void cannotOpenCellAfterGameIsLost() {
        GridOfSquares grid = new GridOfSquares(2, 2);
        Game<Coordinate> game = new Game<>(grid);
        GridInitializer<Coordinate> initializer = new GridInitializer<>(grid);
        Coordinate mine = new Coordinate(1, 1);

        initializer.placeMine(mine);


        game.openCell(mine);

        Coordinate other = new Coordinate(1, 2);
        game.openCell(other);
        assertFalse(grid.getCell(other).isRevealed());
    }

    @Test
    void gameIsWonWhenAllSafeCellsAreRevealed() {
        GridOfSquares grid = new GridOfSquares(1, 2);
        Game<Coordinate> game = new Game<>(grid);
        GridInitializer<Coordinate> initializer = new GridInitializer<>(grid);

        Coordinate mine = new Coordinate(1, 1);
        Coordinate safe = new Coordinate(1, 2);

        initializer.placeMine(mine);


        game.openCell(safe);

        assertEquals(GameState.WON, game.getState());
    }

    @Test
    void flaggedCellCannotBeOpened() {
        GridOfSquares grid = new GridOfSquares(2, 2);
        Coordinate cell = new Coordinate(1, 1);

        Game<Coordinate> game = new Game<>(grid);

        game.toggleFlag(cell);
        game.openCell(cell);

        assertFalse(grid.getCell(cell).isRevealed());
    }

    @Test
    void openingCellWithZeroNeighborMinesRevealsNeighbors() {
        GridOfSquares grid = new GridOfSquares(3, 3);

        Game<Coordinate> game = new Game<>(grid);

        Coordinate center = new Coordinate(2, 2);

        game.openCell(center);

        for (Cell neighbor : grid.getCellNeighbors(center)) {
            assertTrue(neighbor.isRevealed());
        }
    }

    @Test
    void flaggedCellCannotBeOpenedInASequenceOfOpenings() {
        GridOfSquares grid = new GridOfSquares(2, 2);
        Coordinate flaggedCell = new Coordinate(1, 1);
        Coordinate cellToOpen = new Coordinate(1, 1);

        Game<Coordinate> game = new Game<>(grid);

        game.toggleFlag(flaggedCell);
        game.openCell(cellToOpen);

        assertFalse(grid.getCell(flaggedCell).isRevealed());
    }

    @Test
    void flaggedCellCanBeUnflaggedAndOpened() {
        GridOfSquares grid = new GridOfSquares(2, 2);
        Coordinate c = new Coordinate(1, 1);

        Game<Coordinate> game = new Game<>(grid);

        game.toggleFlag(c);
        game.toggleFlag(c);

        game.openCell(c);

        assertTrue(grid.getCell(c).isRevealed());
    }

    @Test
    void revealedCellCannotBeFlagged() {

        GridOfSquares grid = new GridOfSquares(2,2);
        Game<Coordinate> game = new Game<>(grid);

        Coordinate c = new Coordinate(1,1);

        game.openCell(c);
        game.toggleFlag(c);

        assertFalse(grid.getCell(c).isFlagged());
    }

}
