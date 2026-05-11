package game.minesweeper.engine;

public class InvalidNumberOfMinesException extends RuntimeException {

    public InvalidNumberOfMinesException(int numberOfMines) {
        super(buildMessage(numberOfMines));
    }

    private static String buildMessage(int numberOfMines) {
        if (numberOfMines < 0) {
            return "Invalid number of mines:  " + numberOfMines + " must be equal or grater than 0";
        } else {
            return "Invalid number of mines:  " + numberOfMines + " must be lower than the number of cells";
        }
    }
}
