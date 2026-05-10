package game.minesweeper.gridTests;

import game.minesweeper.grid.Coordinate3D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Coordinate3DTest {
    @Test
    void newCoordinate3DHasCorrectXValue() {
        Coordinate3D Coordinate3D = new Coordinate3D(4,2,0);
        int rowValue = Coordinate3D.x();
        assertEquals(4, rowValue);
    }

    @Test
    void newCoordinate3DHasCorrectYValue() {
        Coordinate3D Coordinate3D = new Coordinate3D(4,2,0);
        int columnValue = Coordinate3D.y();
        assertEquals(2, columnValue);
    }

    @Test
    void newCoordinate3DHasCorrectZValue() {
        Coordinate3D Coordinate3D = new Coordinate3D(4,2,0);
        int columnValue = Coordinate3D.z();
        assertEquals(0, columnValue);
    }

    @Test
    void correctDisplayString() {
        Coordinate3D Coordinate3D = new Coordinate3D(4,2,0);
        String displayString = Coordinate3D.displayString();
        assertEquals("4,2,0", displayString);
    }

    @Test
    void Coordinate3DNotFeasible(){
        Coordinate3D Coordinate3D = new Coordinate3D(-4,2,0);
        assertFalse(Coordinate3D.isFeasible());
    }
}
