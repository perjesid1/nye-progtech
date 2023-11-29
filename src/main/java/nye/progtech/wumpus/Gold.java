package nye.progtech.wumpus;

import java.util.Objects;

public class Gold extends Event{
    private boolean found;
    public Gold(){
        this.found = false;
    }

    public void setFound(boolean found){ this.found = found; }
    public boolean isFound() { return this.found; }

    @Override
    public void encounter() {
        if(!found) {
            System.out.println("You fond what you came here for... Now get out!");
            this.found = true;
        }
    }

    @Override
    public void message() {
        if(!found)
            System.out.println("You saw something glint in the corner of your eye...");
    }

    @Override
    public int hashCode() {
        return Objects.hash(found);
    }

    @Override
    public char print() {
        return 'G';
    }
}
