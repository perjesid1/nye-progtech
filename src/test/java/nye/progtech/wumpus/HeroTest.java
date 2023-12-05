package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    @Test
    void givenMapHasHero_whenHeroObjectIsCreated_thenArrowsNumberShouldBeZero(){
        Hero testHero = new Hero();
        assertEquals(0,testHero.getArrows());
    }
    @Test
    void givenMapHasHero_whenHeroObjectIsCreated_thenHasGoldShouldBeFalse(){
        Hero testHero = new Hero();
        assertFalse(testHero.isHasGold());
    }

    @Test
    void givenMapHasHero_whenHeroObjectIsCreatedWithoutACurrentRoom_thenCurrentRoomShouldBeEqualToNull(){
        Hero testHero = new Hero();
        assertNull(testHero.getCurrentRoom());
    }

    @Test
    void givenMapHasHero_whenDirectionIsSetToEast_thenDirectionShouldBeEqualToTheSetValue(){
        Hero testHero = new Hero();
        testHero.setDirectionAsChar('E');
        assertEquals('E',testHero.getDirectionAsChar());
    }
    @Test
    void givenMapHasHero_whenDirectionIsSetToWest_thenDirectionShouldBeEqualToTheSetValue(){
        Hero testHero = new Hero();
        testHero.setDirectionAsChar('W');
        assertEquals('W',testHero.getDirectionAsChar());
    }
    @Test
    void givenMapHasHero_whenDirectionIsSetToSouth_thenDirectionShouldBeEqualToTheSetValue(){
        Hero testHero = new Hero();
        testHero.setDirectionAsChar('S');
        assertEquals('S',testHero.getDirectionAsChar());
    }
    @Test
    void givenMapHasHero_whenDirectionIsSetToNorth_thenDirectionShouldBeEqualToTheSetValue(){
        Hero testHero = new Hero();
        testHero.setDirectionAsChar('N');
        assertEquals('N',testHero.getDirectionAsChar());
    }
    @Test
    void givenMapHasHero_whenHeroObjectIsCreated_thenCurrentRoomShouldBeEqualToTheOnePassedInTheConstructor(){
        Random r = new Random();
        Room testRoom = new Room(r.nextInt(),r.nextInt());
        Hero testHero = new Hero(testRoom);
        assertEquals(testRoom,testHero.getCurrentRoom());
    }
    @Test
    void givenMapHasHero_whenArrowsNumberIsSet_thenArrowsNumberShouldBeEqualToTheSetValue(){
        Random r = new Random();
        Hero testHero = new Hero();
        int numberOfArrow = r.nextInt();
        testHero.setArrows(numberOfArrow);
        assertEquals(numberOfArrow, testHero.getArrows());
    }
    @Test
    void givenMapHasHero_whenHasGoldIsSet_thenHasGouldShouldBeEqualToTheSetValue(){
        Random r = new Random();
        Hero testHero = new Hero();
        boolean hasGold = r.nextBoolean();
        testHero.setHasGold(hasGold);
        assertEquals(hasGold, testHero.isHasGold());
    }
    @Test
    void givenMapHasHero_whenCurrentRoomIsSet_thenCurrentRoomShouldBeEqualToTheSetValue(){
        Random r = new Random();
        Room testRoom = new Room(r.nextInt(),r.nextInt());
        Hero testHero = new Hero();
        assertNull(testHero.getCurrentRoom());
        testHero.setCurrentRoom(testRoom);
        assertEquals(testRoom, testHero.getCurrentRoom());
    }
}