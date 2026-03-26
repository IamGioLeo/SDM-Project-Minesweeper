package game.minesweeper.CLI;

import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GameState;
import game.minesweeper.grid.GridOfSquares;

import java.util.Scanner;

public class ConsoleUI {

    public void start(GameController controller, GridOfSquares grid) {

        Scanner scanner = new Scanner(System.in);

        GridPrinter.print(grid);

        while (controller.getGameState() == GameState.RUNNING) {

            System.out.println("Enter command to open a cell or to place/remove a flag:");
            System.out.println("open(o) row col | flag(f) row col");

            String command = scanner.next().toLowerCase();

            int row;
            int col;

            switch (command) {
                case "open", "o":
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                    controller.open(row, col);
                    GridPrinter.print(grid);
                    break;

                case "flag", "f":
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                    controller.toggleFlag(row, col);
                    GridPrinter.print(grid);
                    break;

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