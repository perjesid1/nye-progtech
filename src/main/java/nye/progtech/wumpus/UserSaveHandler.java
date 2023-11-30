package nye.progtech.wumpus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserSaveHandler extends SaveHandler{
    private Users allUsers;
    private User currentUser;

    public UserSaveHandler(){
        super("users");
        this.allUsers = new Users();
        this.currentUser=null;
        File directory = new File("users");
        if (! directory.exists())
            directory.mkdir();
    }

    public User getCurrentUser(){
        return this.currentUser;
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
        File usersFile = new File("users\\" + super.getFileName() + ".txt");
        if(usersFile.exists() && !usersFile.isDirectory()){
            usersFile.delete();
        }
        usersFile.createNewFile();
        FileWriter usersWriter = new FileWriter(usersFile);

        for (User user:this.allUsers) {
            usersWriter.write("[UserName]:" + user.getUserName() + ";[HighScore]:" + user.getHighScore());
        }
        usersWriter.close();
    }

    public void loadAllUsers() throws IOException{
        File usersFile = new File("users\\" + super.getFileName() + ".txt");
        Scanner usersScanner = new Scanner(usersFile);
        if(usersFile.exists() && !usersFile.isDirectory()) {
            try {
                String loadedUser;
                while (usersScanner.hasNext()) {
                    loadedUser = usersScanner.nextLine();
                    this.allUsers.add(new User(loadedUser.substring(11, loadedUser.indexOf(";[HighScore]:")), Integer.parseInt(loadedUser.substring(loadedUser.indexOf(";[HighScore]:")+13))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException("Corrupted save file!");
            }
        }
        usersScanner.close();
    }

    public Users getAllUsers() {
        return allUsers;
    }
}
