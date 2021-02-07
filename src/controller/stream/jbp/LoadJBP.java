package controller.stream.jbp;

import modell.bean.BeanStorage;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;

class LoadJBP {

    private String filename;
    private BeanStorage o;

    LoadJBP(String filename) {
        this.filename = filename;
        this.load();
    }

    /**
     * http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_17_011.htm
     */
    void load() {

        XMLDecoder dec = null;

        try {
            dec = new XMLDecoder(new FileInputStream(filename));
            this.o = (BeanStorage) dec.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dec != null)
                dec.close();
        }
    }

    public BeanStorage getObject() {
        return this.o;
    }
}
