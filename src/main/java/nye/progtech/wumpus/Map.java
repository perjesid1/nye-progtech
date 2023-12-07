package nye.progtech.wumpus;
/**
 *
 */
public class Map {
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
    private static final int TOO_SMALL_MAP_SIZE = 5;
    /**
     *
     */
    private static final int TOO_BIG_MAP_SIZE = 21;
    /**
     *
     */
    public enum DIRECTIONS {
        /**
         *
         */
        E,
        /**
         *
         */
        S,
        /**
         *
         */
        W,
        /**
         *
         */
        N
    }
    /**
     *
     */
    private final int size;
    /**
     *
     */
    private final int escapeRow;
    /**
     *
     */
    private final int escapeCol;
    /**
     *
     */
    private int wumpusCount;
    /**
     *
     */
    private Room[][] rooms;
    /**
     *
     */
    private Hero hero;

    /**
     *
     * @param newSize Size of the newly created map.
     * @param newHero Hero of the newly created map.
     * @throws Exception Exceptions about invalid map size.
     */
    public Map(final int newSize, final Hero newHero) throws Exception {
        this.hero = newHero;
        this.size = newSize;
        this.escapeCol = hero.getCurrentRoom().getColumn();
        this.escapeRow = hero.getCurrentRoom().getRow();
        if (size <= TOO_SMALL_MAP_SIZE) {
            throw new InvalidMapSize("Map too small.");
        }
        if (size >= TOO_BIG_MAP_SIZE) {
            throw new InvalidMapSize("Map too big.");
        }
        if (escapeRow < 0 || escapeRow > size - 1
                || escapeCol < 0 || escapeCol > size - 1) {
            throw new InvalidCoordinates("Hero outside of the map.");
        }

        this.rooms = new Room[size][size];
        this.rooms[escapeRow][escapeCol] = new Room(escapeRow,
                escapeCol, new Escape());
        if (size <= SMALL_MAP_SIZE) {
            this.wumpusCount = SMALL_MAP_WUMPUS_COUNT;
        } else if (size < BIG_MAP_SIZE) {
            this.wumpusCount = MEDIUM_MAP_WUMPUS_COUNT;
        } else {
            this.wumpusCount = BIG_MAP_WUMPUS_COUNT;
        }
        this.hero.setArrows(wumpusCount);
    }
    /**
     *
     * @return Hero object of the Map.
     */
    public Hero getHero() {
        return this.hero;
    }
    /**
     *
     * @return Number of Wumpus' on the Map.
     */
    public int getWumpusCount() {
        return this.wumpusCount;
    }
    /**
     *
     * @param newWumpusCount  Number of Wumpus on the map to set.
     */
    public void setWumpusCount(final int newWumpusCount) {
        this.wumpusCount = newWumpusCount;
    }
    /**
     *
     * @return Size of the Map.
     */
    public int getSize() {
        return this.size;
    }
    /**
     *
     * @param row Row of the Room we want to get.
     * @param col Column of the Room we want to get.
     * @return A specific Room of the Map.
     */
    public Room getRoom(final int row, final int col) {
        return this.rooms[row][col];
    }
    /**
     *
     * @return The Room where the player can Escape if they have the gold.
     */
    public Room getEscapeRoom() {
        return this.getRoom(this.escapeRow, this.escapeCol);
    }
    /**
     *
     * @param row Row of the Room we want to set.
     * @param col Column of the Room we want to set.
     * @param room Room we want to set at the given coordinates.
     */
    public void setRoom(final int row, final int col, final Room room) {
        this.rooms[row][col] = room;
    }
}
