package game.minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    @Test
    void openCommandRevealsCell() {

        Grid grid = new Grid(2,2);
        Game game = new Game(grid);
        GameController controller = new GameController(game);

        controller.open(1,1);

        assertTrue(grid.getCell(new Coordinate(1,1)).isRevealed());
    }

    @Test
    void flagCommandFlagsCell() {

        Grid grid = new Grid(2,2);
        Game game = new Game(grid);
        GameController controller = new GameController(game);

        controller.toggleFlag(1,1);

        assertTrue(grid.getCell(new Coordinate(1,1)).isFlagged());
    }
}
