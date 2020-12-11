package controller.stream.jos;


import controller.stream.StreamParameter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

class LoadJOS {

    private String filename;
    private Object o;

    public LoadJOS(String filename) {
        this.filename = filename;
        this.load();
    }

    void load() {
        FileInputStream fs = null;
        ObjectInputStream os = null;

        try {
            fs = new FileInputStream(filename);
            os = new ObjectInputStream(fs);

            this.o = os.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.getStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object getList() {
        return this.o;
    }
}
