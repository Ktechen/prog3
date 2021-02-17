package controller.stream.jbp;


import modell.bean.BeanStorage;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;

class SaveJBP {

    private final String filename;

    public SaveJBP(String filename) {
        this.filename = filename;
        this.save();
    }

    /**
     * Source: http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_17_011.htm
     */
    void save() {

        XMLEncoder xmlEncoder = null;

        try {
            xmlEncoder = new XMLEncoder(new FileOutputStream(filename));

            BeanStorage beanStorage = new BeanStorage();
            beanStorage.addToMediaList();
            beanStorage.addToUploaderList();

            xmlEncoder.writeObject(beanStorage);
            xmlEncoder.flush();

        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (xmlEncoder != null) {
                xmlEncoder.close();
            }
        }
    }

}
