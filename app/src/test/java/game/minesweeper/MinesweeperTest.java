package game.minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinesweeperTest {
    @Test
    void appHasAGreeting() {
        Minesweeper classUnderTest = new Minesweeper();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}