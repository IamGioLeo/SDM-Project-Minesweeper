package game.minesweeper;

import game.minesweeper.CLI.ConsoleUI;
import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.GirdOfSquares;

public class Minesweeper {


    public static void main(String[] args) {

        GirdOfSquares grid = new GirdOfSquares(8, 8);

        GridInitializer initializer = new GridInitializer(grid);
        initializer.initialize(10);

        Game game = new Game(grid);
        GameController controller = new GameController(game);

        ConsoleUI ui = new ConsoleUI();
        ui.start(controller, grid);
    }

}
