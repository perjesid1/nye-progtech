package nye.progtech.wumpus;

public class SaveHandler {
    private String fileName;
    public SaveHandler(String fileName){
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
