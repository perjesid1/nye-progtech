package nye.progtech.wumpus;
/**
 *
 */
public class Gold extends Event {
    /**
     *
     */
    private boolean found;
    /**
     *
     */
    public Gold() {
        this.found = false;
    }

    /**
     *
     * @param newFound Value to set for found filed.
     */
    public void setFound(final boolean newFound) {
        this.found = newFound;
    }
    /**
     *
     * @return value of the found field;
     */
    public boolean isFound() {
        return this.found;
    }
    /**
     *
     */
    @Override
    public void encounter() {
        if (!found) {
            System.out.println("You found what you came here for... "
                    + "Now get out!");
            this.found = true;
        }
    }
    /**
     *
     */
    @Override
    public void message() {
        if (!found) {
            System.out.println("You saw something glint in the corner"
                    + " of your eye...");
        }
    }
    /**
     *
     * @return Character to print in the printMap function.
     */
    @Override
    public char print() {
        return 'G';
    }
}
