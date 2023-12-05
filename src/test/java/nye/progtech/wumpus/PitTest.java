package nye.progtech.wumpus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PitTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void givenMapHasPit_whenMapIsPrinted_thenRepresentationShouldBeCharP(){
        Pit testPit = new Pit();
        assertEquals('P',testPit.print());
    }
    @Test
    void givenMapHasPit_whenPitIsInAnAdjacentRoom_thenPitShouldPrintAMessage(){
        Pit testPit = new Pit();
        testPit.message();
        assertEquals("You feel a slight breeze...", outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasPit_whenEncountered_thenPitShouldPrintAMessage(){
        Pit testPit = new Pit();
        testPit.encounter();
        assertEquals("You fall into an endless pit...\nGAME OVER", outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}