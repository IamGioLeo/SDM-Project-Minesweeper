package game.minesweeper.engine;

public record CommandResult(
        boolean boardChanged,
        GameState state
) {}