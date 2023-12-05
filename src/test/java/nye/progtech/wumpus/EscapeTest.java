package nye.progtech.wumpus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EscapeTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void givenMapHasEscape_whenEscapeObjectIsCreated_thenCanEscapeShouldBeFalse() {
        Escape testEscape = new Escape();
        assertFalse(testEscape.isCanEscape());
    }
    @Test
    void givenMapHasEscape_whenCanEscapeIsSet_thenCanEscapeShouldBeTheValueSet() {
        Random r = new Random();
        boolean canEscape = r.nextBoolean();
        Escape testEscape = new Escape();
        testEscape.setCanEscape(canEscape);
        assertEquals(canEscape,testEscape.isCanEscape());
    }
    @Test
    void givenMapHasEscape_whenMapIsPrinted_thenRepresentationShouldBeCharE(){
        Escape testEscape = new Escape();
        assertEquals('E',testEscape.print());
    }
    @Test
    void givenMapHasEscape_whenCanEscapeIsTrueAndEscapeIsEncountered_thenEscapeShouldPrintAMessage(){
        Escape testEscape = new Escape();
        testEscape.setCanEscape(true);
        testEscape.encounter();
        assertEquals("Hooray! You made it in one piece!\nGood job!", outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasEscape_whenCanEscapeIsFalseAndEscapeIsEncountered_thenEscapeShouldNotPrintAMessage(){
        Escape testEscape = new Escape();
        testEscape.encounter();
        assertEquals("",outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasEscape_whenEscapeIsInAnAdjacentRoom_thenEscapeShouldNotPrintAMessage(){
        Escape testEscape = new Escape();
        testEscape.message();
        assertEquals("",outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}