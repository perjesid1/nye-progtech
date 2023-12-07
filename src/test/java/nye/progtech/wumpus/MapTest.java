package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    void givenAMapIsCreated_whenMapSizeIsTooBig_thenItShouldThrowAnException() {
        InvalidMapSize thrown = assertThrows(
                InvalidMapSize.class,
                () -> new Map(21, new Hero(new Room(0,0))),
                "Expected Map() to throw InvalidMapSize, but it didn't"
        );
        assertEquals("Map too big.", thrown.getMessage());
    }
    @Test
    void givenAMapIsCreated_whenMapSizeIsTooSmall_thenItShouldThrowAnException() {
        InvalidMapSize thrown = assertThrows(
                InvalidMapSize.class,
                () -> new Map(5, new Hero(new Room(0,0))),
                "Expected Map() to throw InvalidMapSize, but it didn't"
        );
        assertEquals("Map too small.", thrown.getMessage());
    }
    @Test
    void givenAMapIsCreated_whenHeroRowIsNegative_thenItShouldThrowAnException() {
        InvalidCoordinates thrown = assertThrows(
                InvalidCoordinates.class,
                () -> new Map(6, new Hero(new Room(-1,0))),
                "Expected Map() to throw InvalidMapSize, but it didn't"
        );
        assertEquals("Hero outside of the map.", thrown.getMessage());
    }
    @Test
    void givenAMapIsCreated_whenHeroColumnIsNegative_thenItShouldThrowAnException() {
        InvalidCoordinates thrown = assertThrows(
                InvalidCoordinates.class,
                () -> new Map(6, new Hero(new Room(0,-1))),
                "Expected Map() to throw InvalidMapSize, but it didn't"
        );
        assertEquals("Hero outside of the map.", thrown.getMessage());
    }
    @Test
    void givenAMapIsCreated_whenHeroRowIsGreaterThanMapSizeMinusOne_thenItShouldThrowAnException(){
        InvalidCoordinates thrown = assertThrows(
                InvalidCoordinates.class,
                () -> new Map(6, new Hero(new Room(6,0))),
                "Expected Map() to throw InvalidMapSize, but it didn't"
        );
        assertEquals("Hero outside of the map.", thrown.getMessage());
    }
    @Test
    void givenAMapIsCreated_whenHeroColumnIsGreaterThanMapSizeMinusOne_thenItShouldThrowAnException(){
        InvalidCoordinates thrown = assertThrows(
                InvalidCoordinates.class,
                () -> new Map(6, new Hero(new Room(0,6))),
                "Expected Map() to throw InvalidMapSize, but it didn't"
        );
        assertEquals("Hero outside of the map.", thrown.getMessage());
    }
    @Test
    void givenAMapIsCreated_whenMapSizeIsSmall_thenWumpusCountShouldBeSetToOne() throws Exception {
        Map testMap = new Map(6, new Hero(new Room(0, 0)));
        assertEquals(1, testMap.getWumpusCount());
    }
    @Test
    void givenAMapIsCreated_whenMapSizeIsMedium_thenWumpusCountShouldBeSetToTwo() throws Exception {
        Map testMap = new Map(10, new Hero(new Room(0, 0)));
        assertEquals(2, testMap.getWumpusCount());
    }
    @Test
    void givenAMapIsCreated_whenMapSizeIsBig_thenWumpusCountShouldBeSetToThree() throws Exception {
        Map testMap = new Map(20, new Hero(new Room(0, 0)));
        assertEquals(3, testMap.getWumpusCount());
    }
    @Test
    void givenAMapExists_whenGetEscapeRoomIsUsed_thenItShouldReturnTheHerosInitialPosition() throws Exception {
        Random random = new Random();
        int row = random.nextInt(0, 19);
        int col = random.nextInt(0, 19);
        Map testMap = new Map(20, new Hero(new Room(row, col)));
        assertEquals(row, testMap.getEscapeRoom().getRow());
        assertEquals(col, testMap.getEscapeRoom().getColumn());
    }

    @Test
    void givenAMapExists_whenGetHeroIsUsed_thenItShouldReturnTheHero() throws Exception {
        Random random = new Random();
        int row = random.nextInt(0, 19);
        int col = random.nextInt(0, 19);
        Hero testHero = new Hero(new Room(row, col));
        Map testMap = new Map(20, testHero);
        assertEquals(testHero, testMap.getHero());
    }

    @Test
    void givenAMapExists_whenGetSizeIsUsed_thenItShouldReturnTheMapsSize() throws Exception {
        Random random = new Random();
        int size = random.nextInt(6, 20);
        int row = random.nextInt(0, size);
        int col = random.nextInt(0, size);
        Map testMap = new Map(size, new Hero(new Room(row, col)));
        assertEquals(size, testMap.getSize());
    }
    @Test
    void givenAMapExists_whenSetWumpusCountIsUsed_thenWumpusCountShouldReturnTheSetAmount() throws Exception {
        Random random = new Random();
        int wumpusCount = random.nextInt(0, 3);
        Map testMap = new Map(20, new Hero(new Room(0, 0)));
        testMap.setWumpusCount(wumpusCount);
        assertEquals(wumpusCount, testMap.getWumpusCount());
    }

    @Test
    void givenAMapExists_whenARoomIsSet_thenItShouldBeSetToTheExactObject() throws Exception {
        Random random = new Random();
        int size = random.nextInt(6, 20);
        int row = random.nextInt(0, size);
        int col = random.nextInt(0, size);
        Map testMap = new Map(size, new Hero(new Room(row, col)));
        row = random.nextInt(0, size);
        col = random.nextInt(0, size);
        Room testRoom;
        int eventType = random.nextInt(0,  5);
        switch (eventType) {
            case 1 -> {
                testRoom = new Room(row, col, new Escape());
            }
            case 2 -> {
                testRoom = new Room(row, col, new Gold());
            }
            case 3 -> {
                testRoom = new Room(row, col, new Pit());
            }
            case 4 -> {
                testRoom = new Room(row, col, new Wall());
            }
            case 5 -> {
                testRoom = new Room(row, col, new Wumpus());
            }
            default -> {
                testRoom = new Room(row, col);
            }
        }
        testMap.setRoom(row, col, testRoom);
        assertEquals(testRoom, testMap.getRoom(row, col));
    }
}