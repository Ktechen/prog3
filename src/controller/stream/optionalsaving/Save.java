package controller.stream.optionalsaving;

import controller.stream.StreamParameter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class Save {

    private String filename;
    private final Object writeableObject;

    Save(String filename, Object writeableObject) {
        this.filename = filename;
        this.filename = StreamParameter.PATH + this.filename;
        this.writeableObject = writeableObject;
        this.save();
    }

    void save() {

        FileOutputStream fs = null;
        ObjectOutputStream os = null;

        try {
            fs = new FileOutputStream(filename);
            os = new ObjectOutputStream(fs);
            os.writeObject(writeableObject);

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
