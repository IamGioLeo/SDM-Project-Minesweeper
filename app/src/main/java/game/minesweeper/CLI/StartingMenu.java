package game.minesweeper.CLI;

import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;

import java.util.Scanner;

public class StartingMenu {

    public static GridOfSquares startMenu() {

        Scanner scanner = new Scanner(System.in);

        int columns = 9;
        int rows = 9;
        int mines = 10;

        while (true) {
            System.out.println("Chose the game difficulty: easy (e), medium (m), hard (h)");
            System.out.println("Or personalized setting: personalize (p)");

            String option = scanner.next().toLowerCase();

            switch (option) {

                case "easy", "e":
                    break;

                case "medium", "m":
                    rows = 16;
                    columns = 16;
                    mines = 40;
                    break;

                case "hard", "h":
                    rows = 30;
                    columns = 16;
                    mines = 99;

                case "personalized", "p":
                    System.out.println("How many Columns?");
                    columns = scanner.nextInt();
                    System.out.println("How many Rows?");
                    rows = scanner.nextInt();
                    System.out.println("How many mines?");
                    mines = scanner.nextInt();
                    if (columns*rows < mines) {
                        System.out.println("There are too many mines!");
                        continue;
                    }
                    break;

                default:
                    System.out.println("Invalid option!");
                    continue;
            }
            break;
        }

        GridOfSquares grid = new GridOfSquares(rows, columns);
        GridInitializer<Coordinate> gridInitializer = new GridInitializer<>(grid);
        gridInitializer.initialize(mines);

        return grid;

    }

}
