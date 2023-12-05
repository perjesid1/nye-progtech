package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    @Test
    void givenMapHasRoom_whenARoomIsCreatedWithEmptyConstructor_thenAllTheValuesShouldBeNullOrZero(){
        Room testRoom = new Room();
        assertEquals(0,testRoom.getRow());
        assertEquals(0, testRoom.getColumn());
        assertNull(testRoom.getEvent());
        assertFalse(testRoom.isHasEvent());
    }
    @Test
    void givenMapHasRoom_whenARoomIsCreatedWithWall_thenEventShouldBeWall(){
        Random random = new Random();
        int row = random.nextInt();
        int col = random.nextInt();
        Room testRoom = new Room(row, col, new Wall());
        assertEquals(row, testRoom.getRow());
        assertEquals(col, testRoom.getColumn());
        assertEquals(Wall.class, testRoom.getEvent().getClass());
        assertTrue(testRoom.isHasEvent());
    }
    @Test
    void givenMapHasRoom_whenARoomIsCreatedWithPit_thenEventShouldBePit(){
        Random random = new Random();
        int row = random.nextInt();
        int col = random.nextInt();
        Room testRoom = new Room(row, col, new Pit());
        assertEquals(row, testRoom.getRow());
        assertEquals(col, testRoom.getColumn());
        assertEquals(Pit.class, testRoom.getEvent().getClass());
        assertTrue(testRoom.isHasEvent());
    }
    @Test
    void givenMapHasRoom_whenARoomIsCreatedWithGold_thenEventShouldBeGold(){
        Random random = new Random();
        int row = random.nextInt();
        int col = random.nextInt();
        Room testRoom = new Room(row, col, new Gold());
        assertEquals(row, testRoom.getRow());
        assertEquals(col, testRoom.getColumn());
        assertEquals(Gold.class, testRoom.getEvent().getClass());
        assertTrue(testRoom.isHasEvent());
    }
    @Test
    void givenMapHasRoom_whenARoomIsCreatedWithWumpus_thenEventShouldBeWumpus(){
        Random random = new Random();
        int row = random.nextInt();
        int col = random.nextInt();
        Room testRoom = new Room(row, col, new Wumpus());
        assertEquals(row, testRoom.getRow());
        assertEquals(col, testRoom.getColumn());
        assertEquals(Wumpus.class, testRoom.getEvent().getClass());
        assertTrue(testRoom.isHasEvent());
    }
    @Test
    void givenMapHasRoom_whenARoomIsCreatedWithEscape_thenEventShouldBeEscape(){
        Random random = new Random();
        int row = random.nextInt();
        int col = random.nextInt();
        Room testRoom = new Room(row, col, new Escape());
        assertEquals(row, testRoom.getRow());
        assertEquals(col, testRoom.getColumn());
        assertEquals(Escape.class, testRoom.getEvent().getClass());
        assertTrue(testRoom.isHasEvent());
    }
}