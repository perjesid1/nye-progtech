package nye.progtech.wumpus;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;
import java.util.*;

public class WumpusApp implements SaveHandler{
    private User currentUser;
    private List<String> mapToPlay = new ArrayList<>();
    private int heroXCoordinate;
    private int heroYCoordinate;
    private char heroDirection;
    private int mapDim;
    private List<String> rawMapData = new ArrayList<>();

    public final void run(){
        clearConsole();
        greetThePlayer();
        sleepFor(3);
        clearConsole();
        gameIntro();
        clearConsole();
        SaveHandler.loadAllUsers();
        askForUser();
        SaveHandler.saveUsers();
        loadMapFromFile(false, "currentMap_" + currentUser.getUserName() + ".txt");
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
        currentUser = new User(userName);
        if(allUsers.userExists(userName)){
            currentUser.setHighScore(allUsers.getHighScore(userName));
        }
        else {
            allUsers.add(currentUser);
        }
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
                SaveHandler.saveUsers();
                System.out.println("Data saved successfully!");
            }
            case "M" -> System.out.println("Not implemented in current version!");
            case "P" -> {
                clearConsole();
                System.out.println("Checking map validity...");
                if (!checkMapValidity())
                    break;
                System.out.println("You have found the labyrinth where the terrifying monster lives...");
                System.out.println("You have only one task... And only one chance...");
                sleepFor(4);
                clearConsole();
                greetThePlayer();
                sleepFor(2);
                CurrentGame newGame = new CurrentGame();
                newGame.setCurrentMap(mapToPlay, heroXCoordinate, heroYCoordinate, heroDirection);
                if (newGame.runGame(currentUser.getUserName())) {
                    currentUser.setHighScore(currentUser.getHighScore() + 1);
                    allUsers.setHighScore(currentUser.getUserName(), currentUser.getHighScore());
                }
            }
            case "L" -> {
                clearConsole();
                System.out.println("Please input the file's name without the file extension which you want to load.");
                System.out.println("The file extension must be .txt!");
                loadMapFromFile(true, scn.nextLine() + ".txt");
            }
            case "B" -> {
                allUsers.sort((a, b) -> {
                    if (a.getHighScore() == b.getHighScore()) {
                        return a.getUserName().compareToIgnoreCase(b.getUserName());
                    } else {
                        return Integer.compare(b.getHighScore(), a.getHighScore());
                    }
                });
                if(allUsers.size() > 10){
                    for(int i = 0; i < 10; i++) {
                        System.out.println(i + 1 + ". " + allUsers.get(i).getUserName() + " [" + allUsers.get(i).getHighScore() + " win(s)]");
                        sleepFor(1);
                    }
                }
                else {
                    for(int i = 0; i < allUsers.size(); i++) {
                        System.out.println(i + 1 + ". " + allUsers.get(i).getUserName() + " [" + allUsers.get(i).getHighScore() + " win(s)]");
                        sleepFor(1);
                    }
                }
                sleepFor(1);
            }
        }
    }
    private void loadMapFromFile(boolean manual, String fileName){
        String answer = "N";
        if(manual){
            Scanner scn = new Scanner(System.in);
            do{
                System.out.println("If you load a map from the \"" + fileName + "\" file, your current map will be overwritten.\nDo you want to continue? (Y/N)");
                try {
                    answer = scn.nextLine();
                }
                catch(Exception e)
                {
                    answer = "XXX";
                }
            } while(!(answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("Y")));
        }
        if(answer.equalsIgnoreCase("Y") || !manual) {
            try {
                File mapFile = new File(fileName);
                Scanner scn = new Scanner(mapFile);
                rawMapData.clear();
                while (scn.hasNextLine()) {
                    rawMapData.add(scn.nextLine());
                }
            } catch (FileNotFoundException e) {
                if (manual) {
                    System.out.println("There is no such file as " + fileName + "...");
                } else if(!(fileName.equals("currentMap.txt"))){
                    System.out.println("You don't have any pending games.");
                    loadMapFromFile(false,"currentMap.txt");
                }
                rawMapData.clear();
            }
        }
    }

    private boolean checkMapValidity(){
        try {
            if(rawMapData.isEmpty())
                throw new FaultyMap("Invalid map data!");
            mapToPlay.clear();
            String[] tempString;
            int wumpusCount = 0;
            int goldCount = 0;
            char currentElement;
            for (int i = 0; i < rawMapData.size(); i++) {
                if (i == 0) {
                    tempString = rawMapData.get(i).split(" ");
                    if (tempString.length != 4 || tempString[3].length() != 1)
                        throw new FaultyMap("Mismatch in the number of data in the header.");
                    mapDim = Integer.parseInt(tempString[0]);
                    if(mapDim < 6 || mapDim > 20)
                        throw new FaultyMap("Map size mismatch! The map size must be comply with the 6<=N<=20 rule.");
                    heroYCoordinate = Integer.parseInt(tempString[2])-1;
                    if(heroYCoordinate < 0 || heroYCoordinate > mapDim || tempString[1].length() > 1)
                        throw  new FaultyMap("The hero's position can't be outside the map.");
                    heroXCoordinate = columnToNumber(tempString[1].toLowerCase())-1;
                    if(heroXCoordinate < 0 || heroXCoordinate > mapDim)
                        throw  new FaultyMap("The hero's position can't be outside the map.");
                    if(tempString[3].equalsIgnoreCase("S") || tempString[3].equalsIgnoreCase("N") || tempString[3].equalsIgnoreCase("W") ||tempString[3].equalsIgnoreCase("E")){
                        heroDirection = tempString[3].toUpperCase().charAt(0);
                    } else {
                        throw new FaultyMap("Invalid direction in the header.");
                    }
                    if(rawMapData.size() - 1 != mapDim)
                        throw new FaultyMap("Mismatch in the number of rows or columns in the map.");
                } else {
                    for (int j = 0; j < rawMapData.get(i).length(); j++) {
                        if(j > mapDim)
                            throw new FaultyMap("Mismatch in the number of rows or columns in the map.");
                        currentElement = rawMapData.get(i).charAt(j);
                        if(currentElement == 'U')
                            wumpusCount++;
                        else if(currentElement == 'G')
                            goldCount++;
                        else if(!(currentElement == '_' || currentElement == 'W' || currentElement == 'P'))
                            throw new FaultyMap("Invalid character in the map");
                        if(i-1 == heroYCoordinate && j == heroXCoordinate && (currentElement == 'P' || currentElement == 'W' || currentElement == 'U'))
                            throw new FaultyMap("Player on wrong tile of the map.");
                    }
                    //A fenti for loop leellenőrzi, hogy a sor valid adatokat tartalmaz-e. Mivel javában nincsenek 2D dinamikus tömbök, így Stringként kezelem a mezőket.
                    mapToPlay.add(rawMapData.get(i));
                }
            }
            if(mapDim <= 8 && wumpusCount > 1)
                throw new FaultyMap("Too many Wumpus on the map!");
            else if(mapDim>= 9 && mapDim <=14 && wumpusCount > 2)
                throw new FaultyMap("Too many Wumpus on the map!");
            else if(mapDim > 14 && wumpusCount > 3) {
                throw new FaultyMap("Too many Wumpus on the map!");
            }
            else if(goldCount != 1){
                throw new FaultyMap("Invalid number of gold on the map.");
            }
        }
        catch(Exception e) {
            System.out.println("Something went wrong... Please check the file, if it exists or is valid.");
            return false;
        }
        return true;
    }

    private int columnToNumber(String colName){
        char[] converted = colName.toCharArray();
        return converted[0] - 'a' + 1;
    }
}
