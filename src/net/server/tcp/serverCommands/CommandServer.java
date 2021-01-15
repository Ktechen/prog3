package net.server.tcp.serverCommands;

import java.io.*;

public abstract class CommandServer {

    private DataInputStream in;
    private DataOutputStream out;

    public CommandServer(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void sendMessage(String msg) throws IOException {
        this.out.writeUTF(msg);
    }

    public Object getMessage() throws IOException, ClassNotFoundException {
        return this.in.readUTF();
    }

}
