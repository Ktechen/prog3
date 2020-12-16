package controller.stream.jbp;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;

class LoadJBP {

    private String filename;
    private Object o;

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
            this.o = dec.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dec != null)
                dec.close();
        }
    }

    Object getObject() {
        return this.o;
    }
}
