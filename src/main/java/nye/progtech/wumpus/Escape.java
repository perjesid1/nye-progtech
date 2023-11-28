package nye.progtech.wumpus;

public class Escape extends Event{
    private boolean canEscape;
    @Override
    public void encounter() {
        if(canEscape)
            System.out.println("Hooray! You made it in one piece!");
    }

    public boolean isCanEscape() {
        return canEscape;
    }

    public void setCanEscape(boolean canEscape) {
        this.canEscape = canEscape;
    }

    @Override
    public void message() {

    }

    @Override
    public char print() {
        return 'E';
    }
}
