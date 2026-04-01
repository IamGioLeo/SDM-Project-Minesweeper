package game.minesweeper.engine;


import game.minesweeper.grid.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridInitializer<C extends CoordinateInterface, G extends AbstractGrid<C>> {

    private final G grid;
    private Set<C> minesCoordinate = new HashSet<>();

    public GridInitializer(G grid) {
        this.grid = grid;
    }

    public void initialize(int mineCount) {
        this.minesCoordinate = generateRandomSetOfCoordinates(mineCount);
        placeMines();
    }

    public void placeMine(C coordinate) {

        Cell cell = grid.getCell(coordinate);

        if (!cell.hasMine()) {

            cell.placeMine();
            minesCoordinate.add(coordinate);

            for (Cell neighborCell : grid.getCellNeighbors(coordinate)) {
                int neighborsMineCount = neighborCell.neighborsMineCount();
                neighborCell.setNeighborsMineCount(neighborsMineCount + 1);
            }
        }
    }

    public void placeMines() {
        for (C coordinate : minesCoordinate) {
            placeMine(coordinate);
        }
    }

    public G getGrid() {
        return grid;
    }

    public int getMineCount() {
        return minesCoordinate.size();
    }

    public Set<C> getMinesCoordinate() {
        return minesCoordinate;
    }

    public Set<C> generateRandomSetOfCoordinates(int mineCount) {

        Set<C> randomSetOfCoordinates = new HashSet<>();
        List<C> cellCoordinates = grid.getAllCoordinates();
        Collections.shuffle(cellCoordinates);

        for (int i = 0; i < mineCount; i++) {
            randomSetOfCoordinates.add(cellCoordinates.get(i));
        }

        return randomSetOfCoordinates;
    }

}
