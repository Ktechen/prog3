package controller.stream.jos;

import controller.stream.Const;

import java.io.*;

class LoadJOS {

    private String filename;
    private Object o;

    LoadJOS(String filename) {
        this.filename = filename;
        this.filename = Const.path + this.filename;
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

    Object getLoadObject() {
        return this.o;
    }
}
