package controller.stream.jbp;

import modell.bean.BeanStorage;

public class JBP {
    public static final String PATH = "testfiles/";
    private String filename;
    private LoadJBP loadJBP;
    private SaveJBP saveJBP;

    //TODO PUBLIC 

    public JBP(String filename) {
        if (null == filename) {
            throw new NullPointerException("filename is null");
        }
        this.filename = filename;
        this.filename = PATH + this.filename;
    }

    public void save() {
        this.saveJBP = new SaveJBP(this.filename);
    }

    public BeanStorage load() {
        this.loadJBP = new LoadJBP(this.filename);
        return this.loadJBP.getObject();
    }

}
