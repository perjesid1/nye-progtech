package nye.progtech.wumpus;

import java.util.Objects;

public class Room {
    private int row;
    private int column;
    private boolean hasEvent;

    private Event event;

    public Room(){
        this.row = 0;
        this.column = 0;
        this.hasEvent = false;
        this.event = null;
    }

    public Room(int row, int column){
        this.row = row;
        this.column = column;
        this.hasEvent = false;
        this.event = null;
    }

    public Room(int row, int column, Event event){
        this.row = row;
        this.column = column;
        this.event = event;
        this.hasEvent = true;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public boolean isHasEvent() {
        return hasEvent;
    }

    public Event getEvent(){
        return this.event;
    }
}
