package nye.progtech.wumpus;

import java.io.FileNotFoundException;
import java.lang.Thread;
import java.util.*;

public class WumpusApp{
    UserSaveHandler users = new UserSaveHandler();
    MapSaveHandler map = new MapSaveHandler();

    public final void run(){
        clearConsole();
        greetThePlayer();
        sleepFor(3);
        clearConsole();
        gameIntro();
        clearConsole();
        try {
            users.loadAllUsers();
        }
        catch(Exception e) {
            System.out.println("Unable to load users - corrupted save file.");
        }

        askForUser();
        try {
            users.saveAllUsers();
        }
        catch(Exception e) {
            System.out.println("Unable to save users - corrupted save file.");
        }
        Throwable throwable = null;
        try{
            map.setFileName("current_map_" + users.getCurrentUser().getUserName());
            map.loadMap();
        }catch (FileNotFoundException e){
            throwable = e;
            System.out.println("Seems like you don't have any pending games saved.");
        }catch (Exception e){
            throwable = e;
            System.out.println("Your pending game is not able to load - corrupted save file.");
        }
        if(throwable == null)
            System.out.println("Your pending game has been loaded.");

        clearConsole();
        do {
            sleepFor(1);
            clearConsole();
            displayMainMenu();
        }
        while(true);
    }

    private static void greetThePlayer(){
        System.out.println("\n");
        System.out.println("░█░█░█░█░█▀█░▀█▀░░░▀█▀░█░█░█▀▀░░░█░█░█░█░█▄█░█▀█░█░█░█▀▀");
        System.out.println("░█▀█░█░█░█░█░░█░░░░░█░░█▀█░█▀▀░░░█▄█░█░█░█░█░█▀▀░█░█░▀▀█");
        System.out.println("░▀░▀░▀▀▀░▀░▀░░▀░░░░░▀░░▀░▀░▀▀▀░░░▀░▀░▀▀▀░▀░▀░▀░░░▀▀▀░▀▀▀");
        System.out.println("\n");
    }
    private static void gameIntro(){
        System.out.println("Welcome to the \"Hunt the Wupus\" game.");
        System.out.println("The game is made by Dániel \"perjesid1\" Perjési, as an assignment for his Computer Science studies.");
        System.out.println("The original game was created by Gregory Yob in 1973.");
        System.out.println("The assignment is a modified version of the original game.");
        sleepFor(5);
    }

    public static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
            else
            {
                System.out.print("\033[2J\033[1;1H");
                System.out.flush();
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void sleepFor(int seconds){
        try {
            Thread.sleep(seconds* 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void askForUser() {
        Scanner scn = new Scanner(System.in);
        String userName;
        String answer;
        do{
            System.out.println("Please type in your user name.");
            try {
                userName = scn.nextLine();
            }
            catch(Exception e)
            {
                userName = "unknownUser";
            }
            System.out.println("Your user name will be \"" + userName + "\".\nContinue? (Y/N)");
            try {
                //System.in.read();
                answer = scn.nextLine();
            }
            catch(Exception e)
            {
                answer = "N";
            }
        }
        while (!answer.equalsIgnoreCase("Y"));
        users.setCurrentUser(userName);
    }

    private void displayMainMenu(){
        System.out.println("Map Editor [M]");
        System.out.println("Load Map From File [L]");
        System.out.println("Save Database [S]");
        System.out.println("Play Current Map [P]");
        System.out.println("Score Board [B]");
        System.out.println("Exit [X]");
        Scanner scn = new Scanner(System.in);
        switch (scn.nextLine().toUpperCase()) {
            case "X" -> System.exit(0);
            case "S" -> {
                Throwable throwable = null;
                try {
                    users.saveAllUsers();
                }
                catch (Exception e){
                    System.out.println("Unable to save users - corrupted save file.");
                    throwable = e;
                }
                if(throwable == null)
                    System.out.println("Data saved successfully!");
            }
            case "M" -> System.out.println("Not implemented in current version!");
            case "P" -> {
                clearConsole();
                System.out.println("You have found the labyrinth where the terrifying monster lives...");
                System.out.println("You have only one task... And only one chance...");
                sleepFor(4);
                clearConsole();
                greetThePlayer();
                sleepFor(2);
                Hunt newGame = new Hunt(users.getCurrentUser().getUserName());
                if (newGame.run(map)) {
                    users.getCurrentUser().setHighScore(users.getCurrentUser().getHighScore() + 1);
                }
            }
            case "L" -> {
                clearConsole();
                System.out.println("Please input the file's name without the file extension which you want to load.");
                System.out.println("The file extension must be .txt!");
                Throwable throwable = null;
                try {
                    map.setFileName(scn.nextLine());
                    map.loadNewMap();
                }catch (FileNotFoundException e){
                    throwable = e;
                    System.out.println("There's no file with such name in the import folder!");
                }catch (Exception e){
                    throwable = e;
                    System.out.println("Invalid import file!");
                }
                if(throwable == null)
                    System.out.println("Map successfully loaded!");

            }
            case "B" -> {
                users.getAllUsers().sort((a, b) -> {
                    if (a.getHighScore() == b.getHighScore()) {
                        return a.getUserName().compareToIgnoreCase(b.getUserName());
                    } else {
                        return Integer.compare(b.getHighScore(), a.getHighScore());
                    }
                });
                if(users.getAllUsers().size() > 10){
                    for(int i = 0; i < 10; i++) {
                        System.out.println(i + 1 + ". " + users.getAllUsers().get(i).getUserName() + " [" + users.getAllUsers().get(i).getHighScore() + " win(s)]");
                        sleepFor(1);
                    }
                }
                else {
                    for(int i = 0; i < users.getAllUsers().size(); i++) {
                        System.out.println(i + 1 + ". " + users.getAllUsers().get(i).getUserName() + " [" + users.getAllUsers().get(i).getHighScore() + " win(s)]");
                        sleepFor(1);
                    }
                }
                sleepFor(1);
            }
        }
    }
}
