package nye.progtech.wumpus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WumpusTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void givenMapHasWumpus_whenMapIsPrinted_thenRepresentationShouldBeCharU(){
        Wumpus testWumpus = new Wumpus();
        assertEquals('U', testWumpus.print());
    }
    @Test
    void givenMapHasWumpus_whenWumpusObjectIsCreated_thenAliveShouldBeTrue(){
        Wumpus testWumpus = new Wumpus();
        assertTrue(testWumpus.isAlive());
    }
    @Test
    void givenMapHasWumpus_whenAliveIsSet_thenAliveShouldBeEqualToSetValue(){
        Random r = new Random();
        boolean isAlive = r.nextBoolean();
        Wumpus testWumpus = new Wumpus();
        testWumpus.setAlive(isAlive);
        assertEquals(isAlive, testWumpus.isAlive());
    }
    @Test
    void givenMapHasWumpus_whenAliveIsTrueAndWumpusIsEncountered_thenWumpusShouldPrintAMessage(){
        Wumpus testWumpus = new Wumpus();
        testWumpus.encounter();
        assertEquals("AH! The Wumpus got you!\nGAME OVER", outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasEscape_whenCanEscapeIsFalseAndEscapeIsEncountered_thenEscapeShouldNotPrintAMessage(){
        Wumpus testWumpus = new Wumpus();
        testWumpus.setAlive(false);
        testWumpus.encounter();
        assertEquals("",outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasWumpus_whenAliveIsTrueAndWumpusIsInAdjacentRoom_thenWumpusShouldPrintAMessage(){
        Wumpus testWumpus = new Wumpus();
        testWumpus.message();
        assertEquals("You feel a foul stench...", outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasEscape_whenCanEscapeIsFalseAndEscapeIsInAdjacentRoom_thenEscapeShouldNotPrintAMessage(){
        Wumpus testWumpus = new Wumpus();
        testWumpus.setAlive(false);
        testWumpus.message();
        assertEquals("",outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}