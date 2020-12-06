package controller.stream.jos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class SaveJOS {

    private String filename;
    private final Object writeableObject;

    SaveJOS(String filename, Object writeableObject) {
        this.filename = filename;
        this.writeableObject = writeableObject;
        this.save();
    }

    void save() {

        FileOutputStream fs = null;
        ObjectOutputStream os = null;
        this.filename = JOS.path + this.filename;

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
