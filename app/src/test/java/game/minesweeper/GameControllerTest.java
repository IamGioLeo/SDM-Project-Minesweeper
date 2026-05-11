package game.minesweeper;

import game.minesweeper.engine.*;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    @Test
    void openCommandRevealsCell() {

        GridOfSquares grid = new GridOfSquares(2,2);
        Game<Coordinate> game = new Game<>(grid);
        GameController controller = new GameController(game);

        controller.open(1,1);

        assertTrue(grid.getCell(new Coordinate(1,1)).isRevealed());
    }

    @Test
    void flagCommandFlagsCell() {

        GridOfSquares grid = new GridOfSquares(2,2);
        Game<Coordinate> game = new Game<>(grid);
        GameController controller = new GameController(game);

        controller.toggleFlag(1,1);

        assertTrue(grid.getCell(new Coordinate(1,1)).isFlagged());
    }

    @Test
    void openMineThroughControllerEndsGame() {

        GridOfSquares grid = new GridOfSquares(2,2);
        Game<Coordinate> game = new Game<>(grid);
        GameController controller = new GameController(game);
        GridInitializer<Coordinate> initializer = new GridInitializer<>(grid);
        Coordinate mine = new Coordinate(1,1);

        initializer.placeMine(mine);
        controller.open(1,1);

        assertEquals(GameState.LOST, controller.getGameState());
    }

    @Test
    void openOperationReturnsBoardChanged() {
        GridOfSquares grid = new GridOfSquares(2,2);
        GameController controller = new GameController(new Game<>(grid));

        CommandResult result = controller.open(1,1);

        assertTrue(result.boardChanged());
    }

    @Test
    void openANonExistingCellThrowsException() {
        GridOfSquares grid = new GridOfSquares(2,2);
        GameController controller = new GameController(new Game<>(grid));

        assertThrows(InvalidCellCoordinateException.class, () -> controller.open(6,7));
    }

    @Test
    void openingInvalidCoordinateThrowsException() {

        GridOfSquares grid = new GridOfSquares(2,2);
        GameController controller =
                new GameController(new Game<>(grid));

        assertThrows(
                InvalidCellCoordinateException.class,
                () -> controller.open(99,99)
        );
    }

    @Test
    void getCellViewWithInvalidCoordinateThrowsException() {

        GridOfSquares grid = new GridOfSquares(2,2);
        GameController controller =
                new GameController(new Game<>(grid));

        assertThrows(
                InvalidCellCoordinateException.class,
                () -> controller.getCellView(-1,4)
        );
    }

}
