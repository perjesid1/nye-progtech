package nye.progtech.wumpus;

import java.util.Scanner;

public class Hunt {
    private String userName;
    public Hunt(String userName){ this.userName = userName; }
    private boolean success = false;
    private boolean exit = false;
    public boolean run(MapSaveHandler map){
        Scanner scn = new Scanner(System.in);
        String inputCommand;
        do {
            showHint(map.getMap());
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
                    map.getMap().getHero().setDirectionAsChar(inputCommand.toUpperCase().charAt(5));
                    moveHero(map.getMap());
                } else if(inputCommand.toUpperCase().startsWith("SHOOT ")) {
                    if(map.getMap().getHero().getArrows() > 0) {
                        map.getMap().getHero().setDirectionAsChar(inputCommand.toUpperCase().charAt(6));
                        shootArrow();
                    } else {
                        System.out.println("You don't have any arrows left...");
                    }
                }
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
                printMap();
            }else {
                System.out.println("Incorrect input. Try again!");
            }
        } while(!exit);
        if(success)
            map.removeSavedProgress(userName);
        return success;
    }

    public void showHint(Map map){
        int row = map.getHero().getCurrentRoom().getRow();
        int col = map.getHero().getCurrentRoom().getColumn();
        int limit = map.getSize()-1;
        if(map.getRoom(row == limit ? 0 : row+1,col).isHasEvent())
            map.getRoom(row == limit ? 0 : row+1,col).getEvent().message();
        if(map.getRoom(row == 0 ? row-1 : limit,col).isHasEvent())
            map.getRoom(row == 0 ? row - 1 : limit, col).getEvent().message();
        if(map.getRoom(row,col == limit ? 0 : col + 1).isHasEvent())
            map.getRoom(row,col == limit ? 0 : col + 1).getEvent().message();
        if(map.getRoom(row,col == 0 ? limit : col - 1).isHasEvent())
            map.getRoom(row,col == 0 ? limit : col - 1).getEvent().message();
    }

    private void moveHero(Map map){
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
                if(((Gold)nextRoom.getEvent()).isFound()) {
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
}