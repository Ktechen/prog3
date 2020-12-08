package controller.stream.optionalsaving;

public class OptionalSaving {

    private Load load;
    private Save save;
    private String filename;

    public OptionalSaving(String filename) {
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
        this.load = new Load(filename);
        return this.load.getLoadObject();
    }

    /**
     * Save a Object in to file
     *
     * @param o = object to be saved
     */
    public void save(Object o) {
        this.save = new Save(filename, o);
    }

}
