package nye.progtech.wumpus;
/**
 *
 */
public class Wumpus extends Event {
    /**
     *
     */
    private boolean alive;
    /**
     *
     */
    public Wumpus() {
        this.alive = true;
    }
    /**
     *
     */
    @Override
    public void encounter() {
        if (alive) {
            System.out.printf("AH! The Wumpus got you!\nGAME OVER");
        }
    }
    /**
     *
     */
    @Override
    public void message() {
        if (alive) {
            System.out.println("You feel a foul stench...");
        }
    }
    /**
     *
     * @return Character to print.
     */
    @Override
    public char print() {
        return 'U';
    }
    /**
     *
     * @param newAlive New state of the Wumpus.
     */
    public void setAlive(final boolean newAlive) {
        this.alive = newAlive;
    }
    /**
     *
     * @return Current state of the Wumpus.
     */
    public boolean isAlive() {
        return alive;
    }
}
