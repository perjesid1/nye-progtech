package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WumpusTest {
    @Test
    void shouldReturnUAsPrintableChar(){
        Wumpus testWumpus = new Wumpus();
        assertEquals('U', testWumpus.print());
    }
    @Test
    void shouldBeAliveByDefault(){
        Wumpus testWumpus = new Wumpus();
        assertTrue(testWumpus.isAlive());
    }
    @Test
    void isAliveShouldBeEqual(){
        Random r = new Random();
        boolean isAlive = r.nextBoolean();
        Wumpus testWumpus = new Wumpus();
        testWumpus.setAlive(isAlive);
        assertEquals(isAlive, testWumpus.isAlive());
    }
}