package game.minesweeper;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {
    @Test
    void newCoordinateHasCorrectRowValue() {
        Coordinate coordinate = new Coordinate(6, 7);
        int rowValue = coordinate.row();
        assertEquals(6, rowValue);
    }

    @Test
    void newCoordinateHasCorrectColumnValue() {
        Coordinate coordinate = new Coordinate(6, 7);
        int columnValue = coordinate.column();
        assertEquals(7, columnValue);
    }


    @Test
    void correctDisplayString() {
        Coordinate coordinate = new Coordinate(6, 7);
        String displayString = coordinate.displayString();
        assertEquals("6,7", displayString);
    }

    @Test
    void coordinateNotFeasible(){
        Coordinate coordinate = new Coordinate(-6,7);
        assertFalse(coordinate.isFeasible());
    }
}
