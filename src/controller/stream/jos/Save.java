package controller.stream.jos;


import controller.stream.Const;
import modell.data.storage.Storage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

class Save {

    private String filename;
    private Object objectList;
    private Storage storage;

    /**
     * Save a Object
     * @param filename
     * @param objectList
     */
    public Save(String filename, Object objectList) {
        this.filename = filename;
        this.filename = Const.path + this.filename;
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
