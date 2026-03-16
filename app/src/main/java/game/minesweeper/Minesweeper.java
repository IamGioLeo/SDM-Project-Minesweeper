package game.minesweeper;

public class Minesweeper {


    public static void main(String[] args) {

        Grid grid = new Grid(8, 8);

        GridInitializer initializer = new GridInitializer(grid);
        initializer.initialize(10);

        Game game = new Game(grid);
        GameController controller = new GameController(game);

        ConsoleUI ui = new ConsoleUI();
        ui.start(controller, grid);
    }

}
