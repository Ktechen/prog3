package controller.stream.jos;


import controller.stream.StreamParameter;
import modell.data.storage.Storage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class SaveJOS {

    private String filename;
    private Object objectList;
    private Storage storage;

    /**
     * SaveJOS a Object
     * @param filename
     * @param objectList
     */
    public SaveJOS(String filename, Object objectList) {
        this.filename = filename;
        this.objectList = objectList;
        this.save(this.objectList);
    }

    void save(Object o) {

        FileOutputStream fs = null;
        ObjectOutputStream os = null;

        try {
            fs = new FileOutputStream(filename);
            os = new ObjectOutputStream(fs);
            os.writeObject(o);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                    fs.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
    }

}
