package game.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    void newCellIsNotRevealed() {
        Cell cell = new Cell();
        assertFalse(cell.isRevealed());
    }

    @Test
    void revealMakesCellRevealed() {
        Cell cell = new Cell();
        cell.reveal();
        assertTrue(cell.isRevealed());
    }

    @Test
    void newCellHasNoMine() {
        Cell cell = new Cell();
        assertFalse(cell.hasMine());
    }

    @Test
    void cellCanHaveMine() {
        Cell cell = new Cell();
        cell.placeMine();
        assertTrue(cell.hasMine());
    }

    @Test
    void newCellIsNotFlagged() {
        Cell cell = new Cell();
        assertFalse(cell.isFlagged());
    }

    @Test
    void flagCanBeToggled() {
        Cell cell = new Cell();
        cell.toggleFlag();
        assertTrue(cell.isFlagged());
        cell.toggleFlag();
        assertFalse(cell.isFlagged());
    }

    @Test
    void newCellNeighborsMineCountIsZero(){
        Cell cell = new Cell();

        assertEquals(0, cell.neighborsMineCount());
    }

    @Test
    void cellNeighborsMineCountPlaceCorrectly(){
        Cell cell = new Cell();
        cell.setNeighborsMineCount(6);

        assertEquals(6, cell.neighborsMineCount());
    }
}
