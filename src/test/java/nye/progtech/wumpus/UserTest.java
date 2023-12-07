package nye.progtech.wumpus;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void givenThereIsAnUserObject_whenTheObjectIsCreated_thenTheHighScoreShouldBeZeroWhenItIsNotGivenInTheConstructor(){
        User testUser = new User("Test");
        assertEquals(0,testUser.getHighScore());
    }
    @Test
    void givenThereIsAnUserObject_whenTheObjectIsCreated_thenTheHighScoreShouldBeTheValueSetByTheConstructor(){
        Random r = new Random();
        int randomHighScore = r.nextInt();
        User testUser = new User("test", randomHighScore);
        assertEquals(randomHighScore, testUser.getHighScore());
    }

    @Test
    void givenThereIsAnUserObject_whenTheObjectIsCreated_thenTheNameShouldBeTheValueSetByTheConstructor(){
        Random r = new Random();
        int randomInt1 = r.nextInt();
        int randomInt2 = r.nextInt();
        User testUser = new User(randomInt2 + "-" + randomInt1);
        assertEquals(randomInt2 + "-" + randomInt1, testUser.getUserName());
    }

    @Test
    void givenThereIsAnUserObject_whenTheHighScoreIsSet_thenTheHighScoreShouldBeTheSetValue(){
        Random r = new Random();
        int randomHighScore = r.nextInt();
        User testUser = new User("test");
        assertEquals(0, testUser.getHighScore());
        testUser.setHighScore(randomHighScore);
        assertEquals(randomHighScore, testUser.getHighScore());
    }

    @Test
    void givenUsersAreCompared_whenUsernameIsTheSame_thenTheyShouldBeEqual(){
        Random r = new Random();
        int randomInt1 = r.nextInt();
        int randomInt2 = r.nextInt();
        User testUser1 = new User(randomInt2 + "-" + randomInt1);
        User testUser2 = new User(randomInt2 + "-" + randomInt1);
        assertEquals(testUser2, testUser1);
        assertEquals(testUser2.hashCode(), testUser1.hashCode());
    }
    @Test
    void givenUsersAreCompared_whenUsernameIsDifferent_thenTheyShouldNotBeEqual(){
        Random r = new Random();
        int randomInt1 = r.nextInt();
        int randomInt2 = r.nextInt();
        User testUser1 = new User(randomInt2 + "-" + randomInt1);
        User testUser2 = new User(randomInt1 + "-" + randomInt2);
        assertNotEquals(testUser2, testUser1);
        assertNotEquals(testUser2.hashCode(), testUser1.hashCode());
    }
    @Test
    void givenUsersAreCompared_whenTheTwoObjectsAreTheSame_thenTheyShouldBeEqual(){
        User testUser = new User("testUser");
        assertTrue(testUser.equals(testUser));
    }
    @Test
    void givenUsersAreCompared_whenTheSecondObjectIsNotAnUser_thenTheyShouldNotBeEqual(){
        User testUser = new User("testUser");
        assertNotEquals(testUser, new Pit());
    }
}