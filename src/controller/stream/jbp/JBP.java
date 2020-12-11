package controller.stream.jbp;

import controller.stream.StreamParameter;

public class JBP {

    private String filename;
    private LoadJBP loadJBP;
    private SaveJBP saveJBP;

    public JBP(String filename) {
        if (null == filename) {
            throw new NullPointerException("filename is null");
        }
        this.filename = filename;
        this.filename = StreamParameter.PATH + this.filename;
    }

    public void save() {
        this.saveJBP = new SaveJBP(this.filename);
    }

    public Object load() {
        this.loadJBP = new LoadJBP(this.filename);
        return this.loadJBP.getObject();
    }

}
