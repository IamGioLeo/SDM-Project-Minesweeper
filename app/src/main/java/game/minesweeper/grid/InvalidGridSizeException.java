package game.minesweeper.grid;

public class InvalidGridSizeException extends RuntimeException {

    public InvalidGridSizeException(int... gridSize) {
        super(buildMessage(gridSize));
    }

    private static String buildMessage(int[] gridSize) {
        StringBuilder message = new StringBuilder("Invalid grid size: ");

        for (int i : gridSize) {
            if (i > 0) {
                message.append(" * ");
            }
            message.append(i);
        }

        return message.toString();
    }
}