package nye.progtech.wumpus;

import java.util.Objects;

public class Wumpus extends Event{
    private boolean alive;

    public Wumpus(){
        this.alive = true;
    }

    @Override
    public void encounter() {
        if(alive)
            System.out.printf("AH! The Wumpus got you!\nGAME OVER");
    }

    @Override
    public void message() {
        if(alive)
            System.out.println("You feel a foul stench...");
    }
    @Override
    public char print() {
        return 'U';
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public boolean isAlive() {
        return alive;
    }
}
