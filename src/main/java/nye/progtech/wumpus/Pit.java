package nye.progtech.wumpus;

public class Pit extends Event{
    public Pit(){

    }

    @Override
    public void encounter() {
        System.out.println("You fall into an endless pit...\nGAME OVER");
    }

    @Override
    public void message() {
        System.out.println("You feel a slight breeze...");
    }

    @Override
    public char print() {
        return 'P';
    }
}
