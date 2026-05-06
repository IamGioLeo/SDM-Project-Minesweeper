package game.minesweeper.engine;

import game.minesweeper.grid.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Game<C extends CoordinateInterface> {

    private final AbstractGrid<C> grid;
    private GameState gameState = GameState.RUNNING;

    public Game(AbstractGrid<C> grid) {
        this.grid = grid;
    }

    public void openCell(C coordinate) {

        if (gameState != GameState.RUNNING) return;

        Cell cell = grid.getCell(coordinate);

        if (cell == null || cell.isRevealed() || cell.isFlagged()) return;

        if (cell.hasMine()) {
            gameState = GameState.LOST;
            cell.reveal();
            return;
        }

        revealCellsInSequence(coordinate);

        checkWinCondition();

    }

    private void revealCellsInSequence(C coordinate) {

        Set<C> visited = new HashSet<>();
        Queue<C> queue = new LinkedList<>();
        visited.add(coordinate);
        queue.add(coordinate);

        while (!queue.isEmpty()) {

            C currentCoordinate = queue.poll();
            Cell cell = grid.getCell(currentCoordinate);

            if (cell == null) continue;

            if (cell.neighborsMineCount() == 0) {
                for (C neighborCoordinate : grid.getNeighborCoordinates(currentCoordinate)) {
                    if (!visited.contains(neighborCoordinate)) {
                        visited.add(neighborCoordinate);
                        queue.add(neighborCoordinate);
                    }
                }
            }

            if (cell.isRevealed() || cell.isFlagged()) {
                continue;
            }

            cell.reveal();
        }
    }

    public void toggleFlag(C coordinate) {

        if (gameState != GameState.RUNNING) return;

        Cell cell = grid.getCell(coordinate);

        if (cell == null || cell.isRevealed()) return;

        cell.toggleFlag();
    }


    private void checkWinCondition() {
        for (Cell cell : grid.getAllCells()) {
            if (!cell.hasMine() && !cell.isRevealed()) {
                return;
            }
        }
        gameState = GameState.WON;
    }

    public GameState getState() {
        return gameState;
    }

    public AbstractGrid<C> getGrid() {
        return grid;
    }
}
