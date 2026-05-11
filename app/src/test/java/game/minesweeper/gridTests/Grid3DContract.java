package game.minesweeper.gridTests;

import game.minesweeper.grid.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public interface Grid3DContract extends GridContractTest<Coordinate3D, AbstractGrid3D> {

    Coordinate3D vertexCoordinate();

    Coordinate3D edgeCoordinate();

    Coordinate3D faceCoordinate();

    int expectedNumberOfVertexNeighbors();

    int expectedNumberOfEdgeNeighbors();

    int expectedNumberOfFaceNeighbors();

    int expectedHeight();

    int expectedWidth();

    int expectedDepth();

    default int internalX() {
        return 1;
    }

    default int internalY() {
        return 1;
    }

    default int internalZ() {
        return 1;
    }

    @Override
    default int expectedTotalCells(){
        return expectedHeight() * expectedWidth() * expectedDepth();
    }

    AbstractGrid3D createGridWithSizes(int width, int height, int depth);

    @Test
    default void constructorThrowsExceptionForInvalidWidth(){
        assertThrows(InvalidGridSizeException.class, () -> createGridWithSizes(-1, 9, 9));
    }

    @Test
    default void constructorThrowsExceptionForInvalidHeight(){
        assertThrows(InvalidGridSizeException.class, () -> createGridWithSizes(9, -1, 9));
    }

    @Test
    default void constructorThrowsExceptionForInvalidDepth(){
        assertThrows(InvalidGridSizeException.class, () -> createGridWithSizes(9, 9, -1));
    }

    @Test
    default void gridHasExpectedHeight() {
        AbstractGrid3D grid = createGrid();

        assertEquals(grid.getHeight(), expectedHeight());
    }

    @Test
    default void gridHasExpectedWidth() {
        AbstractGrid3D grid = createGrid();

        assertEquals(grid.getWidth(), expectedWidth());
    }

    @Test
    default void gridHasExpectedDepth() {
        AbstractGrid3D grid = createGrid();

        assertEquals(grid.getDepth(), expectedDepth());
    }

    @Test
    default void gridHasExpectedNumberOfVertexNeighbors() {
        AbstractGrid3D grid = createGrid();

        int numberOfVertexNeighbors = grid.getCellNeighbors(vertexCoordinate()).size();

        assertEquals(expectedNumberOfVertexNeighbors(),  numberOfVertexNeighbors);
    }

    @Test
    default void gridHasExpectedNumberOfEdgeNeighbors() {
        AbstractGrid3D grid = createGrid();

        int numberOfEdgeNeighbors = grid.getCellNeighbors(edgeCoordinate()).size();

        assertEquals(expectedNumberOfEdgeNeighbors(),  numberOfEdgeNeighbors);
    }

    @Test
    default void gridHasExpectedNumberOfFaceNeighbors() {
        AbstractGrid3D grid = createGrid();

        int numberOfFaceNeighbors = grid.getCellNeighbors(faceCoordinate()).size();

        assertEquals(expectedNumberOfFaceNeighbors(),  numberOfFaceNeighbors);
    }

    @Test
    default void gridCanGetCellWithInts() {
        AbstractGrid3D grid = createGrid();

        Cell cell = grid.getCell(internalX(), internalY(), internalZ());

        assertNotNull(cell);
    }
}
