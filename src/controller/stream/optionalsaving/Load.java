package controller.stream.optionalsaving;


import java.io.*;

class Load {

    private String filename;
    private Object o;

    Load(String filename) {
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

    Object getLoadObject() {
        return this.o;
    }
}
