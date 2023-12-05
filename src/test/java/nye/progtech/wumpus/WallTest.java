package nye.progtech.wumpus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void givenMapHasWall_whenMapIsPrinted_thenRepresentationShouldBeCharW(){
        Wall testWall = new Wall();
        assertEquals('W', testWall.print());
    }
    @Test
    void givenMapHasWall_whenWallIsEncountered_thenWallShouldPrintAMessage(){
        Wall testWall = new Wall();
        testWall.encounter();
        assertEquals("Banging your head against the wall won't help...", outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasWall_whenWallIsInAnAdjacentRoom_thenWallShouldNotPrintAMessage(){
        Wall testWall = new Wall();
        testWall.message();
        assertEquals("",outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}