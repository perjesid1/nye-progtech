package nye.progtech.wumpus;

public class MapSaveHandler extends SaveHandler{
    private Map handledMap;
    public MapSaveHandler(String fileName, Map mapToSave){
        super(fileName);
        this.handledMap = mapToSave;
    }

    public Map loadNewMap(){
        return handledMap;
    }

    public void saveMap(Map map){

    }
}
