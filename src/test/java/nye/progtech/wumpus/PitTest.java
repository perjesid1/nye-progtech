package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PitTest {
    @Test
    void shouldReturnPAsPrintableChar(){
        Pit testPit = new Pit();
        assertEquals('P',testPit.print());
    }

}