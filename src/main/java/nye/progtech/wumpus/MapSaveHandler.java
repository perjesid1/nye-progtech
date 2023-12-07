package nye.progtech.wumpus;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 */
public class MapSaveHandler extends SaveHandler {
    /**
     *
     */
    private static final int SIZE_POSITION = 0;
    /**
     *
     */
    private static final int COLUMN_POSITION = 1;
    /**
     *
     */
    private static final int ROW_POSITION = 2;
    /**
     *
     */
    private static final int DIRECTION_POSITION = 3;
    /**
     *
     */
    private static final int GOLD_COUNT = 1;
    /**
     *
     */
    private static final int SMALL_MAP_SIZE = 8;
    /**
     *
     */
    private static final int BIG_MAP_SIZE = 15;
    /**
     *
     */
    private static final int MEDIUM_MAP_WUMPUS_COUNT = 2;
    /**
     *
     */
    private static final int BIG_MAP_WUMPUS_COUNT = 3;
    /**
     *
     */
    private static final int SMALL_MAP_WUMPUS_COUNT = 1;
    /**
     *
     */
    private Map handledMap;
    /**
     *
     */
    public MapSaveHandler() {
        super("currentMap");
        this.handledMap = null;
        File directory = new File("maps");
        if (!directory.exists()) {
            directory.mkdir();
        }
        directory = new File("import");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    /**
     *
     * @throws IOException Exception of the FileHandler.
     */
    public void loadNewMap() throws IOException {
        File mapFile = new File("import\\" + super.getFileName() + ".txt");
        Scanner mapScanner = new Scanner(mapFile);
        if (mapFile.exists() && !mapFile.isDirectory()) {
            try {
                String firstRowData = mapScanner.nextLine();
                int size = Integer.parseInt(firstRowData
                        .split(" ")[SIZE_POSITION]);
                char column = firstRowData.split(" ")[COLUMN_POSITION]
                        .toLowerCase().toCharArray()[0];
                int row = Integer.parseInt(firstRowData
                        .split(" ")[ROW_POSITION]) - 1;
                char direction = firstRowData.split(" ")[DIRECTION_POSITION]
                        .toUpperCase().toCharArray()[0];
                int wumpusCount = 0;
                int goldCount = 0;
                Hero hero = new Hero(new Room(row, column - 'a'));
                hero.setDirectionAsChar(direction);
                handledMap = new Map(size, hero);
                if (handledMap.getHero()
                        .getCurrentRoom().getColumn() > size - 1
                        || handledMap.getHero()
                        .getCurrentRoom().getColumn() == 0) {
                    throw new IOException("Invalid import file!");
                } else if (handledMap.getHero()
                        .getCurrentRoom().getRow() > size - 1
                        || handledMap.getHero()
                        .getCurrentRoom().getRow() == 0) {
                    throw new IOException("Invalid import file!");
                }
                char[] mapData;
                for (int r = 0; r < size; r++) {
                    mapData = mapScanner.nextLine().toCharArray();
                    for (int c = 0; c < size; c++) {
                        switch (mapData[c]) {
                            case '_' -> this.handledMap
                                    .setRoom(r, c, new Room(r, c));
                            case 'W' -> this.handledMap.setRoom(r, c,
                                            new Room(r, c, new Wall()));
                            case 'U' -> {
                                this.handledMap.setRoom(r, c,
                                        new Room(r, c, new Wumpus()));
                                wumpusCount++;
                            }
                            case 'G' -> {
                                this.handledMap.setRoom(r, c,
                                        new Room(r, c, new Gold()));
                                goldCount++;
                            }
                            case 'P' -> this.handledMap.setRoom(r, c,
                                    new Room(r, c, new Pit()));
                            default -> throw
                                    new IOException("Invalid import file!");
                        }
                    }
                }
                this.handledMap.setRoom(row, column - 'a',
                        new Room(row, column - 'a', new Escape()));
                //Map validation.
                if (size <= SMALL_MAP_SIZE
                        && wumpusCount != SMALL_MAP_WUMPUS_COUNT) {
                    throw new IOException("Invalid import file!");
                } else if (size < BIG_MAP_SIZE
                        && size > SMALL_MAP_SIZE
                        && wumpusCount != MEDIUM_MAP_WUMPUS_COUNT) {
                    throw new IOException("Invalid import file!");
                } else if (size >= BIG_MAP_SIZE
                        && wumpusCount != BIG_MAP_WUMPUS_COUNT) {
                    throw new IOException("Invalid import file!");
                } else if (goldCount != GOLD_COUNT) {
                    throw new IOException("Invalid import file!");
                }
                this.handledMap.getHero().setArrows(wumpusCount);
                mapScanner.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException("Invalid import file!");
            }
        } else {
            throw new FileNotFoundException("Missing import file!");
        }
    }

    /**
     *
     * @throws IOException Exception of the FileHandler.
     */
    public void loadMap() throws IOException {
        File mapFile = new File("maps\\" + super.getFileName() + ".txt");
        Scanner mapScanner = new Scanner(mapFile);
        Hero hero = new Hero();
        if (mapFile.exists() && !mapFile.isDirectory()) {
            try {
                hero.setDirectionAsChar(mapScanner.nextLine().toCharArray()[0]);
                hero.setCurrentRoom(new Room(mapScanner
                        .nextInt(), mapScanner.nextInt()));
                hero.setArrows(mapScanner.nextInt());
                hero.setHasGold(mapScanner.nextInt() == 1);
                this.handledMap = new Map(mapScanner.nextInt(), hero);
                int wumpusCount = mapScanner.nextInt();
                int currentWumpus = 0;
                boolean[] wumpusAlive = new boolean[wumpusCount];
                for (int i = 0; i < wumpusCount; i++) {
                    wumpusAlive[i] = mapScanner.nextInt() == 1;
                }
                String mapData;
                for (int i = 0; i < this.handledMap.getSize(); i++) {
                    mapData = mapScanner.next();
                    for (int j = 0; j < this.handledMap.getSize(); j++) {
                        switch (mapData.toCharArray()[j]) {
                            case '_' -> this.handledMap
                                    .setRoom(i, j, new Room(i, j));
                            case 'E' -> {
                                this.handledMap.setRoom(i, j,
                                        new Room(i, j, new Escape()));
                                if (this.handledMap.getHero().isHasGold()) {
                                    ((Escape) this.handledMap
                                            .getRoom(i, j).getEvent())
                                            .setCanEscape(true);
                                }
                            }
                            case 'W' -> this.handledMap.setRoom(i, j,
                                    new Room(i, j, new Wall()));
                            case 'U' -> {
                                this.handledMap.setRoom(i, j,
                                        new Room(i, j, new Wumpus()));
                                if (!wumpusAlive[currentWumpus]) {
                                    ((Wumpus) this.handledMap
                                            .getRoom(i, j).getEvent())
                                            .setAlive(false);
                                }
                                currentWumpus++;
                            }
                            case 'G' -> {
                                this.handledMap.setRoom(i, j,
                                        new Room(i, j, new Gold()));
                                if (this.handledMap.getHero().isHasGold()) {
                                    ((Gold) this.handledMap
                                            .getRoom(i, j).getEvent())
                                            .setFound(true);
                                }
                            }
                            case 'P' -> this.handledMap.setRoom(i, j,
                                    new Room(i, j, new Pit()));
                            default -> throw
                                    new IOException("Corrupted save file!");
                        }
                    }
                }
                mapScanner.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException("Corrupted save file!");
            }
        } else {
            throw new FileNotFoundException("Missing save file!");
        }
    }
    /**
     *
     * @param userName Map to Save.
     * @throws IOException Exceptions of the file handler.
     */
    public void saveMap(final String userName) throws IOException {
        File mapFile = new File("maps\\current_map_" + userName + ".txt");
        if (mapFile.exists() && !mapFile.isDirectory()) {
            mapFile.delete();
        }
        mapFile.createNewFile();
        String[] mapData = new String[this.handledMap.getSize()];
        FileWriter mapWriter = new FileWriter(mapFile);

        //Hero's Data (static length)
        mapWriter.write(this.handledMap.getHero()
                .getDirectionAsChar() + "\n");
        mapWriter.write(this.handledMap.getHero()
                .getCurrentRoom().getRow() + "\n");
        mapWriter.write(this.handledMap.getHero()
                .getCurrentRoom().getColumn() + "\n");
        mapWriter.write(this.handledMap.getHero()
                .getArrows() + "\n");
        if (this.handledMap.getHero().isHasGold()) {
            mapWriter.write("1\n");
        } else {
            mapWriter.write("0\n");
        }
        mapWriter.write(this.handledMap.getSize() + "\n");
        mapWriter.write(String.valueOf(this.handledMap
                .getWumpusCount()) + "\n");
        for (int r = 0; r < this.handledMap.getSize(); r++) {
            mapData[r] = "";
            for (int c = 0; c < this.handledMap.getSize(); c++) {
                if (this.handledMap.getRoom(r, c).getEvent() == null) {
                    mapData[r] += '_';
                } else {
                    mapData[r] += this.handledMap.getRoom(r, c)
                            .getEvent().print();
                    if (this.handledMap.getRoom(r, c)
                            .getEvent().print() == 'U') {
                        if (((Wumpus) this.handledMap
                                .getRoom(r, c).getEvent()).isAlive()) {
                            mapWriter.write("1\n");
                        } else {
                            mapWriter.write("0\n");
                        }
                    }
                }
            }
            mapData[r] += "\n";
        }
        for (int i = 0; i < this.handledMap.getSize(); i++) {
            mapWriter.write(mapData[i]);
        }

        mapWriter.close();
    }
    /**
     *
     * @param fileName Name of the map file to set.
     */
    public void setFileName(final String fileName) {
        super.setFileName(fileName);
    }
    /**
     *
     * @param userName User to remove the save of.
     */
    public void removeSavedProgress(final String userName) {
        File mapFile = new File("maps\\current_map_" + userName + ".txt");
        if (mapFile.exists() && !mapFile.isDirectory()) {
            mapFile.delete();
        }
    }
    /**
     *
     * @return Current Map.
     */
    public Map getMap() {
        return this.handledMap;
    }
}
