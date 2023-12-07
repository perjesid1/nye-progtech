package nye.progtech.wumpus;
/**
 *
 */
public class Room {
    /**
     *
     */
    private int row;
    /**
     *
     */
    private int column;
    /**
     *
     */
    private boolean hasEvent;
    /**
     *
     */
    private Event event;
    /**
     *
     */
    public Room() {
        this.row = 0;
        this.column = 0;
        this.hasEvent = false;
        this.event = null;
    }
    /**
     *
     * @param newRow Row of the Room on the Map.
     * @param newColumn Column of the Room on the Map.
     */
    public Room(final int newRow, final int newColumn) {
        this.row = newRow;
        this.column = newColumn;
        this.hasEvent = false;
        this.event = null;
    }
    /**
     *
     * @param newRow Row of the Room on the Map.
     * @param newColumn Column of the Room on the Map.
     * @param newEvent Event associated to the Room.
     */
    public Room(final int newRow, final int newColumn, final Event newEvent) {
        this.row = newRow;
        this.column = newColumn;
        this.event = newEvent;
        this.hasEvent = true;
    }
    /**
     *
     * @return Row of the Room on the Map.
     */
    public int getRow() {
        return this.row;
    }
    /**
     *
     * @return Column of the Room on the Map.
     */
    public int getColumn() {
        return this.column;
    }
    /**
     *
     * @return True if the Room has an even associated, otherwise false.
     */
    public boolean isHasEvent() {
        return hasEvent;
    }
    /**
     *
     * @return Event associated to the Room.
     */
    public Event getEvent() {
        return this.event;
    }
}
