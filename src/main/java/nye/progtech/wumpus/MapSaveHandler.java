package nye.progtech.wumpus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MapSaveHandler extends SaveHandler{
    private String fileName;
    private Map handledMap;
    public MapSaveHandler(String fileName, Map mapToSave){
        super(fileName);
        this.handledMap = mapToSave;
    }

    public MapSaveHandler(){
        super("currentMap");
        this.handledMap = null;
    }

    public MapSaveHandler(String fileName){
        super(fileName);
        this.handledMap = null;
    }

    public Map loadNewMap() throws IOException{
        File mapFile = new File("import\\" + fileName + ".txt");
        Scanner mapScanner = new Scanner(mapFile);
        if(mapFile.exists() && !mapFile.isDirectory()){
            try {
                String firstRowData = mapScanner.nextLine();
                int size = Integer.parseInt(firstRowData.split(" ")[0]);
                char column = firstRowData.split(" ")[1].toCharArray()[0];
                int row = Integer.parseInt(firstRowData.split(" ")[2]);
                char direction = firstRowData.split(" ")[3].toCharArray()[0];
                int wumpusCount = 0;
                int goldCount = 0;
                Hero hero = new Hero(new Room(row, column - 'a' + 1));
                hero.setDirectionAsChar(direction);
                handledMap = new Map(size, hero);
                if(handledMap.getHero().getCurrentRoom().getColumn() > size-1 || handledMap.getHero().getCurrentRoom().getColumn() == 0)
                    throw new IOException("Invalid import file!");
                else if(handledMap.getHero().getCurrentRoom().getRow() > size-1 || handledMap.getHero().getCurrentRoom().getRow() == 0)
                    throw new IOException("Invalid import file!");
                char[] mapData;
                for(int r = 0; r < size; r++){
                    mapData = mapScanner.nextLine().toCharArray();
                    for(int c = 0; c < size; c++){
                        switch(mapData[c]){
                            case '_' -> this.handledMap.setRoom(r,c,new Room(r,c));
                            case 'W' -> this.handledMap.setRoom(r,c, new Room(r,c, new Wall()));
                            case 'U' -> {
                                this.handledMap.setRoom(r,c,new Room(r,c, new Wumpus()));
                                wumpusCount++;
                            }
                            case 'G' ->{
                                this.handledMap.setRoom(r,c, new Room(r,c, new Gold()));
                                goldCount++;
                            }
                            case 'P' -> this.handledMap.setRoom(r,c, new Room(r,c, new Pit()));
                            default -> throw new IOException("Invalid import file!");
                        }
                    }
                }
                //Map validation.
                if(size <= 8 && wumpusCount != 1){
                    throw new IOException("Invalid import file!");
                } else if(size < 14 && size > 8 && wumpusCount != 2){
                    throw new IOException("Invalid import file!");
                } else if(size >= 14 && wumpusCount != 3){
                    throw new IOException("Invalid import file!");
                } else if (goldCount != 1) {
                    throw new IOException("Invalid import file!");
                }
                handledMap.getHero().setArrows(wumpusCount);
            }
            catch (Exception e){
                e.printStackTrace();
                throw new IOException("Invalid import file!");
            }
        } else throw new FileNotFoundException("Missing import file!");

        return handledMap;
    }

    public Map loadMap() throws IOException{
        File mapFile = new File("maps\\" + fileName + ".txt");
        Scanner mapScanner = new Scanner(mapFile);
        Hero hero = new Hero();
        if(mapFile.exists() && !mapFile.isDirectory()){
            try{
                hero.setDirectionAsChar(mapScanner.nextLine().toCharArray()[0]);
                hero.setCurrentRoom(new Room(mapScanner.nextInt(),mapScanner.nextInt()));
                hero.setArrows(mapScanner.nextInt());
                hero.setHasGold(mapScanner.nextInt() == 1);
                this.handledMap = new Map(mapScanner.nextInt(), hero);
                int wumpusCount = mapScanner.nextInt();
                int currentWumpus = 0;
                boolean[] wumpusAlive = new boolean[wumpusCount];
                for(int i = 0; i < wumpusCount; i++){
                    wumpusAlive[i] = mapScanner.nextInt() == 1;
                }
                char[] mapData;
                for(int i = 0; i<this.handledMap.getSize(); i++){
                    mapData = mapScanner.nextLine().toCharArray();
                    for(int j = 0; j < this.handledMap.getSize(); j++){
                        switch(mapData[j]){
                            case '_' -> this.handledMap.setRoom(i,j,new Room(i,j));
                            case 'E' -> {
                                this.handledMap.setRoom(i,j, new Room(i,j, new Escape()));
                                if(this.handledMap.getHero().isHasGold())
                                    ((Escape)this.handledMap.getRoom(i,j).getEvent()).setCanEscape(true);
                            }
                            case 'W' -> this.handledMap.setRoom(i,j, new Room(i,j, new Wall()));
                            case 'U' -> {
                                this.handledMap.setRoom(i,j,new Room(i,j, new Wumpus()));
                                if(!wumpusAlive[currentWumpus])
                                    ((Wumpus)this.handledMap.getRoom(i,j).getEvent()).setAlive(false);
                                currentWumpus++;
                            }
                            case 'G' ->{
                                this.handledMap.setRoom(i,j, new Room(i,j, new Gold()));
                                if(this.handledMap.getHero().isHasGold())
                                    ((Gold)this.handledMap.getRoom(i,j).getEvent()).setFound(true);
                            }
                            case 'P' -> this.handledMap.setRoom(i,j, new Room(i,j, new Pit()));
                            default -> throw new IOException("Corrupted save file!");
                        }
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
                throw new IOException("Corrupted save file!");
            }
        } else throw new FileNotFoundException("Missing save file!");
        return this.handledMap;
    }

    public void saveMap(String userName) throws IOException{
        File mapFile = new File("maps\\current_map_" + userName + ".txt");
        if(mapFile.exists() && !mapFile.isDirectory()){
            mapFile.delete();
        }
        mapFile.createNewFile();
        String[] mapData = new String[this.handledMap.getSize()];
        FileWriter mapWriter = new FileWriter(mapFile);

        //Hero's Data (static length)
        mapWriter.write(this.handledMap.getHero().getDirectionAsChar());
        mapWriter.write(this.handledMap.getHero().getCurrentRoom().getColumn());
        mapWriter.write(this.handledMap.getHero().getCurrentRoom().getRow());
        mapWriter.write(this.handledMap.getHero().getArrows());
        if(this.handledMap.getHero().isHasGold())
            mapWriter.write(1);
        else
            mapWriter.write(0);
        mapWriter.write(this.handledMap.getSize());
        mapWriter.write(this.handledMap.getWumpusCount());
        for(int r = 0; r < this.handledMap.getSize(); r++){
            for(int c = 0; c <this.handledMap.getSize(); c++){
                if(this.handledMap.getRoom(r,c).getEvent() == null)
                    mapData[r] += '_';
                else
                    mapData[r] += this.handledMap.getRoom(r,c).getEvent().print();
                if(this.handledMap.getRoom(r,c).getEvent().print() == 'U')
                {
                    if(((Wumpus)this.handledMap.getRoom(r,c).getEvent()).isAlive())
                        mapWriter.write(1);
                    else
                        mapWriter.write(0);
                }
            }
        }
        for(int i = 0; i<this.handledMap.getSize(); i++)
            mapWriter.write(mapData[i]);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void removeSavedProgress(String userName){
        File mapFile = new File("maps\\current_map_" + userName + ".txt");
        if(mapFile.exists() && !mapFile.isDirectory()){
            mapFile.delete();
        }
    }

    public Map getMap(){ return this.handledMap; }
}