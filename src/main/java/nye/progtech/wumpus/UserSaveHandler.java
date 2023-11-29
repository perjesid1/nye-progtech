package nye.progtech.wumpus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserSaveHandler extends SaveHandler{
    private String fileName;
    private Users allUsers;
    private User currentUser;

    public UserSaveHandler(){
        super("users");
        this.allUsers=null;
        this.currentUser=null;
    }

    public UserSaveHandler(Users allUsers, User currentUser){
        super("users");
        this.allUsers = allUsers;
        this.currentUser = currentUser;
    }

    public UserSaveHandler(Users allUsers, User currentUser, String fileName){
        super(fileName);
        this.allUsers = allUsers;
        this.currentUser = currentUser;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }
    public User getUserByName(String userName){
        return this.allUsers.getUserByName(userName);
    }

    public void setCurrentUser(String userName){
        if(this.allUsers.userExists(userName)){
            this.currentUser = this.allUsers.getUserByName(userName);
        } else {
            this.currentUser = new User(userName);
            this.allUsers.add(this.currentUser);
        }
    }

    public void saveAllUsers() throws IOException {
        File usersFile = new File("users\\" + fileName + ".txt");
        if(usersFile.exists() && !usersFile.isDirectory()){
            usersFile.delete();
        }
        usersFile.createNewFile();
        FileWriter usersWriter = new FileWriter(usersFile);

        for (User user:this.allUsers) {
            usersWriter.write("[UserName]:" + user.getUserName() + ";[HighScore]:" + user.getHighScore());
        }
    }

    public void loadAllUsers() throws IOException{
        File usersFile = new File("maps\\" + fileName + ".txt");
        Scanner usersScanner = new Scanner(usersFile);
        if(usersFile.exists() && !usersFile.isDirectory()) {
            try {
                String loadedUser;
                while (usersScanner.hasNext()) {
                    loadedUser = usersScanner.nextLine();
                    this.allUsers.add(new User(loadedUser.substring(11, loadedUser.indexOf(";[HighScore]")), Integer.parseInt(loadedUser.substring(loadedUser.indexOf(";[HighScore]")))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException("Corrupted save file!");
            }
        }
    }

    public Users getAllUsers() {
        return allUsers;
    }
}
