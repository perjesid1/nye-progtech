package nye.progtech.wumpus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GoldTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void givenMapHasGold_whenGoldObjectIsCreated_thenFoundShouldBeFalse() {
        Gold testGold = new Gold();
        assertFalse(testGold.isFound());
    }

    @Test
    void givenMapHasGold_whenFoundIsSet_thenFoundShouldReturnSetValue() {
        Random r = new Random();
        boolean found = r.nextBoolean();
        Gold testGold = new Gold();
        testGold.setFound(found);
        assertEquals(found, testGold.isFound());
    }

    @Test
    void givenMapHasGold_whenMapIsPrinted_thenRepresentationShouldBeCharG(){
        Gold testGold = new Gold();
        assertEquals('G',testGold.print());
    }
    @Test
    void givenMapHasGold_whenFoundIsFalseAndGoldIsEncountered_thenGoldShouldPrintAMessage(){
        Gold testGold = new Gold();
        testGold.encounter();
        assertEquals("You found what you came here for... Now get out!", outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasGold_whenFoundIsTrueAndGoldIsEncountered_thenGoldShouldNotPrintAMessage(){
        Gold testGold = new Gold();
        testGold.setFound(true);
        testGold.encounter();
        assertEquals("",outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasGold_whenFoundIsFalseAndGoldIsInAdjacentRoom_thenGoldShouldPrintAMessage(){
        Gold testGold = new Gold();
        testGold.message();
        assertEquals("You saw something glint in the corner of your eye...", outputStreamCaptor.toString().trim());
    }
    @Test
    void givenMapHasGold_whenFoundIsTrueAndGoldIsInAdjacentRoom_thenGoldShouldNotPrintAMessage(){
        Gold testGold = new Gold();
        testGold.setFound(true);
        testGold.message();
        assertEquals("",outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}