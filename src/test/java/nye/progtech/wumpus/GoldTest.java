package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GoldTest {
    @Test
    void byDefaultIsFoundShouldReturnFalse() {
        Gold testGold = new Gold();
        assertFalse(testGold.isFound());
    }

    @Test
    void isFoundSetShouldBeEqual() {
        Random r = new Random();
        boolean found = r.nextBoolean();
        Gold testGold = new Gold();
        testGold.setFound(found);
        assertEquals(found, testGold.isFound());
    }

    @Test
    void shouldReturnGAsPrintableChar(){
        Gold testGold = new Gold();
        assertEquals('G',testGold.print());
    }
}