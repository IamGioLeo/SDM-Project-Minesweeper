package game.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void openingAMineEndsTheGame() {
        Grid grid = new Grid(2,2);
        Coordinate mine = new Coordinate(1,1);
        grid.getCell(mine).placeMine();

        Game game = new Game(grid);
        game.openCell(mine);
        assertEquals(GameState.LOST, game.getState());
    }
}
