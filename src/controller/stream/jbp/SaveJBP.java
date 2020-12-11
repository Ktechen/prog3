package controller.stream.jbp;

import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploader;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

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
            xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));

            xmlEncoder.writeObject(StorageAsSingelton.getInstance());

        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (xmlEncoder != null) {
                xmlEncoder.close();
            }
        }
    }

}
