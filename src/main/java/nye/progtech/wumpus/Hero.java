package nye.progtech.wumpus;

import java.util.Objects;

public class Hero {
    private boolean hasGold;
    private int arrows;
    Room currentRoom;

    private Map.directions direction;

    public Hero(){
        this.hasGold = false;
        this.arrows = 0;
        this.currentRoom = null;
    }

    public Hero(Room currentRoom){
        this.hasGold = false;
        this.arrows = 0;
        this.currentRoom = currentRoom;
    }

    public int getArrows(){
        return this.arrows;
    }

    public void setArrows(int arrows){
        this.arrows = arrows;
    }

    public boolean isHasGold() {
        return this.hasGold;
    }

    public void setHasGold(boolean hasGold){
        this.hasGold = hasGold;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero hero)) return false;
        return isHasGold() == hero.isHasGold() && getArrows() == hero.getArrows() && Objects.equals(getCurrentRoom(), hero.getCurrentRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isHasGold(), getArrows(), getCurrentRoom());
    }

    public Map.directions getDirection(){
        return this.direction;
    }

    public void setDirection(Map.directions direction){
        this.direction = direction;
    }
}
