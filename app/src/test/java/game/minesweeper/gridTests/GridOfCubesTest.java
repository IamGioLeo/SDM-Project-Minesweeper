package game.minesweeper.gridTests;

import game.minesweeper.grid.AbstractGrid3D;
import game.minesweeper.grid.Coordinate3D;
import game.minesweeper.grid.GridOfCubes;

public class GridOfCubesTest implements Grid3DContract {

    @Override
    public Coordinate3D vertexCoordinate() {
        return new Coordinate3D(1, 1, 1);
    }

    @Override
    public Coordinate3D edgeCoordinate() {
        return new Coordinate3D(1, 1, 5);
    }

    @Override
    public Coordinate3D faceCoordinate() {
        return new Coordinate3D(1, 5, 5);
    }

    @Override
    public int expectedNumberOfVertexNeighbors() {
        return 7;
    }

    @Override
    public int expectedNumberOfEdgeNeighbors() {
        return 11;
    }

    @Override
    public int expectedNumberOfFaceNeighbors() {
        return 17;
    }

    @Override
    public int expectedHeight() {
        return 9;
    }

    @Override
    public int expectedWidth() {
        return 9;
    }

    @Override
    public int expectedDepth() {
        return 9;
    }

    @Override
    public AbstractGrid3D createGridWithSizes(int width, int height, int depth) {
        return new GridOfCubes(width, height, depth);
    }

    @Override
    public AbstractGrid3D createGrid() {
        return new GridOfCubes(expectedWidth(), expectedHeight(), expectedDepth());
    }

    @Override
    public Coordinate3D validCoordinate() {
        return new Coordinate3D(6, 6, 6);
    }

    @Override
    public Coordinate3D invalidCoordinate() {
        return new Coordinate3D(22, 11, 99);
    }

    @Override
    public Coordinate3D coordinateWithMaximumNumberOfNeighbors() {
        return new Coordinate3D(7, 5, 3);
    }

    @Override
    public int expectedMaximumNumberOfNeighbors() {
        return 26;
    }
}
