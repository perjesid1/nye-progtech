package nye.progtech.wumpus;

import java.util.Objects;

public class User {
    private final String userName;
    private int highScore;

    public User(String userName, int highScore) {
        this.userName = userName;
        this.highScore = highScore;
    }

    public User(String userName) {
        this.userName = userName;
        this.highScore = 0;
    }

    public String getUserName() {
        return userName;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getUserName(), user.getUserName());
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", highScore=" + highScore +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName());
    }
}