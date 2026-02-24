package game.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


}
