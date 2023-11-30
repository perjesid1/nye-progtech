package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EscapeTest {
    @Test
    void byDefaultIsCanEscapeShouldReturnFalse() {
        Escape testEscape = new Escape();
        assertFalse(testEscape.isCanEscape());
    }

    @Test
    void canEscapeShouldBeEqual() {
        Random r = new Random();
        boolean canEscape = r.nextBoolean();
        Escape testEscape = new Escape();
        testEscape.setCanEscape(canEscape);
        assertEquals(canEscape,testEscape.isCanEscape());
    }

    @Test
    void shouldReturnEAsPrintableChar(){
        Escape testEscape = new Escape();
        assertEquals('E',testEscape.print());
    }
}