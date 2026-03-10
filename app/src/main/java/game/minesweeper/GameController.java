package game.minesweeper;

public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void open(int row, int column) {
        game.openCell(new Coordinate(row, column));
    }
}
