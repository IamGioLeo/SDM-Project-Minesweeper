package game.minesweeper.engine;

public record CellView(
        boolean revealed,
        boolean flagged,
        boolean mine,
        int neighborMineCount
) {
}