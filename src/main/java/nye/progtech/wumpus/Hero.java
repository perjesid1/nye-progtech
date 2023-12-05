package nye.progtech.wumpus;
/**
 *
 */
public class Hero {
    /**
     *
     */
    private boolean hasGold;
    /**
     *
     */
    private int arrows;
    /**
     *
     */
    private Room currentRoom;
    /**
     *
     */
    private Map.directions direction;
    /**
     *
     */
    public Hero() {
        this.hasGold = false;
        this.arrows = 0;
        this.currentRoom = null;
    }
    /**
     *
     * @param newCurrentRoom Value to set the currentRoom field to.
     */
    public Hero(final Room newCurrentRoom) {
        this.hasGold = false;
        this.arrows = 0;
        this.currentRoom = newCurrentRoom;
    }
    /**
     *
     * @return Value of arrows field;
     */
    public int getArrows() {
        return this.arrows;
    }
    /**
     *
     * @param newArrows Value to set the arrows field to.
     */
    public void setArrows(final int newArrows) {
        this.arrows = newArrows;
    }
    /**
     *
     * @return Value of hasGold field.
     */
    public boolean isHasGold() {
        return this.hasGold;
    }
    /**
     *
     * @param newHasGold Value to set the hasGold field to.
     */
    public void setHasGold(final boolean newHasGold) {
        this.hasGold = newHasGold;
    }
    /**
     *
     * @return Value of the currentRoom field.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    /**
     *
     * @param newCurrentRoom Value to set the currentRoom field of.
     */
    public void setCurrentRoom(final Room newCurrentRoom) {
        this.currentRoom = newCurrentRoom;
    }
    /**
     *
     * @return Direction of the hero as a character.
     */
    public char getDirectionAsChar() {
        switch (this.direction) {
            case E -> {
                return 'E';
            }
            case S -> {
                return 'S';
            }
            case W -> {
                return 'W';
            }
            default -> {
                return 'N';
            }
        }
    }
    /**
     *
     * @param directionAsChar Direction the hero is looking to.
     */
    public void setDirectionAsChar(final char directionAsChar) {
        switch (directionAsChar) {
            case 'E' -> this.direction = Map.directions.E;
            case 'S' -> this.direction = Map.directions.S;
            case 'W' -> this.direction = Map.directions.W;
            default -> this.direction = Map.directions.N;
        }
    }
}
