package controller.management;

import java.io.*;

public abstract class CommandManagement {

    private DataInputStream in;
    private DataOutputStream out;
    private boolean offline = false;

    //TODO Alle überstream realiseren  (Wenn Zeit dafür ist)

    /**
     * handle the in and out stream form server
     * @param in
     * @param out
     */
    CommandManagement(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    void sendMessage(String msg) throws IOException {
        this.out.writeUTF(msg);
        this.out.flush();
    }

    String getMessage() throws IOException {
        return this.in.readUTF();
    }

    protected boolean isOffline() {
        return offline;
    }

    /**
     * Change Stream from DataInputStream to System.out Stream
     *
     * @param offline
     */
    public void setOffline(boolean offline) {
        this.offline = offline;
    }
}
