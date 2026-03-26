package game.minesweeper;

import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameState;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GirdOfSquares;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void openingAMineEndsTheGame() {
        GirdOfSquares grid = new GirdOfSquares(2,2);
        Game game = new Game(grid);
        GridInitializer initializer = new GridInitializer(grid);
        Coordinate mine = new Coordinate(1,1);

        initializer.placeMine(mine);
        game.openCell(mine);

        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    public void openingSafeCellRevealsIt(){
        GirdOfSquares grid = new GirdOfSquares(2,2);
        Game game = new Game(grid);
        Coordinate safe = new Coordinate(1,1);


        game.openCell(safe);

        assertTrue(grid.getCell(safe).isRevealed());
    }

    @Test
    public void openingSafeCellContinuesTheGame(){
        GirdOfSquares grid = new GirdOfSquares(2,2);
        Game game = new Game(grid);
        GridInitializer initializer = new GridInitializer(grid);

        Coordinate mine = new Coordinate(1,1);
        Coordinate safe = new Coordinate(2,2);

        initializer.placeMine(mine);


        game.openCell(safe);

        assertEquals(GameState.RUNNING, game.getState());
    }

    @Test
    public void cannotOpenCellAfterGameIsLost(){
        GirdOfSquares grid = new GirdOfSquares(2,2);
        Game game = new Game(grid);
        GridInitializer initializer = new GridInitializer(grid);
        Coordinate mine = new Coordinate(1,1);

        initializer.placeMine(mine);


        game.openCell(mine); //lose

        Coordinate other = new Coordinate(1,2);
        game.openCell(other);
        assertFalse(grid.getCell(other).isRevealed());
    }

    @Test
    void gameIsWonWhenAllSafeCellsAreRevealed() {
        GirdOfSquares grid = new GirdOfSquares(1, 2);
        Game game = new Game(grid);
        GridInitializer initializer = new GridInitializer(grid);

        Coordinate mine = new Coordinate(1,1);
        Coordinate safe = new Coordinate(1,2);

        initializer.placeMine(mine);



        game.openCell(safe);

        assertEquals(GameState.WON, game.getState());
    }

    @Test
    void flaggedCellCannotBeOpened(){
        GirdOfSquares grid = new GirdOfSquares(2, 2);
        Coordinate cell = new Coordinate(1,1);

        Game game = new Game(grid);

        game.toggleFlag(cell);
        game.openCell(cell);

        assertFalse(grid.getCell(cell).isRevealed());
    }

    @Test
    void openingCellWithZeroNeighborMinesRevealsNeighbors() {
        GirdOfSquares grid = new GirdOfSquares(3,3);

        Game game = new Game(grid);

        Coordinate center = new Coordinate(2,2);

        game.openCell(center);

        for(Cell neighbor : grid.getCellNeighbors(center)) {
            assertTrue(neighbor.isRevealed());
        }
    }

    @Test
    void flaggedCellCanBeUnflaggedAndOpened() {
        GirdOfSquares grid = new GirdOfSquares(2,2);
        Coordinate c = new Coordinate(1,1);

        Game game = new Game(grid);

        game.toggleFlag(c);
        game.toggleFlag(c);

        game.openCell(c);

        assertTrue(grid.getCell(c).isRevealed());
    }

}
