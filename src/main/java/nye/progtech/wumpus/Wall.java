package nye.progtech.wumpus;

public class Wall extends Event{
    public Wall(){}

    @Override
    public void encounter() {
        System.out.println("Banging your head against the wall won't help...");
    }

    @Override
    public void message() {}

    @Override
    public char print() {
        return 'W';
    }
}
