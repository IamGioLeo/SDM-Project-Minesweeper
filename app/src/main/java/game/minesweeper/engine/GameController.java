package game.minesweeper.engine;
import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;

public class GameController {
    private final Game<Coordinate> game;

    public GameController(Game<Coordinate> game) {
        this.game = game;
    }

    public void open(int row, int column) {
        game.openCell(new Coordinate(row, column));
    }

    public void toggleFlag(int row, int column) {
        game.toggleFlag(new Coordinate(row, column));
    }

    public GameState getGameState() {
        return game.getState();
    }

    public CellView getCellView(int row, int column) {

        Coordinate coordinate = new Coordinate(row, column);
        Cell cell = game.getGrid().getCell(coordinate);

        if (cell == null) return null;

        return new CellView(
                cell.isRevealed(),
                cell.isFlagged(),
                cell.hasMine(),
                cell.neighborsMineCount()
        );
    }


}
