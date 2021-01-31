package controller.management;

import java.io.*;

public abstract class CommandManagement {

    private DataInputStream in;
    private DataOutputStream out;
    private boolean offline = false;

    /**
     * handle the in and out stream form server
     * @param in
     * @param out
     */
    public CommandManagement(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void sendMessage(String msg) throws IOException {
        this.out.writeUTF(msg);
        this.out.flush();
    }

    public String getMessage() throws IOException {
        return this.in.readUTF();
    }

    protected boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }
}
