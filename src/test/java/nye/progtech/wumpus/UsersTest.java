package nye.progtech.wumpus;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
class UsersTest {
    @Test
    void givenUsersExist_whenSizeIsUsed_thenItShouldReturnTheCountOfUsers() {
        Random random = new Random();
        int count = random.nextInt(0, 10);
        Users testUsers = new Users();
        for (int i = 0; i < count; i++) {
            testUsers.add(new User("TestUser_" + i));
        }
        assertEquals(count, testUsers.getAllUsers().size());
    }

    @Test
    void givenUsersExist_whenGetUserByNameIsUsed_thenItShouldReturnTheProperInstance() {
        Random random = new Random();
        int count = random.nextInt(0, 10);
        User testUser = new User("TestUser_1");
        Users testUsers = new Users();
        testUsers.add(testUser);
        for (int i = 1; i < count; i++) {
            testUsers.add(new User("TestUser_" + i));
        }
        assertEquals(testUser, testUsers.getUserByName(testUser.getUserName()));
    }
    @Test
    void givenUsersExist_whenUserExistsIsUsedAndUserDoesExist_thenItShouldReturnTrue() {
        Random random = new Random();
        int count = random.nextInt(0, 10);
        User testUser = new User("TestUser_1");
        Users testUsers = new Users();
        testUsers.add(testUser);
        for (int i = 1; i < count; i++) {
            testUsers.add(new User("TestUser_" + i));
        }
        assertTrue(testUsers.userExists(testUser.getUserName()));
    }
    @Test
    void givenUsersExist_whenUserExistsIsUsedAndUserDoesNotExist_thenItShouldReturnFalse() {
        Random random = new Random();
        int count = random.nextInt(0, 10);
        Users testUsers = new Users();
        for (int i = 0; i < count; i++) {
            testUsers.add(new User("TestUser_" + i));
        }
        assertFalse(testUsers.userExists("DefinitelyNonexistentUser"));
    }
}