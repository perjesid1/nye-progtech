package nye.progtech.wumpus;

/**
 *
 */
public class Escape extends Event {
    /**
     *
     */
    private boolean canEscape;

    /**
     *
     */
    public Escape() {
        this.canEscape = false;
    }

    /**
     *
     */
    @Override
    public final void encounter() {
        if (canEscape) {
            System.out.println("Hooray! You made it in one piece!\nGood job!");
        }
    }

    /**
     *
     * @return Returns if the player can escape through this room.
     */
    public boolean isCanEscape() {
        return canEscape;
    }

    /**
     *
     * @param newCanEscape canEscape field's new value.
     */
    public void setCanEscape(final boolean newCanEscape) {
        this.canEscape = newCanEscape;
    }

    /**
     *
     */
    @Override
    public void message() { }

    /**
     *
     * @return Character to print in printMap function.
     */
    @Override
    public final char print() {
        return 'E';
    }
}
