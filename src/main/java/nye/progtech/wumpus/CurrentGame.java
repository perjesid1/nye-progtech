package nye.progtech.wumpus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrentGame{
    private List<String> currentMap = new ArrayList<>();
    private List<String> originalMap = new ArrayList<>();
    private String currentPlayerName;

    public void setCurrentMap(List<String> mapToSet, int heroXCoordinate, int heroYCoordinate, char heroDirection) {
        this.heroDirection = heroDirection;
        heroCoordinates[0] = heroXCoordinate;
        heroCoordinates[1] = heroYCoordinate;

        this.currentMap = mapToSet;
        this.originalMap = mapToSet;
    }
    private int[] heroCoordinates = new int[] {0,0};
    private int[] exitCoordinates = new int[] {0,0};
    private boolean heroHasGold = false;
    private int heroArrows;
    private int remainingWumpus;
    private char heroDirection;
    private char west;
    private char south;
    private char east;
    private char north;
    private boolean exit = false;
    private boolean success = false;

    private void initializeVariables(){
        for(int x = 0; x < currentMap.size();x++){
            //Miven a map NxN méretű kell legyen, mindkét loopban helytálló a .size()
            for(int y = 0; y < currentMap.size();y++){
                if(currentMap.get(x).charAt(y) == 'U')
                    remainingWumpus++;
            }
        }
        exitCoordinates[0] = heroCoordinates[0];
        exitCoordinates[1] = heroCoordinates[1];
        heroArrows = remainingWumpus;
    }

    public boolean runGame(String currentUserName){
        currentPlayerName = currentUserName;
        saveProgess(originalMap, false);
        initializeVariables();
        Scanner scn = new Scanner(System.in);
        String inputCommand;
        do {
            showHint();
            inputCommand = scn.nextLine();
            if(inputCommand.equalsIgnoreCase("help")){
                System.out.println("Currently accepted commands:");
                System.out.println("help -> This is what got you here.");
                System.out.println("hint -> You have problems with your memory, heh?...");
                System.out.println("move [E/W/N/S] -> Moves in the tunnels.");
                System.out.println("shoot [E/W/N/S] -> Shoots in the selected direction.");
                System.out.println("postpone -> Saves the current progress and exits to the main menu.");
                System.out.println("exit -> Exits to the main menu.");
            } else if(inputCommand.toUpperCase().endsWith("E") || inputCommand.toUpperCase().endsWith("W") ||
                    inputCommand.toUpperCase().endsWith("N") || inputCommand.toUpperCase().endsWith("S") ) {
                if(inputCommand.toUpperCase().startsWith("MOVE ")) {
                    heroDirection = inputCommand.toUpperCase().charAt(5);
                    moveHero();
                } else if(inputCommand.toUpperCase().startsWith("SHOOT ")) {
                    if(heroArrows > 0) {
                        heroDirection = inputCommand.toUpperCase().charAt(6);
                        shootArrow();
                    } else {
                        System.out.println("You don't have any arrows left...");
                    }
                }
            } else if(inputCommand.equalsIgnoreCase("HINT")) {
                showHint();
            } else if(inputCommand.equalsIgnoreCase("EXIT")){
                exit = true;
            } else if (inputCommand.equalsIgnoreCase("POSTPONE")) {
                saveProgess(currentMap, true);
            } else if(currentPlayerName.equals("Blesh") && inputCommand.equalsIgnoreCase("PRINT")){
                printMap();
            }else {
                System.out.println("Incorrect input. Try again!");
            }
        } while(!exit);
        return success;
    }
    private void showHint(){
        String hintText = new String();

        scanEnvironment(heroCoordinates);

        if(west == 'U' || south =='U' || east == 'U' || north == 'U') {
            hintText += "You feel a foul stench...\n";
        }
        if (west == 'P' || south =='P' || east == 'P' || north == 'P') {
            hintText += "You feel a slight breeze...\n";
        }
        if(west == 'G' || south =='G' || east == 'G' || north == 'G') {
            hintText += "You saw something glint in the corner of your eye...\n";
        }
        if(heroHasGold){
            hintText+= "You have the gold, better get out of here...";
        }
        hintText += "Remaining arrows: " + heroArrows +"\n";
        hintText += "Possible directions:";
        if(west != 'W') hintText += " West;";
        if(south != 'W') hintText += " South;";
        if(east != 'W') hintText += " East;";
        if(north != 'W') hintText += " North;";
        hintText+="\nWhat would you like to do?\n";
        System.out.println(hintText);
    }

    private void saveProgess(List<String> mapToSave, boolean userBound){
        String fileName;
        if(userBound)
            fileName = "currentMap_" + currentPlayerName + ".txt";
        else
            fileName = "currentMap.txt";

        try {
            File saveFile = new File(fileName);
            if (saveFile.createNewFile()) {
                FileWriter saveWriter = new FileWriter(fileName);

                saveWriter.write(mapToSave.size() + " " + (char)(heroCoordinates[0]+'a') + " " + (heroCoordinates[1]+1) + " " + heroDirection + "\n");
                for(int i = 0; i<mapToSave.size();i++)
                    saveWriter.write(mapToSave.get(i)+"\n");
                saveWriter.close();
            } else {
                saveFile.delete();
                saveFile.createNewFile();
                FileWriter saveWriter = new FileWriter(fileName);

                saveWriter.write(mapToSave.size() + " " + (char)(heroCoordinates[0]+'a') + " " + (heroCoordinates[1]+1) + " " + heroDirection + "\n");
                for(int i = 0; i<mapToSave.size();i++)
                    saveWriter.write(mapToSave.get(i)+"\n");
                saveWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Error during saving current map!");
        }
    }

    private void moveHero(){
        scanEnvironment(heroCoordinates);
        char nextRoom = 'X';
        boolean canMove = false;
        switch (heroDirection) {
            case 'W' -> nextRoom = west;
            case 'E' -> nextRoom = east;
            case 'S' -> nextRoom = south;
            case 'N' -> nextRoom = north;
        }
        switch (nextRoom) {
            case 'W' -> System.out.println("Banging your head into the wall will not help your situation...");
            case 'U' -> {
                System.out.println("AH! The Wumpus got you!\nGAME OVER");
                exit = true;
            }
            case 'G' -> {
                System.out.println("HAH! You have found the gold! Get out of here as fast as you can...");
                heroHasGold = true;
                canMove = true;
            }
            case 'P' -> {
                System.out.println("AAAAAAH! You have fallen into a bottomless pit...\nGAME OVER");
                exit = true;
            }
            case '_' -> {
                canMove = true;
            }
        }
        if(canMove)
        {
            switch (heroDirection) {
                case 'W' -> {
                    if (heroCoordinates[0] == 0) heroCoordinates[0] = currentMap.size() - 1;
                    else heroCoordinates[0]--;
                }
                case 'E' -> {
                    if (heroCoordinates[0] == currentMap.size() - 1) heroCoordinates[0] = 0;
                    else heroCoordinates[0]++;
                }
                case 'S' -> {
                    if (heroCoordinates[1] == 0) heroCoordinates[1] = currentMap.size() - 1;
                    else heroCoordinates[1]++;
                }
                case 'N' -> {
                    if (heroCoordinates[1] == currentMap.size() - 1) heroCoordinates[1] = 0;
                    else heroCoordinates[1]--;
                }

            }
            if (heroCoordinates[0] == exitCoordinates[0] && heroCoordinates[1] == exitCoordinates[1] && heroHasGold) {
                System.out.println("Finally, sunlight! You have managed to escape the Wumpus and grab the gold!\nGood job!");
                exit = true;
                success = true;
            }
        }
    }

    private void scanEnvironment(int[] position)
    {
        if(position[0] == 0) west = currentMap.get(currentMap.size()-1).charAt(position[1]);
        else west = currentMap.get(position[1]).charAt(position[0]-1);
        if(position[0] == currentMap.size()-1) east = currentMap.get(0).charAt(position[1]);
        else east = currentMap.get(position[1]).charAt(position[0]+1);
        if(position[1] == 0) south = currentMap.get(position[0]).charAt(currentMap.size()-1);
        else south = currentMap.get(position[1]+1).charAt(position[0]);
        if(position[1] == currentMap.size()-1) north = currentMap.get(position[0]).charAt(0);
        else north = currentMap.get(position[1]-1).charAt(position[0]);
    }

    private void shootArrow(){
        heroArrows--;
        int[] arrowCoordinates = new int[] {0,0};
        arrowCoordinates[0] = heroCoordinates[0];
        arrowCoordinates[1] = heroCoordinates[1];
        char arrowDirection = heroDirection;
        char nextRoom = 'X';
        int originalWumpusCount = remainingWumpus;
        boolean shouldExit = false;
        do {
            scanEnvironment(arrowCoordinates);
            switch (arrowDirection) {
                case 'W' -> {
                    nextRoom = west;
                    if (arrowCoordinates[0] == 0) arrowCoordinates[0] = currentMap.size() - 1;
                    else arrowCoordinates[0]--;
                }
                case 'E' -> {
                    nextRoom = east;
                    if (arrowCoordinates[0] == 0) arrowCoordinates[0] = currentMap.size() - 1;
                    else arrowCoordinates[0]++;
                }
                case 'S' -> {
                    nextRoom = south;
                    if (arrowCoordinates[1] == currentMap.size() - 1) arrowCoordinates[1] = 0;
                    else arrowCoordinates[1]++;
                }
                case 'N' -> {
                    nextRoom = north;
                    if (arrowCoordinates[1] == currentMap.size() - 1) arrowCoordinates[1] = 0;
                    else arrowCoordinates[1]--;
                }
            }
            if(nextRoom == 'U')
                remainingWumpus--;
            if(nextRoom == 'W')
                shouldExit = true;
            if(arrowCoordinates[0] == heroCoordinates[0] && arrowCoordinates[1] == heroCoordinates[1])
                shouldExit = true;
        } while (shouldExit == false);

        if(arrowCoordinates[0] == heroCoordinates[0] && arrowCoordinates[1] == heroCoordinates[1]){
            System.out.println("You really are not smart, aren't you? You just killed yourself with your arrow...\nGAME OVER!");
            exit = true;
        } else {
            int difference = originalWumpusCount - remainingWumpus;
            if(originalMap.size() > 14 && difference == 3 || (originalMap.size() >= 9 && originalMap.size() <= 14 && difference == 2)){
                System.out.println("\"AAAAARGHHHH!\" You lucky bastard! You all of them with one shot!\nGood job!");
                exit = true;
                success = true;
            } else if(difference != 0) {
                if(remainingWumpus == 0)
                {
                    System.out.println("\"AAAAARGHHHH!\" You lucky bastard! You killed the last Wumpus!\nGood job!");
                    exit = true;
                    success = true;
                } else {
                    System.out.println("\"AAAAARGHHHH!\" You sure are lucky to hit anything in this pitch black environment...\nYou killed a Wumpus!");
                }
            } else System.out.println("No luck this time... The only thing the arrow hit is a wall.");
        }
    }

    private void printMap(){
        String toPrint;
        for(int i = 0; i<currentMap.size(); i++){
            if(i == heroCoordinates[1]){
                if(heroCoordinates[0] > 0){
                    if(heroCoordinates[0] < currentMap.get(i).length())
                        toPrint = currentMap.get(i).substring(0,heroCoordinates[0]) + "H" + currentMap.get(i).substring(heroCoordinates[0]+1);
                    else
                        toPrint = currentMap.get(i).substring(0,heroCoordinates[0]) + "H";
                } else {
                    toPrint = "H" + currentMap.get(i).substring(heroCoordinates[0]+1);
                }
            } else {
                toPrint = currentMap.get(i);
            }
            System.out.println(toPrint);
        }
    }
}
