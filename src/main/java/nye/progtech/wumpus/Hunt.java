package nye.progtech.wumpus;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Hunt {
    private String userName;
    public Hunt(String userName){ this.userName = userName; }
    private boolean success = false;
    private boolean exit = false;
    public boolean run(@NotNull MapSaveHandler map){
        Scanner scn = new Scanner(System.in);
        String inputCommand;
        do {
            //showHint(map.getMap());
            inputCommand = scn.nextLine();
            if(inputCommand.equalsIgnoreCase("help")){
                System.out.println("Currently accepted commands:");
                System.out.println("help -> This is what got you here.");
                System.out.println("hint -> You have problems with your memory, heh?...");
                System.out.println("move [E/W/N/S] -> Moves in the tunnels.");
                System.out.println("shoot [E/W/N/S] -> Shoots in the selected direction.");
                System.out.println("postpone -> Saves the current progress and exits to the main menu.");
                System.out.println("exit -> Exits to the main menu.");
            } else if(inputCommand.toUpperCase().endsWith(" E") || inputCommand.toUpperCase().endsWith(" W") ||
                    inputCommand.toUpperCase().endsWith(" N") || inputCommand.toUpperCase().endsWith(" S") ) {
                if(inputCommand.toUpperCase().startsWith("MOVE ")) {
                    map.getMap().getHero().setDirectionAsChar(inputCommand.toUpperCase().charAt(5));
                    moveHero(map.getMap());
                    showHint(map.getMap());
                } else if(inputCommand.toUpperCase().startsWith("SHOOT ")) {
                    if(map.getMap().getHero().getArrows() > 0) {
                        map.getMap().getHero().setDirectionAsChar(inputCommand.toUpperCase().charAt(6));
                        shootArrow(map.getMap());
                    } else {
                        System.out.println("You don't have any arrows left...");
                    }
                }else System.out.println("Incorrect input. Try again!");
            } else if(inputCommand.equalsIgnoreCase("HINT")) {
                showHint(map.getMap());
            } else if(inputCommand.equalsIgnoreCase("EXIT")){
                exit = true;
            } else if (inputCommand.equalsIgnoreCase("POSTPONE")) {
                Throwable throwable = null;
                try {
                    map.saveMap(this.userName);
                }catch (Exception e){
                    throwable = e;
                    System.out.println("Unable to save your game progress - corrupt save file!");
                }
                if(throwable == null)
                    System.out.println("Progress saved successfully!");

            } else if(userName.equals("Blesh") && inputCommand.equalsIgnoreCase("PRINT")){
                printMap(map.getMap());
            }else {
                System.out.println("Incorrect input. Try again!");
            }
        } while(!exit);
        if(success)
            map.removeSavedProgress(userName);
        return success;
    }

    public void showHint(@NotNull Map map){
        int row = map.getHero().getCurrentRoom().getRow();
        int col = map.getHero().getCurrentRoom().getColumn();
        int limit = map.getSize()-1;
        if(map.getRoom(row == limit ? 0 : row+1,col).isHasEvent())
            map.getRoom(row == limit ? 0 : row+1,col).getEvent().message();
        if(map.getRoom(row == 0 ? limit : row-1,col).isHasEvent())
            map.getRoom(row == 0 ? limit : row-1, col).getEvent().message();
        if(map.getRoom(row,col == limit ? 0 : col + 1).isHasEvent())
            map.getRoom(row,col == limit ? 0 : col + 1).getEvent().message();
        if(map.getRoom(row,col == 0 ? limit : col - 1).isHasEvent())
            map.getRoom(row,col == 0 ? limit : col - 1).getEvent().message();
    }

    private void moveHero(@NotNull Map map){
        Room nextRoom = new Room();
        int row = map.getHero().getCurrentRoom().getRow();
        int col = map.getHero().getCurrentRoom().getColumn();
        int limit = map.getSize()-1;

        switch (map.getHero().getDirectionAsChar()) {
            case 'W' -> nextRoom = map.getRoom(row, col == 0 ? limit : col - 1);
            case 'E' -> nextRoom = map.getRoom(row, col == limit ? 0 : col + 1);
            case 'S' -> nextRoom = map.getRoom(row == limit ? 0 : row + 1, col);
            case 'N' -> nextRoom = map.getRoom(row == 0 ? limit : row - 1, col);
        }
        if(nextRoom.isHasEvent()){
            if(nextRoom.getEvent().getClass() == Wall.class){
                nextRoom.getEvent().encounter();
            }else if (nextRoom.getEvent().getClass() == Wumpus.class){
                if(((Wumpus)nextRoom.getEvent()).isAlive()) {
                    nextRoom.getEvent().encounter();
                    exit = true;
                }
            }else if(nextRoom.getEvent().getClass() == Gold.class){
                if(!((Gold)nextRoom.getEvent()).isFound()) {
                    nextRoom.getEvent().encounter();
                    map.getHero().setHasGold(true);
                    ((Escape)map.getEscapeRoom().getEvent()).setCanEscape(true);
                }
                map.getHero().setCurrentRoom(nextRoom);
            }
            else if (nextRoom.getEvent().getClass() == Pit.class){
                nextRoom.getEvent().encounter();
                exit = true;
            }else if(nextRoom.getEvent().getClass() == Escape.class) {
                if(((Escape) nextRoom.getEvent()).isCanEscape()) {
                    nextRoom.getEvent().encounter();
                    success = true;
                    exit = true;
                }
            }
        }else
        {
            map.getHero().setCurrentRoom(nextRoom);
        }
    }

    public void shootArrow(@NotNull Map map){
        map.getHero().setArrows(map.getHero().getArrows()-1);
        int row = map.getHero().getCurrentRoom().getRow();
        int col = map.getHero().getCurrentRoom().getColumn();
        int limit = map.getSize();
        Room nextRoom = new Room();
        int originalWumpusCount = map.getWumpusCount();
        boolean shouldExit = false;

        do {
            switch (map.getHero().getDirectionAsChar()) {
                case 'W' -> nextRoom = map.getRoom(row, col == 0 ? limit : col - 1);
                case 'E' -> nextRoom = map.getRoom(row, col == limit ? 0 : col + 1);
                case 'S' -> nextRoom = map.getRoom(row == limit ? 0 : row + 1, col);
                case 'N' -> nextRoom = map.getRoom(row == 0 ? limit : row - 1, col);
            }
            row = nextRoom.getRow();
            col = nextRoom.getColumn();
            if(nextRoom.isHasEvent()){
                if(nextRoom.getEvent().getClass() == Wall.class)
                    shouldExit = true;
                else if(nextRoom.getEvent().getClass() == Wumpus.class){
                    ((Wumpus)nextRoom.getEvent()).setAlive(false);
                    map.setWumpusCount(map.getWumpusCount()-1);
                }else if(nextRoom.equals(map.getHero().getCurrentRoom()))
                    shouldExit = true;
            }
        } while (!shouldExit);

        if(nextRoom.equals(map.getHero().getCurrentRoom())){
            System.out.println("You really are not smart, aren't you? You just killed yourself with your arrow...\nGAME OVER!");
            exit = true;
        } else {
            int difference = originalWumpusCount - map.getWumpusCount();
            if(map.getSize() > 14 && difference == 3 || (map.getSize() >= 9 && map.getSize() <= 14 && difference == 2)){
                System.out.println("\"AAAAARGHHHH!\" You lucky bastard! You all of them with one shot!\nGood job!");
                exit = true;
                success = true;
            } else if(difference != 0) {
                if(map.getWumpusCount() == 0)
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

    public void printMap(@NotNull Map map){
        String toPrint = "";
        for(int r = 0; r<map.getSize(); r++){
            toPrint="";
            for(int c = 0; c<map.getSize(); c++){
                if(map.getHero().getCurrentRoom().getRow() == r && map.getHero().getCurrentRoom().getColumn() == c)
                    toPrint += "H";
                else if(map.getRoom(r,c).isHasEvent())
                    toPrint += map.getRoom(r,c).getEvent().print();
                else
                    toPrint += '_';
            }
            System.out.println(toPrint);
        }
    }
}
