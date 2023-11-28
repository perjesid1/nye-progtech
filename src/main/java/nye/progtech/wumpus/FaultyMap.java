package nye.progtech.wumpus;

public class FaultyMap extends Exception{
    public FaultyMap(String errorMessage) {
        super(errorMessage);
    }
}
