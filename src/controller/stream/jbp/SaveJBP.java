package controller.stream.jbp;


import modell.bean.BeanStorage;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

class SaveJBP {

    private final String filename;

    public SaveJBP(String filename) {

        if (null == filename) {
            throw new NullPointerException("filename is null");
        }

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

            xmlEncoder.writeObject(beanStorage.getGetUsedTags());
            xmlEncoder.flush();

            xmlEncoder.writeObject(beanStorage.getCounter());
            xmlEncoder.flush();

            xmlEncoder.writeObject(beanStorage.getMedia());
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
