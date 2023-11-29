package nye.progtech.wumpus;

public class Map {
    public enum directions {
        E,
        S,
        W,
        N;
    }
    private int size;
    private int escapeRow;
    private int escapeCol;
    private int wumpusCount;
    private Room[][] rooms;
    private Hero hero;
    public Map(int size, Hero hero) throws Exception{
        this.hero = hero;
        this.escapeCol = hero.getCurrentRoom().getColumn();
        this.escapeRow = hero.getCurrentRoom().getRow();
        if(size < 6)
            throw new InvalidMapSize("Map too small.");
        if(size > 20)
            throw new InvalidMapSize("Map too big.");
        if(escapeRow < 0 || escapeRow > size - 1 || escapeCol < 0 || escapeCol > size-1)
            throw new InvalidCoordinates("Hero outside of the map.");

        this.rooms = new Room[size][size];
        if(size <= 8){
            this.wumpusCount = 1;
        } else if(size < 14){
            this.wumpusCount = 2;
        } else{
            this.wumpusCount = 3;
        }
        this.hero.setArrows(wumpusCount);
    }

    public Hero getHero() {
        return this.hero;
    }

    public int getWumpusCount() {
        return this.wumpusCount;
    }

    public void setWumpusCount(int wumpusCount) {
        this.wumpusCount = wumpusCount;
    }

    public int getSize() {
        return this.size;
    }

    public Room getRoom(int row, int col){
        return this.rooms[row][col];
    }

    public Room getEscapeRoom(){
        return this.rooms[this.escapeRow][this.escapeCol];
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setRoom(int row, int col, Room room){
        this.rooms[row][col] = room;
    }
}
