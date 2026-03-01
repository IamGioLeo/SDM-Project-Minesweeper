package game.minesweeper;

public class Game {

    private final Grid grid;
    private GameState gameState = GameState.RUNNING;

    public Game(Grid grid) {
        this.grid = grid;
    }

    public void openCell(Coordinate coordinate) {
        Cell cell = grid.getCell(coordinate);

        if (cell.hasMine()) {
            gameState = GameState.LOST;
            return;
        }

        cell.reveal();

    }

    public GameState getState() {
        return gameState;
    }
}
