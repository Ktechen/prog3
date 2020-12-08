package controller.stream.jos;


import controller.stream.Const;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

class Load {

    private String filename;
    private Object listo;

    public Load(String filename) {
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

            listo = os.readObject();

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
        return listo;
    }
}
