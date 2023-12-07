package nye.progtech.wumpus;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
/**
 *
 */
public class UserSaveHandler extends SaveHandler {
    /**
     *
     */
    private static final int LENGTH_OF_USERNAME_BLOCK = 11;
    /**
     *
     */
    private static final int LENGTH_OF_HIGH_SCORE_BLOCK = 13;
    /**
     *
     */
    private Users allUsers;
    /**
     *
     */
    private User currentUser;
    /**
     *
     */
    public UserSaveHandler() {
        super("users");
        this.allUsers = new Users();
        this.currentUser = null;
        File directory = new File("users");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    /**
     *
     * @return Currently handled user object.
     */
    public User getCurrentUser() {
        return this.currentUser;
    }
    /**
     *
     * @param userName Identifier of the user we want to handle.
     */
    public void setCurrentUser(final String userName) {
        if (this.allUsers.userExists(userName)) {
            this.currentUser = this.allUsers.getUserByName(userName);
        } else {
            this.currentUser = new User(userName);
            this.allUsers.add(this.currentUser);
        }
    }
    /**
     *
     * @throws IOException Exception of the file handler.
     */
    public void saveAllUsers() throws IOException {
        File usersFile = new File("users\\" + super.getFileName() + ".txt");
        if (usersFile.exists() && !usersFile.isDirectory()) {
            usersFile.delete();
        }
        usersFile.createNewFile();
        FileWriter usersWriter = new FileWriter(usersFile);
        List<User> tempList = this.allUsers.getAllUsers();
        for (User user : tempList) {
            usersWriter.write("[UserName]:" + user.getUserName()
                    + ";[HighScore]:" + user.getHighScore());
        }
        usersWriter.close();
    }
    /**
     *
     * @throws IOException Exception of the file handler.
     */
    public void loadAllUsers() throws IOException {
        File usersFile = new File("users\\" + super.getFileName() + ".txt");
        Scanner usersScanner = new Scanner(usersFile);
        if (usersFile.exists() && !usersFile.isDirectory()) {
            try {
                String loadedUser;
                while (usersScanner.hasNext()) {
                    loadedUser = usersScanner.nextLine();
                    this.allUsers.add(new User(loadedUser
                            .substring(LENGTH_OF_USERNAME_BLOCK, loadedUser
                                    .indexOf(";[HighScore]:")),
                            Integer.parseInt(loadedUser
                                    .substring(loadedUser
                                            .indexOf(";[HighScore]:")
                                            + LENGTH_OF_HIGH_SCORE_BLOCK))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException("Corrupted save file!");
            }
        }
        usersScanner.close();
    }
    /**
     *
     * @return Users object (List<User>)
     */
    public Users getAllUsers() {
        return allUsers;
    }
}
