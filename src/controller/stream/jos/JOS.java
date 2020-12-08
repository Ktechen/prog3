package controller.stream.jos;

import java.util.List;

public class JOS {

    private Load load;
    private Save save;
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
        this.load = new Load(this.filename);
        return this.load.getList();
    }

    /**
     * Save a Object in to file
     *
     * @param o = object to be saved
     */
    public void save(Object o) {
        this.save = new Save(this.filename, o);
    }


}
