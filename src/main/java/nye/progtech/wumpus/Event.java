package nye.progtech.wumpus;

/**
 *
 */
public abstract class Event {
    /**
     *
     */
    public abstract void encounter();
    /**
     *
     */
    public abstract void message();
    /**
     *
     * @return Character to print in the printMap function.
     */
    public abstract char print();
}
