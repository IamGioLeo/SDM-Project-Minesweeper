package game.minesweeper.CLI;

import game.minesweeper.GUI.StartingMenuGUI;
import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;

import java.util.Scanner;

public class StartingMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startMenu() {

        int columns = 9;
        int rows = 9;
        int mines = 10;

        System.out.println("Chose the defaults game difficulties: easy (e), medium (m), hard (h)");
        System.out.println("Or personalize your game: personalize (p)");
        System.out.println("To play on the Graphical User Interface: gui (g)");

        menuLoop:
        while (true) {

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
                    break;

                case "personalized", "p":
                    int[] configuration = personalizationLoop();
                    break;

                case "gui", "g":
                    new StartingMenuGUI().startMenu();
                    return;

                case "help":
                    System.out.println("Chose the defaults game difficulties: easy (e), medium (m), hard (h)");
                    System.out.println("Or access personalized setting: personalize (p)");
                    System.out.println("To play on the Graphical User Interface: gui (g)");
                    System.out.println("To exit: exit");
                    continue;

                case "exit":
                    break menuLoop;

                default:
                    System.out.println("Invalid option!");
                    continue;
            }

            while (true) {
                startGame(rows, columns, mines);

                System.out.println("Do you want to start a new game? (y/n)");
                String newGame = scanner.next().toLowerCase();

                switch (newGame) {
                    case "y", "yes":
                        continue;
                    case "menu":
                        System.out.println("Chose the defaults game difficulties: easy (e), medium (m), hard (h)");
                        System.out.println("Or personalize your game: personalize (p)");
                        System.out.println("To play on the Graphical User Interface: gui (g)");

                        continue menuLoop;
                    default:
                        return;
                }
            }
        }
    }

    public static int[] personalizationLoop() {

        int[] configuration = new int[3];

        while (true) {
            System.out.println("How many Columns?");
            configuration[0] = scanner.nextInt();
            if (configuration[0] <= 0) {
                System.out.println("Columns must be greater than 0!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("How many Rows?");
            configuration[1] = scanner.nextInt();
            if (configuration[1] <= 0) {
                System.out.println("Rows must be greater than 0!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("How many mines?");
            configuration[2] = scanner.nextInt();
            if (configuration[0] * configuration[1] < configuration[2]) {
                System.out.println("There are too many mines!\n");
                continue;
            }
            break;
        }

        return configuration;
    }

    public static void startGame(int rows, int columns, int mines) {

        GridOfSquares grid = new GridOfSquares(rows, columns);
        GridInitializer<Coordinate> gridInitializer = new GridInitializer<>(grid);
        gridInitializer.initialize(mines);

        Game<Coordinate> game = new Game<>(grid);
        GameController controller = new GameController(game);

        ConsoleUI ui = new ConsoleUI();
        ui.start(controller, grid);

    }

}
