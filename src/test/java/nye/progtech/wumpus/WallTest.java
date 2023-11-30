package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    @Test
    void shouldReturnWAsPrintableChar(){
        Wall testWall = new Wall();
        assertEquals('W', testWall.print());
    }

}