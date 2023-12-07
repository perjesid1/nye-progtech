package nye.progtech.wumpus;
import java.util.ArrayList;
import java.util.List;
/**
 *
 */
public class Users {
    /**
     *
     */
    private List<User> usersList = new ArrayList<>();
    /**
     *
     * @param userName Identifier of the user.
     * @return Ture if an user exists with the given username, false otherwise.
     */
    public boolean userExists(final String userName) {
        int userIndex = this.usersList.indexOf(new User(userName));
        return userIndex != -1;
    }
    /**
     *
     * @param userName Identifier of the user.
     * @return User object.
     */
    public User getUserByName(final String userName) {
        User user = new User(userName);
        return this.usersList.get(this.usersList.indexOf(user));
    }

    /**
     *
     * @param user User to add.
     */
    public void add(final User user) {
        this.usersList.add(user);
    }
    /**
     *
     * @return List of Users.
     */
    public List<User> getAllUsers() {
        return this.usersList;
    }
}
