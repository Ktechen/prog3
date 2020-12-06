package controller.stream.jos;

public class JOS {

    public static final String path = "testfiles/";
    private LoadJOS loadJOS;
    private SaveJOS saveJOS;
    private String filename;

    public JOS(String filename) {
        if (null == filename) {
            throw new NullPointerException("filename is null");
        }

        this.filename = filename;
    }

    /**
     * Load a object out of file
     *
     * @return object
     */
    public Object load() {
        this.loadJOS = new LoadJOS(filename);
        return this.loadJOS.getLoadObject();
    }

    /**
     * Save a Object in to file
     *
     * @param o = object to be saved
     */
    public void save(Object o) {
        this.saveJOS = new SaveJOS(filename, o);
    }

}
