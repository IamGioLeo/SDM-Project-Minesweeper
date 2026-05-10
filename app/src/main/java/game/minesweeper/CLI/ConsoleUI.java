package game.minesweeper.CLI;

import game.minesweeper.engine.CommandResult;
import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GameState;
import game.minesweeper.engine.InvalidCellCoordinateException;
import game.minesweeper.grid.GridOfSquares;

import java.util.Scanner;

public class ConsoleUI {

    public void start(GameController controller, GridOfSquares grid) {

        Scanner scanner = new Scanner(System.in);

        GridPrinter.print(controller, grid);

        System.out.println("Enter command to open a cell or to place/remove a flag:");
        System.out.println("open(o) row col | flag(f) row col");

        gameLoop:
        while (controller.getGameState() == GameState.RUNNING) {

            String command = scanner.next().toLowerCase();

            int row;
            int col;

            switch (command) {
                case "open", "o": {
                    row = scanner.nextInt();
                    col = scanner.nextInt();

                    try {
                        CommandResult result = controller.open(row, col);
                        if (result.boardChanged()) {
                            GridPrinter.print(controller, grid);
                        }
                    } catch (InvalidCellCoordinateException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case "flag", "f": {
                    row = scanner.nextInt();
                    col = scanner.nextInt();

                    try {
                        CommandResult result = controller.toggleFlag(row, col);
                        if (result.boardChanged()) {
                            GridPrinter.print(controller, grid);
                        }
                    } catch (InvalidCellCoordinateException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case "help":
                    System.out.println("Enter command to open a cell or to place/remove a flag:");
                    System.out.println("open(o) row col | flag(f) row col");
                    System.out.println("To quit the game: q");
                    break;

                case "quit", "q":
                    break gameLoop;

                default:
                    System.out.println("Invalid command");
            }
        }

        if (controller.getGameState() == GameState.WON) {
            System.out.println("You won!");
        } else {
            System.out.println("Game over!");
        }

    }
}