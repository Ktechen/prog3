package controller.stream.jos;

import controller.stream.StreamParameter;

public class JOS  {

    private LoadJOS loadJOS;
    private SaveJOS saveJOS;
    private String filename;

    public JOS(String filename) {
        if (null == filename) {
            throw new NullPointerException("filename is null");
        }
        this.filename = filename;
        this.filename = StreamParameter.PATH + this.filename;
    }

    /**
     * LoadJOS a object out of file
     *
     * @return object
     */
    public Object load() {
        this.loadJOS = new LoadJOS(this.filename);
        return this.loadJOS.getList();
    }

    /**
     * SaveJOS a Object in to file
     *
     * @param o = object to be saved
     */
    public void save(Object o) {
        this.saveJOS = new SaveJOS(this.filename, o);
    }


}
