package net.server.serverCommands;

import java.io.*;

public abstract class CommandServer {

    private DataInputStream in;
    private DataOutputStream out;

    /**
     * handle the in and out stream form server
     * @param in
     * @param out
     */
    public CommandServer(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void sendMessage(String msg) throws IOException {
        this.out.writeUTF(msg);
    }

    public Object getMessage() throws IOException {
        return this.in.readUTF();
    }

}
