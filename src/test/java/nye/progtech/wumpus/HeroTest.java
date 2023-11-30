package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    @Test
    void byDefaultShouldHaveZeroArrows(){
        Hero testHero = new Hero();
        assertEquals(0,testHero.getArrows());
    }
    @Test
    void byDefaultShouldNotHaveGold(){
        Hero testHero = new Hero();
        assertFalse(testHero.isHasGold());
    }

    @Test
    void byDefaultMapShouldBeNull(){
        Hero testHero = new Hero();
        assertNull(testHero.getCurrentRoom());
    }

    @Test
    void directionShouldBeEast(){
        Hero testHero = new Hero();
        testHero.setDirectionAsChar('E');
        assertEquals('E',testHero.getDirectionAsChar());
    }
    @Test
    void directionShouldBeWest(){
        Hero testHero = new Hero();
        testHero.setDirectionAsChar('W');
        assertEquals('W',testHero.getDirectionAsChar());
    }
    @Test
    void directionShouldBeSouth(){
        Hero testHero = new Hero();
        testHero.setDirectionAsChar('S');
        assertEquals('S',testHero.getDirectionAsChar());
    }
    @Test
    void directionShouldBeNorth(){
        Hero testHero = new Hero();
        testHero.setDirectionAsChar('N');
        assertEquals('N',testHero.getDirectionAsChar());
    }
    @Test
    void roomShouldBeEqual(){
        Random r = new Random();
        Room testRoom = new Room(r.nextInt(),r.nextInt());
        Hero testHero = new Hero(testRoom);
        assertEquals(testRoom,testHero.getCurrentRoom());
    }
    @Test
    void arrowsNumberShouldBeEqual(){
        Random r = new Random();
        Hero testHero = new Hero();
        int numberOfArrow = r.nextInt();
        testHero.setArrows(numberOfArrow);
        assertEquals(numberOfArrow, testHero.getArrows());
    }
    @Test
    void hasGoldShouldBeEqual(){
        Random r = new Random();
        Hero testHero = new Hero();
        boolean hasGold = r.nextBoolean();
        testHero.setHasGold(hasGold);
        assertEquals(hasGold, testHero.isHasGold());
    }
}