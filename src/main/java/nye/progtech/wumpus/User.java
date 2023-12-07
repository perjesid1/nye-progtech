package nye.progtech.wumpus;
import java.util.Objects;
/**
 *
 */
public class User {
    /**
     *
     */
    private final String userName;
    /**
     *
     */
    private int highScore;
    /**
     *
     * @param newUserName Identifier of the User.
     * @param newHighScore High Score of the User.
     */
    public User(final String newUserName, final int newHighScore) {
        this.userName = newUserName;
        this.highScore = newHighScore;
    }
    /**
     *
     * @param newUserName Identifier of the User.
     */
    public User(final String newUserName) {
        this.userName = newUserName;
        this.highScore = 0;
    }
    /**
     *
     * @return Identifier of the User.
     */
    public String getUserName() {
        return userName;
    }
    /**
     *
     * @return High Score of the user.
     */
    public int getHighScore() {
        return highScore;
    }
    /**
     *
     * @param newHighScore High Score to set for the user.
     */
    public void setHighScore(final int newHighScore) {
        this.highScore = newHighScore;
    }
    /**
     *
     * @param o Object to compare to.
     * @return True if the two users are the same, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return Objects.equals(getUserName(), user.getUserName());
    }
    /**
     *
     * @return Hash of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getHighScore());
    }
}
