package nye.progtech.wumpus;
/**
 *
 */
public class SaveHandler {
    /**
     *
     */
    private String fileName;
    /**
     *
     * @param newFileName Name of the save file we are using.
     */
    public SaveHandler(final String newFileName) {
        this.fileName = newFileName;
    }
    /**
     *
     * @return Name of the save file we are using.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     *
     * @param newFileName Name of the save file we want to use.
     */
    public void setFileName(final String newFileName) {
        this.fileName = newFileName;
    }
}
